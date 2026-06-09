package edu.unl.cc.jbrew.View;

import edu.unl.cc.jbrew.Domain.Inventory.Inventory;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Inventory.Category;
import edu.unl.cc.jbrew.Domain.Movements.Movement;
import edu.unl.cc.jbrew.Domain.Movements.ProductMovement;
import edu.unl.cc.jbrew.Domain.Invoice.SaleInvoice;
import edu.unl.cc.jbrew.Domain.People.Supplier;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;
import java.util.Scanner;
import java.util.List;

public class View {
    private Scanner scanner; // Asociación con Scanner

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║            SISTEMA DE GESTIÓN DE INVENTARIO                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Agregar Categoría                                        ║");
        System.out.println("║  2. Agregar Producto                                         ║");
        System.out.println("║  3. Ver Productos                                            ║");
        System.out.println("║  4. Ver Categorías                                           ║");
        System.out.println("║  5. Buscar Producto                                          ║");
        System.out.println("║  6. Crear Movimiento (Entrada/Salida)                        ║");
        System.out.println("║  7. Ver Movimientos                                          ║");
        System.out.println("║  8. Ver Kardex                                               ║");
        System.out.println("║  9. Ver Alertas de Stock                                     ║");
        System.out.println("║  10. Generar Reportes                                        ║");
        System.out.println("║  0. Salir                                                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.print("║  Seleccione una opción: ");
    }

    public int getIntInput() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public String getStringInput() {
        return scanner.nextLine();
    }

    public double getDoubleInput() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void displayInventoryProductList(Inventory inventory) { // Asociación con Inventory
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║               LISTA DE PRODUCTOS DEL INVENTARIO              ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        for (Product product : inventory.showProduct()) {
            System.out.println("║  ID del Producto: " + product.getIdProduct());
            System.out.println("║  Nombre: " + product.getName());
            System.out.println("║  Descripción: " + product.getDescription());
            System.out.println("║  Precio de Venta: $" + product.getSalePrice());
            System.out.println("║  Precio de Compra: $" + product.getPurchasePrice());
            System.out.println("║  Stock: " + product.getStock());
            System.out.println("║  Stock Mínimo: " + product.getMinStock());
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayCategoryList(Inventory inventory) { // Asociación con Inventory
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     LISTA DE CATEGORÍAS                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        for (Category category : inventory.showCategory()) {
            System.out.println("║  ID de Categoría: " + category.getIdCategory());
            System.out.println("║  Nombre: " + category.getName());
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayMovementDetails(Movement movement) { // Asociación con Movement
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                DETALLES DEL MOVIMIENTO                    ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID del Movimiento: " + movement.getIdMovement());
        System.out.println("║  Tipo de Movimiento: " + movement.getMovementType());
        System.out.println("║  Estado: " + movement.getStatus());
        System.out.println("║  Fecha: " + movement.getDate());
        System.out.println("║  Descripción: " + movement.getDescription());
        System.out.println("║  Total: $" + movement.calculateTotal());
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║                   ÍTEMES DEL MOVIMIENTO                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        for (ProductMovement detail : movement.getProductMovementList()) {
            System.out.println("║  Producto: " + detail.getProduct().getName());
            System.out.println("║  Cantidad: " + detail.getQuantity());
            System.out.println("║  Precio Unitario: $" + detail.getUnitPrice());
            System.out.println("║  Subtotal: $" + detail.getSubtotal());
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displaySaleInvoiceDetails(SaleInvoice saleInvoice) { // Asociación con SaleInvoice
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 DETALLES DE FACTURA DE VENTA                 ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID de Factura: " + saleInvoice.getIdInvoice());
        System.out.println("║  Número de Factura: " + saleInvoice.getInvoiceNumber());
        System.out.println("║  Fecha de Factura: " + saleInvoice.getInvoiceDate());
        System.out.println("║  Total: $" + saleInvoice.getTotal());
        System.out.println("║  Cliente: " + saleInvoice.getCustomer().getName());
        System.out.println("║  Método de Pago: " + saleInvoice.getPaymentMethod());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displaySupplierDetails(Supplier supplier) { // Asociación con Supplier
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   DETALLES DEL PROVEEDOR                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID del Proveedor: " + supplier.getIdSupplier());
        System.out.println("║  Nombre: " + supplier.getName());
        System.out.println("║  Teléfono: " + supplier.getPhone());
        System.out.println("║  Email: " + supplier.getEmail());
        System.out.println("║  Dirección: " + supplier.getAddress());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayCustomerDetails(Customer customer) { // Asociación con Customer
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     DETALLES DEL CLIENTE                     ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID del Cliente: " + customer.getIdCustomer());
        System.out.println("║  Nombre: " + customer.getName());
        System.out.println("║  Teléfono: " + customer.getPhone());
        System.out.println("║  Email: " + customer.getEmail());
        System.out.println("║  Dirección: " + customer.getAddress());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayKardexEntry(Kardex kardex) { // Asociación con Kardex
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      ENTRADA DE KARDEX                       ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID de Kardex: " + kardex.getIdKardex());
        System.out.println("║  Producto: " + kardex.getProduct().getName());
        System.out.println("║  Fecha: " + kardex.getDate());
        System.out.println("║  Tipo de Movimiento: " + kardex.getMovementType());
        System.out.println("║  Cantidad: " + kardex.getQuantity());
        System.out.println("║  Saldo: " + kardex.getBalance());
        System.out.println("║  Descripción: " + kardex.getDescription());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayStockAlert(StockAlert alert) { // Asociación con StockAlert
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALERTA DE STOCK                          ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ID de Alerta: " + alert.getIdAlert());
        System.out.println("║  Producto: " + alert.getProduct().getName());
        System.out.println("║  Stock Actual: " + alert.getCurrentStock());
        System.out.println("║  Stock Mínimo: " + alert.getMinStock());
        System.out.println("║  Fecha de Alerta: " + alert.getAlertDate());
        System.out.println("║  Estado: " + (alert.isResolved() ? "RESUELTA" : "PENDIENTE"));
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayCategoryList(List<Category> categories) {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      CATEGORÍAS DISPONIBLES                  ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        if (categories.isEmpty()) {
            System.out.println("║  No hay categorías registradas.                              ║");
        } else {
            for (Category category : categories) {
                System.out.println("║  " + category.getName() + "                                                  ");
            }
        }
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    public void closeScanner() {
        scanner.close();
    }
}