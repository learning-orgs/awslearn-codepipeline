package com.aws.awslearncodepipeline.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Configuration
@PropertySource("classpath:roles.properties")
public class RolesConfig {

    private static final Logger logger = LoggerFactory.getLogger(RolesConfig.class);

    @Autowired
    private Environment env;

    /**
     * Retrieves all role mappings from the properties file.
     *
     * @return a map of URLs to roles.
     */
    public Map<String, Set<String>> getRoleMappings() {
        Map<String, Set<String>> roleMappings = new HashMap<>();

        // Fetch role mappings from properties file using the keys defined in 'roles.keys'
        String roleKeys = env.getProperty("roles.keys", "");
        logger.info("Fetching role mappings with keys: {}", roleKeys);

        for (String key : roleKeys.split(",")) {
            String urls = env.getProperty(key);
            String roles = env.getProperty(key.replace("urls", "roles"));

            if (urls != null && roles != null) {
                roleMappings.put(urls, Set.of(roles.split(",")));
                logger.info("Mapped URLs: {} to Roles: {}", urls, roles);
            } else {
                logger.warn("Missing URL or Role mapping for key: {}", key);
            }
        }

        return roleMappings;
    }

    /**
     * Retrieves public URLs from the properties file.
     *
     * @return an array of public URLs.
     */
    public String[] getPublicUrls() {
        String publicUrls = env.getProperty("public.urls", "");

        // Log the public URLs being fetched
        logger.info("Fetching public URLs: {}", publicUrls);

        if (!publicUrls.isEmpty()) {
            return publicUrls.split(",");
        } else {
            logger.warn("No public URLs defined in 'public.urls' property");
            return new String[0];
        }
    }
}
