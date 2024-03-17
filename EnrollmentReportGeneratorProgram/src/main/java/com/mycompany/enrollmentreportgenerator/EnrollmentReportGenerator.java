package com.mycompany.enrollmentreportgenerator;

// Jackson dependency for JSON processing
import CustomModels.Employee;
import JSONPayloads.EnrollmentRecord;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// Apache Commons CSV dependency for CSV file creation output
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *  @author     Nathaniel Merck
 *  @date       2.18.24
 *  @desc       This program is the main logic of fetching JSON, processing data, sorting, and writing CSV files.
 */

public class EnrollmentReportGenerator {
    // constant to hold the JSON data url we are pulling from
    private static final String JSON_URL = "https://raw.githubusercontent.com/iagtech/challenges-data/main/coding-problem.json";
    // define the CSV_FILE_PATH constant by finding the user's home, then their desktop, then a custom folder I made for holding the csv files
    private static final String CSV_FILE_PATH = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "TestCSVFiles" + File.separator;

    // initializer to make sure we have the existing directory that we want
    static {
        ensureDirectoryExists(CSV_FILE_PATH);
    }
    
    public static void main(String[] args) {
        try {
            // fetch the enrollments 
            List<EnrollmentRecord> enrollments = fetchEnrollments();
            // once gathered, we group and sort the enrollments based on the requirements
            Map<Integer, List<Employee>> enrollmentsByEmployer = groupAndSortEnrollments(enrollments);
            // loop through each employer id and their employees to make a csv file for each employer
            enrollmentsByEmployer.forEach((employerId, employees) -> {
                // name the file appropriately
                String filePath = CSV_FILE_PATH + employerId + ".csv";
                try {
                    writeCsv(filePath, employees);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
     
    /**
     * Fetches enrollment records from a predefined JSON URL.
     * 
     * @return A list of enrollment record objects.
     * @throws IOException If there is an issue reading from the URL.
     */
    private static List<EnrollmentRecord> fetchEnrollments() throws IOException {
        // using Jackson to make an ObjectMapper to convert JSON to java objects
        ObjectMapper mapper = new ObjectMapper();
        // we use the EnrollmentRecord class so that our data matches the expected JSON payload format. 
        TypeReference<List<EnrollmentRecord>> typeReference = new TypeReference<>() {};
        // return the read values of the retrieved JSON
        return mapper.readValue(new URL(JSON_URL), typeReference);
    }

    /**
     * Groups and sorts enrollment records by employer, preparing them for CSV output.
     * Requirements: records are sorted by total enrollments in descending order, then alphabetically by name.
     * 
     * @param enrollments The list of enrollment records to process.
     * @return A map of employer IDs to lists of  objects, sorted according to the specified criteria.
     */
    private static Map<Integer, List<Employee>> groupAndSortEnrollments(List<EnrollmentRecord> enrollments) {
        Map<Integer, List<Employee>> enrollmentsByEmployer = new HashMap<>();
        for (EnrollmentRecord record : enrollments) {
            if (record.getIsEnrolled()) {
                int employerId = record.getEmployer();
                Employee emp = new Employee();
                emp.setEmployer(employerId);
                emp.setName(record.getEmployee());
                emp.setTotalEnrolled(record.getChildrenEnrolled() + record.getSpousesEnrolled() + 1);
                emp.setSpousesEnrolled(record.getSpousesEnrolled());
                emp.setChildrenEnrolled(record.getChildrenEnrolled());

                // add employees in correlation to what employer they belong to, to this list
                enrollmentsByEmployer.computeIfAbsent(employerId, k -> new ArrayList<>()).add(emp);
            }
        }
        
        // sort by total enrollments descending, then by name alphabetically
        for (List<Employee> list : enrollmentsByEmployer.values()) {
            Collections.sort(list, new Comparator<Employee>() {
                @Override
                public int compare(Employee e1, Employee e2) {
                    // First, compare by totalEnrolled in reverse order
                    int totalEnrolledComparison = Integer.compare(e2.getTotalEnrolled(), e1.getTotalEnrolled());
                    if (totalEnrolledComparison != 0) {
                        return totalEnrolledComparison;
                    }
                    // If totalEnrolled is the same, then compare by name
                    return e1.getName().compareTo(e2.getName());
                }
            });
        }

        return enrollmentsByEmployer;
    }

    /**
     * Writes the list of employees to a CSV file at the given path. 
     * 
     * @param filePath The path to the file where the CSV will be written.
     * @param employees The list of employees to write to the CSV file.
     * @throws IOException If an error occurs during writing to the file.
     */
    private static void writeCsv(String filePath, List<Employee> employees) throws IOException {
        File file = new File(filePath);
        try (FileWriter out = new FileWriter(file);
            // define the printer and headers
            CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT.withHeader("Employer", "Employee", "Total Enrolled", "Spouses Enrolled", "Children Enrolled"))) {
            // loop through each line for employees
            for (Employee employee : employees) {
                printer.printRecord(employee.getEmployer(), employee.getName(), employee.getTotalEnrolled(), employee.getSpousesEnrolled(), employee.getChildrenEnrolled());
            }
        }
        // print out success for csv file creation
        System.out.println("CSV file has been created for employer ID: " + employees.get(0).getEmployer() + " at " + file.getAbsolutePath());
    }
    
    /**
     * Checks that the directory for the specified file exists, creating it if it doesn't exist. 
     * 
     * @param path The parent directory of the file.
     */
    private static void ensureDirectoryExists(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
            // make the directory and check if it was successfully created 
            boolean wasSuccessful = directory.mkdirs();
            if (wasSuccessful) {
                System.out.println("Directory created successfully at: " + path);
            } else {
                System.out.println("Failed to create directory.");
            }
        } else {
            System.out.println("Directory already exists at: " + path);
        }
    }
}
