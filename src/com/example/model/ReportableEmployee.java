package com.example.model;

import com.example.service.Exportable;

/**
 * Interface for employees that can generate reports
 */
public interface ReportableEmployee extends Exportable {
    String generateDetailedReport();
    String getEmployeeType();
}