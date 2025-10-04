package com.example.service;

import com.example.exception.ExportException;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Exports employee reports to file with exception handling
 */
public class EmployeeReportExporter {
    private final String filename;

    public EmployeeReportExporter(String filename) {
        this.filename = filename;
    }

    public void exportToFile(String content) throws ExportException {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            throw new ExportException("Failed to export employee report to: " + filename, e);
        }
    }
}