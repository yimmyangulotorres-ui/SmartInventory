package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Reports.Report;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;
import java.util.List;

public class ReportView {

    public void generateReportStock(Report report) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                       REPORTE DE STOCK                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Generando reporte de stock...");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void generateReportSale(Report report) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                    REPORTE DE VENTAS                         ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Generando reporte de ventas...");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void generateReportMovements(Report report) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   REPORTE DE MOVIMIENTOS                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Generando reporte de movimientos...");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void consultKardex(Report report, List<Kardex> kardexList) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CONSULTANDO KARDEX                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Consultando kardex para reporte...");
        for (Kardex kardex : kardexList) {
            kardex.showKardexEntry();
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void consultStockAlerts(Report report, List<StockAlert> stockAlertList) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               CONSULTANDO ALERTAS DE STOCK                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║    Consultando alertas de stock para reporte...");
        for (StockAlert alert : stockAlertList) {
            System.out.println("║   Alerta: " + alert.toString());
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void exportReport(Report report) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  EXPORTANDO REPORTE                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Exportando reporte a archivo...");
        System.out.println("║  Exportación completada");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
