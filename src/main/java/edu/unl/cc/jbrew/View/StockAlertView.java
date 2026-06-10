package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Reports.StockAlert;

public class StockAlertView {

    public void checkStockLevel(StockAlert alert) {
        if (alert.getCurrentStock() <= alert.getMinStock()) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                     ALERTA DE STOCK                          ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  Producto: " + alert.getProduct().getName());
            System.out.println("║  Stock actual: " + alert.getCurrentStock());
            System.out.println("║  Stock mínimo: " + alert.getMinStock());
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
        }
    }

    public void resolveAlert(StockAlert alert) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    ALERTA RESUELTA                           ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║   Producto: " + alert.getProduct().getName());
        System.out.println("║  Estado: La alerta ha sido resuelta exitosamente");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void showAlert(StockAlert alert) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ALERTA DE STOCK                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID de Alerta: " + alert.getIdAlert());
        System.out.println("║  Producto: " + alert.getProduct().getName());
        System.out.println("║  Stock Actual: " + alert.getCurrentStock());
        System.out.println("║  Stock Mínimo: " + alert.getMinStock());
        System.out.println("║  Fecha de Alerta: " + alert.getAlertDate());
        System.out.println("║  Estado: " + (alert.isResolved() ? "RESUELTA" : "PENDIENTE"));
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
