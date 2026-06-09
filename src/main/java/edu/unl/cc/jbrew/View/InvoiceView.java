package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Invoice.PurchaseInvoice;
import edu.unl.cc.jbrew.Domain.Invoice.SaleInvoice;

public class InvoiceView {

    public void calculateTotalPurchaseInvoice(PurchaseInvoice invoice) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              FACTURA DE COMPRA                            ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Número de Factura: " + invoice.getInvoiceNumber());
        System.out.println("║  Calculando total...");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void generatePurchaseInvoice(PurchaseInvoice invoice) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              FACTURA DE COMPRA GENERADA                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Número de Factura: " + invoice.getInvoiceNumber());
        System.out.println("║  Proveedor: " + invoice.getSupplier().getName());
        System.out.println("║  Estado: Factura generada exitosamente");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void calculateTotalSaleInvoice(SaleInvoice invoice) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              FACTURA DE VENTA                              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Número de Factura: " + invoice.getInvoiceNumber());
        System.out.println("║  Calculando total...");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void generateSaleInvoice(SaleInvoice invoice) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║             FACTURA DE VENTA GENERADA                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Número de Factura: " + invoice.getInvoiceNumber());
        System.out.println("║  Cliente: " + invoice.getCustomer().getName());
        System.out.println("║  Estado: Factura generada exitosamente");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }
}
