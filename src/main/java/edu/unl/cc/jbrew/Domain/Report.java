package edu.unl.cc.jbrew.Domain;

import java.util.Date;

public class Report {

    private int idReport;
    private String reportType;
    private Date generationDate;

    public Report() {
        generationDate = new Date();
    }

    public Report(int idReport, String reportType, Date generationDate) {
        this.idReport = idReport;
        this.reportType = reportType;
        this.generationDate = generationDate;
    }

    // Generar reporte de stock
    public void generateStockReport() {
        System.out.println("Generando reporte de stock...");
    }

    // Generar reporte de ventas
    public void generateSalesReport() {
        System.out.println("Generando reporte de ventas...");
    }

    // Generar reporte de movimientos
    public void generateMovementReport() {
        System.out.println("Generando reporte de movimientos...");
    }

    // Exportar reporte
    public void exportReport() {
        System.out.println("Exportando reporte...");
    }

    // Getters y Setters
    public int getIdReport() {
        return idReport;
    }

    public void setIdReport(int idReport) {
        this.idReport = idReport;
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
