package edu.unl.cc;

import edu.unl.cc.jbrew.Domain.Inventory.Inventory;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Inventory.Category;
import edu.unl.cc.jbrew.Domain.Movements.Movement;
import edu.unl.cc.jbrew.Domain.Movements.MovementType;
import edu.unl.cc.jbrew.Domain.Movements.ProductMovement;
import edu.unl.cc.jbrew.Domain.Invoice.SaleInvoice;
import edu.unl.cc.jbrew.Domain.People.Supplier;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;
import edu.unl.cc.jbrew.Domain.Reports.Report;
import edu.unl.cc.jbrew.View.View;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory();
    private static View view = new View();
    private static List<Movement> movements = new ArrayList<>();
    private static List<SaleInvoice> saleInvoices = new ArrayList<>();
    private static List<Supplier> suppliers = new ArrayList<>();
    private static List<Customer> customers = new ArrayList<>();
    private static List<Kardex> kardexList = new ArrayList<>();
    private static List<StockAlert> stockAlerts = new ArrayList<>();
    private static int productIdCounter = 1;
    private static int categoryIdCounter = 1;
    private static int supplierIdCounter = 1;
    private static int customerIdCounter = 1;
    private static int movementIdCounter = 1;
    private static int kardexIdCounter = 1;
    private static int alertIdCounter = 1;
    private static int invoiceIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE INVENTARIO =====");
            System.out.println("1. Agregar Categoría");
            System.out.println("2. Agregar Producto");
            System.out.println("3. Ver Productos");
            System.out.println("4. Ver Categorías");
            System.out.println("5. Buscar Producto");
            System.out.println("6. Crear Movimiento (Entrada/Salida)");
            System.out.println("7. Registrar Venta");
            System.out.println("8. Agregar Proveedor");
            System.out.println("9. Agregar Cliente");
            System.out.println("10. Ver Movimientos");
            System.out.println("11. Ver Kardex");
            System.out.println("12. Ver Alertas de Stock");
            System.out.println("13. Generar Reportes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    addCategory();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    view.displayInventoryProductList(inventory);
                    break;
                case 4:
                    showCategories();
                    break;
                case 5:
                    searchProduct();
                    break;
                case 6:
                    createMovement();
                    break;
                case 7:
                    registerSale();
                    break;
                case 8:
                    addSupplier();
                    break;
                case 9:
                    addCustomer();
                    break;
                case 10:
                    viewMovements();
                    break;
                case 11:
                    viewKardex();
                    break;
                case 12:
                    viewStockAlerts();
                    break;
                case 13:
                    generateReports();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    private static void addCategory() {
        System.out.println("\n--- AGREGAR CATEGORÍA ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();

        Category category = new Category(categoryIdCounter++, description, name);
        inventory.addCategory(category);
        System.out.println("Categoría agregada exitosamente.");
    }

    private static void addProduct() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        System.out.print("Precio de Venta: ");
        double salePrice = scanner.nextDouble();
        System.out.print("Precio de Compra: ");
        double purchasePrice = scanner.nextDouble();
        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        System.out.print("Stock Mínimo: ");
        int minStock = scanner.nextInt();
        scanner.nextLine();

        Product product = new Product(productIdCounter++, name, description, salePrice, purchasePrice, stock, minStock);
        inventory.addProduct(product);
        
        // Check for stock alert
        if (product.verifyStockMinimo()) {
            StockAlert alert = product.generateStockAlert(alertIdCounter++);
            if (alert != null) {
                stockAlerts.add(alert);
                System.out.println("Alerta de stock generada para el producto.");
            }
        }
        
        System.out.println("Producto agregado exitosamente.");
    }

    private static void showCategories() {
        System.out.println("\n--- CATEGORÍAS ---");
        for (Category category : inventory.showCategory()) {
            System.out.println("ID: " + category.getIdCategory() + " | Nombre: " + category.getName() + " | Descripción: " + category.getDescription());
        }
    }

    private static void searchProduct() {
        System.out.println("\n--- BUSCAR PRODUCTO ---");
        System.out.print("Nombre del producto: ");
        String name = scanner.nextLine();
        Product product = inventory.searchProduct(name);
        if (product != null) {
            System.out.println("Producto encontrado:");
            System.out.println("ID: " + product.getIdProduct());
            System.out.println("Nombre: " + product.getName());
            System.out.println("Stock: " + product.getStock());
            System.out.println("Precio: " + product.getSalePrice());
        } else {
            System.out.println("Producto no encontrado.");
        }
    }

    private static void createMovement() {
        System.out.println("\n--- CREAR MOVIMIENTO ---");
        System.out.println("Tipo de movimiento:");
        System.out.println("1. ENTRADA (ENTRY)");
        System.out.println("2. SALIDA (EXIT)");
        System.out.print("Seleccione: ");
        int typeOption = scanner.nextInt();
        scanner.nextLine();

        MovementType type = (typeOption == 1) ? MovementType.ENTRY : MovementType.EXIT;

        Movement movement = new Movement(movementIdCounter++, type, new Date(), "");

        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        movement.setDescription(description);

        System.out.println("Agregar productos al movimiento (0 para terminar):");
        while (true) {
            System.out.print("ID del producto: ");
            int productId = scanner.nextInt();
            if (productId == 0) break;

            Product product = findProductById(productId);
            if (product == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Cantidad: ");
            int quantity = scanner.nextInt();
            System.out.print("Precio unitario: ");
            double unitPrice = scanner.nextDouble();
            scanner.nextLine();

            movement.addProductMovement(product, quantity, unitPrice);
            System.out.println("Producto agregado al movimiento.");
        }

        movement.processMovement();
        movements.add(movement);
        
        // Create Kardex entry
        for (ProductMovement pm : movement.getProductMovementList()) {
            Kardex kardex = new Kardex(
                kardexIdCounter++,
                pm.getProduct(),
                new Date(),
                movement.getMovementType(),
                pm.getQuantity(),
                pm.getProduct().getStock(),
                movement.getDescription()
            );
            kardexList.add(kardex);
        }
        
        // Check for stock alerts
        for (ProductMovement pm : movement.getProductMovementList()) {
            if (pm.getProduct().verifyStockMinimo()) {
                StockAlert alert = pm.getProduct().generateStockAlert(alertIdCounter++);
                if (alert != null) {
                    stockAlerts.add(alert);
                    System.out.println("Alerta de stock generada para: " + pm.getProduct().getName());
                }
            }
        }
        
        view.displayMovementDetails(movement);
    }

    private static void registerSale() {
        System.out.println("\n--- REGISTRAR VENTA ---");
        System.out.print("ID del cliente: ");
        int customerId = scanner.nextInt();
        scanner.nextLine();
        
        Customer customer = findCustomerById(customerId);
        if (customer == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }
        
        System.out.print("Total de la venta: ");
        double total = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Método de pago: ");
        String paymentMethod = scanner.nextLine();

        SaleInvoice saleInvoice = new SaleInvoice(invoiceIdCounter++, new Date(), "INV-" + invoiceIdCounter, customer, paymentMethod, null);
        saleInvoice.setTotal(total);
        saleInvoices.add(saleInvoice);
        view.displaySaleInvoiceDetails(saleInvoice);
        System.out.println("Venta registrada exitosamente.");
    }

    private static void addSupplier() {
        System.out.println("\n--- AGREGAR PROVEEDOR ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        Supplier supplier = new Supplier(supplierIdCounter++, name, phone, email, address);
        suppliers.add(supplier);
        System.out.println("Proveedor agregado exitosamente con ID: " + supplier.getIdSupplier());
    }

    private static void generateReports() {
        System.out.println("\n--- GENERAR REPORTES ---");
        Report report = new Report(1, "General Report", new Date());
        report.generateReportStock();
        report.generateReportSale();
        report.generateReportMovements();
        report.consultKardex(kardexList);
        report.consultStockAlerts(stockAlerts);
        report.exportReport();
    }

    private static void addCustomer() {
        System.out.println("\n--- AGREGAR CLIENTE ---");
        System.out.print("Nombre: ");
        String name = scanner.nextLine();
        System.out.print("Teléfono: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Dirección: ");
        String address = scanner.nextLine();

        Customer customer = new Customer(customerIdCounter++, name, phone, email, address);
        customers.add(customer);
        view.displayCustomerDetails(customer);
        System.out.println("Cliente agregado exitosamente.");
    }

    private static void viewMovements() {
        System.out.println("\n--- MOVIMIENTOS ---");
        for (Movement movement : movements) {
            view.displayMovementDetails(movement);
        }
    }

    private static void viewKardex() {
        System.out.println("\n--- KARDEX ---");
        for (Kardex kardex : kardexList) {
            view.displayKardexEntry(kardex);
        }
    }

    private static void viewStockAlerts() {
        System.out.println("\n--- ALERTAS DE STOCK ---");
        for (StockAlert alert : stockAlerts) {
            view.displayStockAlert(alert);
        }
    }

    private static Customer findCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getIdCustomer() == id) {
                return customer;
            }
        }
        return null;
    }

    private static Product findProductById(int id) {
        for (Product product : inventory.showProduct()) {
            if (product.getIdProduct() == id) {
                return product;
            }
        }
        return null;
    }
}
