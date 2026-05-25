package edu.unl.cc.jbrew.Domain;

import java.util.Date;

public class Report {

    private int reportId;
    private String reportType;
    private Date generationDate;

    public Report(int reportId, String reportType, Date generationDate) {
        this.reportId = reportId;
        this.reportType = reportType;
        this.generationDate = generationDate;
    }

    public void generateReportStock() {
        System.out.println("Generando reporte de stock...");
    }

    public void generateReportSale() {
        System.out.println("Generando reporte de ventas...");
    }

    public void generateReportMovements() {
        System.out.println("Generando reporte de movimientos...");
    }

    public void exportReport() {
        System.out.println("Exportando reporte a archivo...");
    }

    // Getters y Setters
    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }
}