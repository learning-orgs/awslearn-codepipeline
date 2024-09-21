package com.aws.awslearncodepipeline.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * PublicController handles requests for the public-facing pages of the application.
 * It includes methods for rendering the public page, sign-up, and forgot password pages.
 */
@Controller
public class AppController {

    private static final Logger logger = LoggerFactory.getLogger(AppController.class);

    /**
     * Displays the public landing page with links to login, sign-up, and forgot password.
     *
     * @return the name of the public page view.
     */
    @GetMapping("/")
    public String publicPage() {
        logger.info("Accessing public landing page");
        return "public";
    }

    @GetMapping("/login")
    public String loginPage() {
        logger.info("Accessing login landing page");
        return "login";
    }

    /**
     * Displays the sign-up page for new users.
     *
     * @return the name of the sign-up page view.
     */
    @GetMapping("/signup")
    public String signup() {
        logger.info("Accessing sign-up page");
        return "signup";
    }

    /**
     * Handles the sign-up form submission.
     *
     * @return redirect to the login page after successful sign-up.
     */
    @PostMapping("/signup")
    public String handleSignup() {
        logger.info("Processing sign-up form submission");
        // Handle sign-up logic here (e.g., save user to database)
        return "redirect:/login"; // Redirect to login after sign-up
    }

    /**
     * Displays the forgot password page for users who need to reset their password.
     *
     * @return the name of the forgot password page view.
     */
    @GetMapping("/forgot-password")
    public String forgotPassword() {
        logger.info("Accessing forgot password page");
        return "forgot-password";
    }

    /**
     * Handles the forgot password form submission.
     *
     * @return redirect to the login page after processing the request.
     */
    @PostMapping("/forgot-password")
    public String handleForgotPassword() {
        logger.info("Processing forgot password form submission");
        // Handle password reset logic here (e.g., send reset email)
        return "redirect:/login"; // Redirect to login after requesting reset
    }
}
