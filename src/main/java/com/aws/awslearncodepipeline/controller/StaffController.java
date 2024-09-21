package com.aws.awslearncodepipeline.controller;

import com.aws.awslearncodepipeline.model.Staff;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/staff")
public class StaffController {

    private static final Logger logger = LoggerFactory.getLogger(StaffController.class);

    private List<Staff> staffList = new ArrayList<>(
            List.of(
                    new Staff(1, "John Doe", "HR"),
                    new Staff(2, "Jane Smith", "Engineering"),
                    new Staff(3, "Emily Johnson", "Finance")
            )
    );

    @Operation(summary = "Get all staff members")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the staff"),
            @ApiResponse(responseCode = "404", description = "No staff found")
    })
    @GetMapping
    public List<Staff> getAllStaff() {
        logger.info("Fetching all staff members");
        return staffList;
    }

    @Operation(summary = "Get a specific staff member by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff found"),
            @ApiResponse(responseCode = "404", description = "Staff not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Staff> getStaffById(@PathVariable int id) {
        logger.info("Fetching staff member with ID: {}", id);
        Optional<Staff> staff = staffList.stream().filter(s -> s.getId() == id).findFirst();
        if (staff.isPresent()) {
            return new ResponseEntity<>(staff.get(), HttpStatus.OK);
        } else {
            logger.warn("Staff member with ID: {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Create a new staff member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Staff created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<String> createStaff(@RequestBody Staff newStaff) {
        logger.info("Creating new staff member: {}", newStaff.getName());
        staffList.add(newStaff);
        return new ResponseEntity<>("Staff member created successfully", HttpStatus.CREATED);
    }

    @Operation(summary = "Update an existing staff member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff updated"),
            @ApiResponse(responseCode = "404", description = "Staff not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<String> updateStaff(@PathVariable int id, @RequestBody Staff updatedStaff) {
        logger.info("Updating staff member with ID: {}", id);
        Optional<Staff> existingStaff = staffList.stream().filter(s -> s.getId() == id).findFirst();
        if (existingStaff.isPresent()) {
            existingStaff.get().setName(updatedStaff.getName());
            existingStaff.get().setDepartment(updatedStaff.getDepartment());
            return new ResponseEntity<>("Staff member updated successfully", HttpStatus.OK);
        } else {
            logger.warn("Staff member with ID: {} not found", id);
            return new ResponseEntity<>("Staff member not found", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a staff member by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Staff deleted"),
            @ApiResponse(responseCode = "404", description = "Staff not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStaff(@PathVariable int id) {
        logger.info("Deleting staff member with ID: {}", id);
        Optional<Staff> staff = staffList.stream().filter(s -> s.getId() == id).findFirst();
        if (staff.isPresent()) {
            staffList.remove(staff.get());
            return new ResponseEntity<>("Staff member deleted successfully", HttpStatus.OK);
        } else {
            logger.warn("Staff member with ID: {} not found", id);
            return new ResponseEntity<>("Staff member not found", HttpStatus.NOT_FOUND);
        }
    }
}
