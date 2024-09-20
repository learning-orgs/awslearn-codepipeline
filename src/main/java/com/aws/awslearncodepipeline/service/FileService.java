package com.aws.awslearncodepipeline.service;

import com.aws.awslearncodepipeline.model.Users;
import com.aws.awslearncodepipeline.repository.UserRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FileService implements UserRepository {

    @Value("${users.file.path}")
    private String filePath;

    // Get all users from Excel
    public List<Users> getUsersFromExcel() throws IOException {
        List<Users> users = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                int id = (int) row.getCell(0).getNumericCellValue();
                String username = row.getCell(1).getStringCellValue();
                String password = row.getCell(2).getStringCellValue();
                String role = row.getCell(3).getStringCellValue();
                List<String> roles = Arrays.asList(role.split(","));
                // users.add(new Users(id, username, password, role));
                users.add(new Users(id, username, password, roles));

            }
        }

        return users;
    }

    // Add a new user to Excel
    public void addUser(Users user) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(new File(filePath))) {

            Sheet sheet = workbook.getSheetAt(0);
            int lastRow = sheet.getLastRowNum();

            Row newRow = sheet.createRow(lastRow + 1);
            newRow.createCell(0).setCellValue(user.getId());
            newRow.createCell(1).setCellValue(user.getUsername());
            newRow.createCell(2).setCellValue(user.getPassword());
            newRow.createCell(3).setCellValue("Default");

            workbook.write(fos);
        }
    }

    // Update user details in Excel
    public void updateUser(Users user) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(new File(filePath))) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getCell(1).getStringCellValue().equals(user.getUsername())) {
                    row.getCell(2).setCellValue(user.getPassword());  // Update password
                    //row.getCell(3).setCellValue(user.getRole());      // Update role
                    break;
                }
            }

            workbook.write(fos);
        }
    }

    // Delete a user by username from Excel
    public void deleteUser(String username) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis);
             FileOutputStream fos = new FileOutputStream(new File(filePath))) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row != null && row.getCell(1).getStringCellValue().equals(username)) {
                    sheet.removeRow(row);
                    break;
                }
            }

            // Re-adjust row numbers (optional but cleaner)
            workbook.write(fos);
        }
    }

    // Find user by username in Excel
    public Users findUserByUsername(String username) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row
                if (row.getCell(1).getStringCellValue().equals(username)) {
                    int id = (int) row.getCell(0).getNumericCellValue();
                    String password = row.getCell(2).getStringCellValue();
                    String role = row.getCell(3).getStringCellValue();
                    List<String> roles = Arrays.asList(role.split(","));
                    return new Users(id, username, password, roles);
                }
            }
        }

        return null; // Return null if no user is found
    }
}
