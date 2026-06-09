package edu.unl.cc;

import edu.unl.cc.jbrew.Domain.Exception.InvalidCategoryNameException;
import edu.unl.cc.jbrew.Domain.Exception.InvalidProductNameException;
import edu.unl.cc.jbrew.Domain.Exception.InvalidProductPriceException;
import edu.unl.cc.jbrew.Domain.Exception.InvalidProductStockException;
import edu.unl.cc.jbrew.Domain.Inventory.Inventory;
import edu.unl.cc.jbrew.Domain.Inventory.Product;
import edu.unl.cc.jbrew.Domain.Inventory.Category;
import edu.unl.cc.jbrew.Domain.Movements.Movement;
import edu.unl.cc.jbrew.Domain.Movements.MovementType;
import edu.unl.cc.jbrew.Domain.Movements.ProductMovement;
import edu.unl.cc.jbrew.Domain.Invoice.SaleInvoice;
import edu.unl.cc.jbrew.Domain.Invoice.PurchaseInvoice;
import edu.unl.cc.jbrew.Domain.People.Supplier;
import edu.unl.cc.jbrew.Domain.People.Customer;
import edu.unl.cc.jbrew.Domain.Kardex.Kardex;
import edu.unl.cc.jbrew.Domain.Reports.StockAlert;
import edu.unl.cc.jbrew.View.View;
import edu.unl.cc.jbrew.View.MovementView;
import edu.unl.cc.jbrew.View.StockAlertView;
import edu.unl.cc.jbrew.View.KardexView;
import edu.unl.cc.jbrew.View.ReportView;
import edu.unl.cc.jbrew.View.InvoiceView;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static Scanner scanner = new Scanner(System.in); // Asociación con Scanner
    private static Inventory inventory = new Inventory(); // Composición con Inventory
    private static View view = new View(); // Composición con View
    private static MovementView movementView = new MovementView(); // Composición con MovementView
    private static StockAlertView stockAlertView = new StockAlertView(); // Composición con StockAlertView
    private static KardexView kardexView = new KardexView(); // Composición con KardexView
    private static ReportView reportView = new ReportView(); // Composición con ReportView
    private static InvoiceView invoiceView = new InvoiceView(); // Composición con InvoiceView
    private static List<Movement> movements = new ArrayList<>(); // Composición con Movement
    private static List<SaleInvoice> saleInvoices = new ArrayList<>(); // Composición con SaleInvoice
    private static List<PurchaseInvoice> purchaseInvoices = new ArrayList<>(); // Composición con PurchaseInvoice
    private static List<Supplier> suppliers = new ArrayList<>(); // Composición con Supplier
    private static List<Customer> customers = new ArrayList<>(); // Composición con Customer
    private static List<Kardex> kardexList = new ArrayList<>(); // Composición con Kardex
    private static List<StockAlert> stockAlerts = new ArrayList<>(); // Composición con StockAlert
    private static int productIdCounter = 1;
    private static int categoryIdCounter = 1;
    private static int supplierIdCounter = 1;
    private static int customerIdCounter = 1;
    private static int movementIdCounter = 1;
    private static int kardexIdCounter = 1;
    private static int alertIdCounter = 1;
    private static int invoiceIdCounter = 1;

    public static void main(String[] args) {
        initializeSampleData();
        while (true) {
            view.displayMenu();
            int option = view.getIntInput();

            if (option == -1) {
                System.out.println("Opción no válida. Debe ingresar un número.");
                continue;
            }

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
                    viewMovements();
                    break;
                case 8:
                    viewKardex();
                    break;
                case 9:
                    viewStockAlerts();
                    break;
                case 10:
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

        try{
        Category category = new Category(categoryIdCounter++, name);
        inventory.addCategory(category);
        System.out.println("Categoría agregada exitosamente.");
        }
        catch (InvalidCategoryNameException e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }
    }

    private static void addProduct() {
        System.out.println("\n--- AGREGAR PRODUCTO ---");
        String name;

        while (true) {
            System.out.print("Nombre: ");
            name = scanner.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                continue;
            }

            if (!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("Error: El nombre solo puede contener letras y espacios.");
                continue;
            }

            break;
        }
        System.out.print("Descripción: ");
        String description = scanner.nextLine();
        double salePrice=0;

        double purchasePrice;

        while (true) {
            System.out.print("Precio de Compra: ");

            purchasePrice = scanner.nextDouble();

            if (purchasePrice <= 0) {
                System.out.println("Error: El precio debe ser mayor que cero.");
                continue;
            }

            break;
        }

        salePrice = purchasePrice * 1.50;

        System.out.println("Precio de Venta calculado (50% ganancia): $" + salePrice);

        int stock;

        while (true) {
            System.out.print("Stock: ");

            stock = scanner.nextInt();

            if (stock < 0) {
                System.out.println("Error: El stock no puede ser negativo.");
                continue;
            }

            break;
        }
        int minStock;

        while (true) {
            System.out.print("Stock Mínimo: ");

            minStock = scanner.nextInt();

            if (minStock < 0) {
                System.out.println("Error: El stock mínimo no puede ser negativo.");
                continue;
            }

            break;
        }
        scanner.nextLine();

        try {
            Product product = new Product(
                    productIdCounter++,
                    name,
                    description,
                    salePrice,
                    purchasePrice,
                    stock,
                    minStock
            );

            // Mostrar categorías
            showCategories();

            System.out.print("Ingrese el nombre de la categoría: ");
            String categoryName = scanner.nextLine();

            Category category = findCategoryByName(categoryName);

            if (category == null) {
                System.out.println("Categoría no encontrada. Debe crear la categoría primero.");
                return;
            }

            inventory.addProduct(product);
            category.addProduct(product);

            System.out.println("Producto agregado exitosamente a la categoría: " + category.getName());

        } catch (InvalidProductNameException |
                 InvalidProductPriceException |
                 InvalidProductStockException e) {

            System.out.println("Error: " + e.getMessage());

        }
    }

    private static Category findCategoryByName(String name) {

        for (Category category : inventory.showCategory()) {

            if (category.getName().equalsIgnoreCase(name)) {
                return category;
            }

        }

        return null;
    }

    private static void showCategories() {
        view.displayCategoryList(inventory.showCategory());
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
            // Show available products
            System.out.println("\n--- PRODUCTOS DISPONIBLES ---");
            for (Product p : inventory.showProduct()) {
                System.out.println("ID: " + p.getIdProduct() + " | Nombre: " + p.getName() + " | Stock: " + p.getStock() + " | Precio: $" + p.getSalePrice());
            }
            
            System.out.print("\nID del producto: ");
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
        
        // Generate invoice based on movement type
        System.out.println("\n¿Desea generar factura para este movimiento?");
        System.out.println("1. Sí");
        System.out.println("2. No");
        System.out.print("Seleccione: ");
        int invoiceOption = scanner.nextInt();
        scanner.nextLine();
        
        if (invoiceOption == 1) {
            if (movement.getMovementType() == MovementType.ENTRY) {
                System.out.println("\n--- GENERAR FACTURA DE COMPRA ---");
                System.out.println("Proveedores existentes:");
                for (Supplier s : suppliers) {
                    System.out.println("ID: " + s.getIdSupplier() + " | Nombre: " + s.getName());
                }
                System.out.println("\n1. Usar proveedor existente");
                System.out.println("2. Crear nuevo proveedor");
                System.out.print("Seleccione: ");
                int supplierOption = scanner.nextInt();
                scanner.nextLine();
                
                Supplier supplier = null;
                if (supplierOption == 1) {
                    System.out.print("Ingrese ID del proveedor: ");
                    int supplierId = scanner.nextInt();
                    scanner.nextLine();
                    supplier = findSupplierById(supplierId);
                    if (supplier == null) {
                        System.out.println("Proveedor no encontrado. Se creará uno nuevo.");
                        supplierOption = 2;
                    }
                }
                
                if (supplierOption == 2 || supplier == null) {
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String address = scanner.nextLine();
                    
                    supplier = new Supplier(supplierIdCounter++, name, phone, email, address);
                    suppliers.add(supplier);
                    System.out.println("Proveedor creado con ID: " + supplier.getIdSupplier());
                }
                
                PurchaseInvoice invoice = new PurchaseInvoice( // new instanciación
                    invoiceIdCounter++,
                    new Date(),
                    "INV-" + invoiceIdCounter,
                    supplier,
                    "PO-" + invoiceIdCounter,
                    movement
                );
                invoiceView.calculateTotalPurchaseInvoice(invoice);
                purchaseInvoices.add(invoice);
                movement.generateInvoice(invoice);
                invoiceView.generatePurchaseInvoice(invoice);
                
            } else {
                System.out.println("\n--- GENERAR FACTURA DE VENTA ---");
                System.out.println("Clientes existentes:");
                for (Customer c : customers) {
                    System.out.println("ID: " + c.getIdCustomer() + " | Nombre: " + c.getName());
                }
                System.out.println("\n1. Usar cliente existente");
                System.out.println("2. Crear nuevo cliente");
                System.out.print("Seleccione: ");
                int customerOption = scanner.nextInt();
                scanner.nextLine();
                
                Customer customer = null;
                if (customerOption == 1) {
                    System.out.print("Ingrese ID del cliente: ");
                    int customerId = scanner.nextInt();
                    scanner.nextLine();
                    customer = findCustomerById(customerId);
                    if (customer == null) {
                        System.out.println("Cliente no encontrado. Se creará uno nuevo.");
                        customerOption = 2;
                    }
                }
                
                if (customerOption == 2 || customer == null) {
                    System.out.print("Nombre: ");
                    String name = scanner.nextLine();
                    System.out.print("Teléfono: ");
                    String phone = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Dirección: ");
                    String address = scanner.nextLine();
                    
                    customer = new Customer(customerIdCounter++, name, phone, email, address);
                    customers.add(customer);
                    System.out.println("Cliente creado con ID: " + customer.getIdCustomer());
                }
                
                System.out.print("Método de pago: ");
                String paymentMethod = scanner.nextLine();
                
                SaleInvoice invoice = new SaleInvoice( // new instanciación
                    invoiceIdCounter++,
                    new Date(),
                    "INV-" + invoiceIdCounter,
                    customer,
                    paymentMethod,
                    movement
                );
                invoiceView.calculateTotalSaleInvoice(invoice);
                saleInvoices.add(invoice);
                movement.generateInvoice(invoice);
                invoiceView.generateSaleInvoice(invoice);
            }
        }
        
        movementView.showMovement(movement);
    }

    private static void generateReports() {
        System.out.println("\n--- GENERAR REPORTES ---");
        
        edu.unl.cc.jbrew.Domain.Reports.Report report = new edu.unl.cc.jbrew.Domain.Reports.Report(
            1, "General", new Date()
        );
        
        reportView.generateReportStock(report);
        System.out.println("\n===== REPORTE DE STOCK =====");
        System.out.println("Total de productos: " + inventory.showProduct().size());
        int totalStock = 0;
        int lowStockCount = 0;
        for (Product p : inventory.showProduct()) {
            totalStock += p.getStock();
            if (p.verifyStockMinimo()) {
                lowStockCount++;
            }
        }
        System.out.println("Stock total: " + totalStock);
        
        System.out.println("Productos con stock bajo: " + lowStockCount);
        
        // Reporte de Ventas
        reportView.generateReportSale(report);
        System.out.println("\n===== REPORTE DE VENTAS =====");
        System.out.println("Total de facturas de venta: " + saleInvoices.size());
        double totalSales = 0;
        for (SaleInvoice invoice : saleInvoices) {
            totalSales += invoice.getTotal();
        }
        System.out.println("Total de ventas: $" + totalSales);
        
        // Reporte de Compras
        System.out.println("\n===== REPORTE DE COMPRAS =====");
        System.out.println("Total de facturas de compra: " + purchaseInvoices.size());
        double totalPurchases = 0;
        for (PurchaseInvoice invoice : purchaseInvoices) {
            totalPurchases += invoice.getTotal();
        }
        System.out.println("Total de compras: $" + totalPurchases);
        
        // Reporte de Movimientos
        reportView.generateReportMovements(report);
        System.out.println("\n===== REPORTE DE MOVIMIENTOS =====");
        System.out.println("Total de movimientos: " + movements.size());
        int entryCount = 0;
        int exitCount = 0;
        for (Movement m : movements) {
            if (m.getMovementType() == MovementType.ENTRY) {
                entryCount++;
            } else {
                exitCount++;
            }
        }
        System.out.println("Entradas: " + entryCount);
        System.out.println("Salidas: " + exitCount);
        
        // Kardex
        reportView.consultKardex(report, kardexList);
        
        System.out.println("\n===== ALERTAS DE STOCK =====");
        reportView.consultStockAlerts(report, stockAlerts);
        
        reportView.exportReport(report);
        System.out.println("\nReporte generado exitosamente.");
    }

    private static Supplier findSupplierById(int id) {
        for (Supplier supplier : suppliers) {
            if (supplier.getIdSupplier() == id) {
                return supplier;
            }
        }
        return null;
    }

    private static void viewMovements() {
        System.out.println("\n--- MOVIMIENTOS ---");
        for (Movement movement : movements) {
            movementView.showMovement(movement);
        }
    }

    private static void viewKardex() {
        System.out.println("\n--- KARDEX ---");
        for (Kardex kardex : kardexList) {
            kardexView.showKardexEntry(kardex);
        }
    }

    private static void viewStockAlerts() {
        System.out.println("\n--- ALERTAS DE STOCK ---");
        for (StockAlert alert : stockAlerts) {
            stockAlertView.showAlert(alert);
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

    private static void initializeSampleData() {
        try {
            // Crear categorías de ejemplo
            Category bebidas = new Category(categoryIdCounter++, "Bebidas");
            Category alimentos = new Category(categoryIdCounter++, "Alimentos");
            Category limpieza = new Category(categoryIdCounter++, "Limpieza");
            
            inventory.addCategory(bebidas);
            inventory.addCategory(alimentos);
            inventory.addCategory(limpieza);
            
            // Crear productos de ejemplo
            Product cocaCola = new Product(productIdCounter++, "Coca Cola", "Refresco de cola", 2.25, 1.5, 50, 10);
            Product pepsi = new Product(productIdCounter++, "Pepsi", "Refresco de cola", 2.25, 1.5, 45, 10);
            Product agua = new Product(productIdCounter++, "Agua", "Agua mineral", 1.0, 0.6, 100, 20);
            Product pan = new Product(productIdCounter++, "Pan", "Pan de molde", 3.0, 2.0, 30, 5);
            Product leche = new Product(productIdCounter++, "Leche", "Leche entera", 2.5, 1.8, 40, 10);
            Product jabon = new Product(productIdCounter++, "Jabón", "Jabón líquido", 4.0, 2.5, 25, 5);
            
            // Agregar productos a sus categorías
            bebidas.addProduct(cocaCola);
            bebidas.addProduct(pepsi);
            bebidas.addProduct(agua);
            alimentos.addProduct(pan);
            alimentos.addProduct(leche);
            limpieza.addProduct(jabon);
            
            // Agregar productos al inventario
            inventory.addProduct(cocaCola);
            inventory.addProduct(pepsi);
            inventory.addProduct(agua);
            inventory.addProduct(pan);
            inventory.addProduct(leche);
            inventory.addProduct(jabon);
            
            // Actualizar contadores
            productIdCounter = 7;
            categoryIdCounter = 4;
            
        } catch (Exception e) {
            System.out.println("Error al cargar datos de ejemplo: " + e.getMessage());
        }
    }
}
