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

    private static void adquirirProducto() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  ADQUIRIR PRODUCTO (NUEVO)                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        String name;
        while (true) {
            System.out.print("║  Nombre: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("║  Error: El nombre no puede estar vacío.");
                continue;
            }
            if (!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("║  Error: El nombre solo puede contener letras y espacios.");
                continue;
            }
            break;
        }
        
        System.out.print("║  Descripción: ");
        String description = scanner.nextLine();
        
        double purchasePrice;
        while (true) {
            System.out.print("║  Precio de Compra: ");
            purchasePrice = scanner.nextDouble();
            if (purchasePrice <= 0) {
                System.out.println("║  Error: El precio debe ser mayor que cero.");
                continue;
            }
            break;
        }
        
        double salePrice = purchasePrice * 1.50;
        System.out.println("║  Precio de Venta calculado (50% ganancia): $" + salePrice);
        
        int stock;
        while (true) {
            System.out.print("║  Stock Inicial: ");
            stock = scanner.nextInt();
            if (stock <= 0) {
                System.out.println("║  Error: El stock debe ser mayor que cero.");
                continue;
            }
            break;
        }
        
        int minStock;
        while (true) {
            System.out.print("║  Stock Mínimo: ");
            minStock = scanner.nextInt();
            if (minStock < 0) {
                System.out.println("║  Error: El stock mínimo no puede ser negativo.");
                continue;
            }
            break;
        }
        scanner.nextLine();
        
        // Mostrar categorías
        showCategories();
        
        Category category;
        while (true) {
            System.out.print("║  Ingrese el nombre de la categoría: ");
            String categoryName = scanner.nextLine();
            category = findCategoryByName(categoryName);
            
            if (category == null) {
                System.out.println("║  Categoría no encontrada. Ingrese una categoría válida.");
                continue;
            }
            
            break;
        }
        
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
            
            inventory.addProduct(product);
            category.addProduct(product);
            
            // Crear movimiento de entrada
            Movement movement = new Movement(
                movementIdCounter++,
                MovementType.ENTRY,
                new Date(),
                "Adquisición de nuevo producto: " + name
            );
            movement.addProductMovement(product, stock, purchasePrice);
            movement.processMovement();
            movements.add(movement);
            
            // Crear Kardex
            Kardex kardex = new Kardex(
                kardexIdCounter++,
                product,
                new Date(),
                MovementType.ENTRY,
                stock,
                stock,
                "Adquisición de nuevo producto"
            );
            kardexList.add(kardex);
            
            // Generar factura de compra
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  ¿Desea generar factura de compra para este movimiento?      ║");
            System.out.println("║  1. Sí                                                      ║");
            System.out.println("║  2. No                                                      ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.print("║  Seleccione una opción: ");
            
            int invoiceOption = view.getIntInput();
            
            if (invoiceOption == 1) {
                System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                  GENERAR FACTURA DE COMPRA                    ║");
                System.out.println("╠══════════════════════════════════════════════════════════════╣");
                System.out.println("║  Proveedores existentes:                                    ║");
                for (Supplier s : suppliers) {
                    System.out.println("║  ID: " + s.getIdSupplier() + " | Nombre: " + s.getName());
                }
                System.out.println("╠══════════════════════════════════════════════════════════════╣");
                System.out.println("║  1. Usar proveedor existente                                ║");
                System.out.println("║  2. Crear nuevo proveedor                                   ║");
                System.out.println("╠══════════════════════════════════════════════════════════════╣");
                System.out.print("║  Seleccione una opción: ");
                
                int supplierOption = view.getIntInput();
                Supplier supplier = null;
                
                if (supplierOption == 1) {
                    System.out.print("║  Ingrese ID del proveedor: ");
                    int supplierId = view.getIntInput();
                    supplier = findSupplierById(supplierId);
                    if (supplier == null) {
                        System.out.println("║  Proveedor no encontrado. Se creará uno nuevo.");
                        supplierOption = 2;
                    }
                }
                
                if (supplierOption == 2 || supplier == null) {
                    System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                    System.out.println("║                    CREAR NUEVO PROVEEDOR                      ║");
                    System.out.println("╠══════════════════════════════════════════════════════════════╣");
                    System.out.print("║  Nombre: ");
                    String supplierName = scanner.nextLine();
                    System.out.print("║  Teléfono: ");
                    String phone = scanner.nextLine();
                    System.out.print("║  Email: ");
                    String email = scanner.nextLine();
                    System.out.print("║  Dirección: ");
                    String address = scanner.nextLine();
                    
                    supplier = new Supplier(supplierIdCounter++, supplierName, phone, email, address);
                    suppliers.add(supplier);
                    System.out.println("║  Proveedor creado con ID: " + supplier.getIdSupplier());
                    System.out.println("╚══════════════════════════════════════════════════════════════╝");
                }
                
                PurchaseInvoice invoice = new PurchaseInvoice(
                    invoiceIdCounter++,
                    new Date(),
                    "INV-" + invoiceIdCounter,
                    supplier,
                    "PO-" + invoiceIdCounter,
                    movement
                );
                invoice.calculateTotal();
                purchaseInvoices.add(invoice);
                invoiceView.calculateTotalPurchaseInvoice(invoice);
                invoiceView.generatePurchaseInvoice(invoice);
            }
            
            System.out.println("║   Producto adquirido exitosamente con ID: " + product.getIdProduct());
            System.out.println("║   Movimiento de entrada registrado: " + movement.getIdMovement());
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            
        } catch (InvalidProductNameException | InvalidProductPriceException | InvalidProductStockException e) {
            System.out.println("║  Error: " + e.getMessage());
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
        }
    }

    private static void reabastecerProducto() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              REABASTECER PRODUCTO (EXISTENTE)                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        view.displayInventoryProductList(inventory);
        
        int productId;
        while (true) {
            System.out.print("║  Ingrese el ID del producto a reabastecer: ");
            productId = view.getIntInput();
            
            if (productId == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            Product product = findProductById(productId);
            if (product == null) {
                System.out.println("║   Producto no encontrado. Ingrese un ID válido.");
                continue;
            }
            
            break;
        }
        
        Product product = findProductById(productId);
        System.out.println("║  Producto: " + product.getName());
        System.out.println("║  Stock actual: " + product.getStock());
        
        int quantity;
        while (true) {
            System.out.print("║  Cantidad a agregar: ");
            quantity = view.getIntInput();
            if (quantity == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            if (quantity <= 0) {
                System.out.println("║  Error: La cantidad debe ser mayor que cero.");
                continue;
            }
            break;
        }
        
        // Crear movimiento de entrada
        Movement movement = new Movement(
            movementIdCounter++,
            MovementType.ENTRY,
            new Date(),
            "Reabastecimiento de producto: " + product.getName()
        );
        movement.addProductMovement(product, quantity, product.getPurchasePrice());
        movement.processMovement();
        movements.add(movement);
        
        // Crear Kardex
        Kardex kardex = new Kardex(
            kardexIdCounter++,
            product,
            new Date(),
            MovementType.ENTRY,
            quantity,
            product.getStock(),
            "Reabastecimiento"
        );
        kardexList.add(kardex);
        
        // Generar factura de compra
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿Desea generar factura de compra para este movimiento?      ║");
        System.out.println("║  1. Sí                                                      ║");
        System.out.println("║  2. No                                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.print("║  Seleccione una opción: ");
        
        int invoiceOption = view.getIntInput();
        
        if (invoiceOption == 1) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                  GENERAR FACTURA DE COMPRA                    ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  Proveedores existentes:                                    ║");
            for (Supplier s : suppliers) {
                System.out.println("║  ID: " + s.getIdSupplier() + " | Nombre: " + s.getName());
            }
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Usar proveedor existente                                ║");
            System.out.println("║  2. Crear nuevo proveedor                                   ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.print("║  Seleccione una opción: ");
            
            int supplierOption = view.getIntInput();
            Supplier supplier = null;
            
            if (supplierOption == 1) {
                System.out.print("║  Ingrese ID del proveedor: ");
                int supplierId = view.getIntInput();
                supplier = findSupplierById(supplierId);
                if (supplier == null) {
                    System.out.println("║  Proveedor no encontrado. Se creará uno nuevo.");
                    supplierOption = 2;
                }
            }
            
            if (supplierOption == 2 || supplier == null) {
                System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                    CREAR NUEVO PROVEEDOR                      ║");
                System.out.println("╠══════════════════════════════════════════════════════════════╣");
                System.out.print("║  Nombre: ");
                String supplierName = scanner.nextLine();
                System.out.print("║  Teléfono: ");
                String phone = scanner.nextLine();
                System.out.print("║  Email: ");
                String email = scanner.nextLine();
                System.out.print("║  Dirección: ");
                String address = scanner.nextLine();
                
                supplier = new Supplier(supplierIdCounter++, supplierName, phone, email, address);
                suppliers.add(supplier);
                System.out.println("║  Proveedor creado con ID: " + supplier.getIdSupplier());
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
            }
            
            PurchaseInvoice invoice = new PurchaseInvoice(
                invoiceIdCounter++,
                new Date(),
                "INV-" + invoiceIdCounter,
                supplier,
                "PO-" + invoiceIdCounter,
                movement
            );
            invoice.calculateTotal();
            purchaseInvoices.add(invoice);
            invoiceView.calculateTotalPurchaseInvoice(invoice);
            invoiceView.generatePurchaseInvoice(invoice);
        }
        
        System.out.println("║   Stock actualizado: " + product.getStock());
        System.out.println("║   Movimiento de entrada registrado: " + movement.getIdMovement());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void movimientoSalida() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  MOVIMIENTO DE SALIDA (VENTA)                ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        view.displayInventoryProductList(inventory);
        
        int productId;
        while (true) {
            System.out.print("║  Ingrese el ID del producto a vender: ");
            productId = view.getIntInput();
            
            if (productId == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            Product product = findProductById(productId);
            if (product == null) {
                System.out.println("║   Producto no encontrado. Ingrese un ID válido.");
                continue;
            }
            
            break;
        }
        
        Product product = findProductById(productId);
        System.out.println("║  Producto: " + product.getName());
        System.out.println("║  Stock actual: " + product.getStock());
        System.out.println("║  Precio de venta: $" + product.getSalePrice());
        
        int quantity;
        while (true) {
            System.out.print("║  Cantidad a vender: ");
            quantity = view.getIntInput();
            
            if (quantity == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            if (quantity <= 0) {
                System.out.println("║  Error: La cantidad debe ser mayor que cero.");
                continue;
            }
            
            if (quantity > product.getStock()) {
                System.out.println("║  Error: Stock insuficiente. Stock disponible: " + product.getStock());
                continue;
            }
            
            break;
        }
        
        // Crear movimiento de salida
        Movement movement = new Movement(
            movementIdCounter++,
            MovementType.EXIT,
            new Date(),
            "Venta de producto: " + product.getName()
        );
        movement.addProductMovement(product, quantity, product.getSalePrice());
        movement.processMovement();
        movements.add(movement);
        
        // Crear Kardex
        Kardex kardex = new Kardex(
            kardexIdCounter++,
            product,
            new Date(),
            MovementType.EXIT,
            quantity,
            product.getStock(),
            "Venta"
        );
        kardexList.add(kardex);
        
        // Generar factura de venta
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  ¿Desea generar factura de venta para este movimiento?       ║");
        System.out.println("║  1. Sí                                                      ║");
        System.out.println("║  2. No                                                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.print("║  Seleccione una opción: ");
        
        int invoiceOption = view.getIntInput();
        
        if (invoiceOption == 1) {
            System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                  GENERAR FACTURA DE VENTA                      ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  Clientes existentes:                                       ║");
            for (Customer c : customers) {
                System.out.println("║  ID: " + c.getIdCustomer() + " | Nombre: " + c.getName());
            }
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.println("║  1. Usar cliente existente                                  ║");
            System.out.println("║  2. Crear nuevo cliente                                     ║");
            System.out.println("╠══════════════════════════════════════════════════════════════╣");
            System.out.print("║  Seleccione una opción: ");
            
            int customerOption = view.getIntInput();
            Customer customer = null;
            
            if (customerOption == 1) {
                System.out.print("║  Ingrese ID del cliente: ");
                int customerId = view.getIntInput();
                customer = findCustomerById(customerId);
                if (customer == null) {
                    System.out.println("║  Cliente no encontrado. Se creará uno nuevo.");
                    customerOption = 2;
                }
            }
            
            if (customerOption == 2 || customer == null) {
                System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
                System.out.println("║                    CREAR NUEVO CLIENTE                        ║");
                System.out.println("╠══════════════════════════════════════════════════════════════╣");
                System.out.print("║  Nombre: ");
                String customerName = scanner.nextLine();
                System.out.print("║  Teléfono: ");
                String phone = scanner.nextLine();
                System.out.print("║  Email: ");
                String email = scanner.nextLine();
                System.out.print("║  Dirección: ");
                String address = scanner.nextLine();
                
                customer = new Customer(customerIdCounter++, customerName, phone, email, address);
                customers.add(customer);
                System.out.println("║  Cliente creado con ID: " + customer.getIdCustomer());
                System.out.println("╚══════════════════════════════════════════════════════════════╝");
            }
            
            System.out.print("║  Método de pago: ");
            String paymentMethod = scanner.nextLine();
            
            SaleInvoice invoice = new SaleInvoice(
                invoiceIdCounter++,
                new Date(),
                "INV-" + invoiceIdCounter,
                customer,
                paymentMethod,
                movement
            );
            invoice.calculateTotal();
            saleInvoices.add(invoice);
            invoiceView.calculateTotalSaleInvoice(invoice);
            invoiceView.generateSaleInvoice(invoice);
        }
        
        // Verificar alertas de stock
        if (product.verifyStockMinimo()) {
            StockAlert alert = product.generateStockAlert(alertIdCounter++);
            if (alert != null) {
                stockAlerts.add(alert);
                System.out.println("║    ALERTA: Stock por debajo del mínimo (" + product.getMinStock() + ")");
            }
        }
        
        double total = quantity * product.getSalePrice();
        System.out.println("║   Venta registrada exitosamente");
        System.out.println("║   Stock actualizado: " + product.getStock());
        System.out.println("║   Total de venta: $" + total);
        System.out.println("║   Movimiento de salida registrado: " + movement.getIdMovement());
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
    }

    private static void addProduct() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                      AGREGAR PRODUCTO                        ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        String name;
        while (true) {
            System.out.print("║  Nombre: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("║  Error: El nombre no puede estar vacío.");
                continue;
            }
            if (!name.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                System.out.println("║  Error: El nombre solo puede contener letras y espacios.");
                continue;
            }
            break;
        }
        
        System.out.print("║  Descripción: ");
        String description = scanner.nextLine();
        
        double purchasePrice;
        while (true) {
            System.out.print("║  Precio de Compra: ");
            purchasePrice = scanner.nextDouble();
            if (purchasePrice <= 0) {
                System.out.println("║  Error: El precio debe ser mayor que cero.");
                continue;
            }
            break;
        }
        
        double salePrice = purchasePrice * 1.50;
        System.out.println("║  Precio de Venta calculado (50% ganancia): $" + salePrice);
        
        int stock;
        while (true) {
            System.out.print("║  Stock: ");
            stock = scanner.nextInt();
            if (stock < 0) {
                System.out.println("║  Error: El stock no puede ser negativo.");
                continue;
            }
            break;
        }
        
        int minStock;
        while (true) {
            System.out.print("║  Stock Mínimo: ");
            minStock = scanner.nextInt();
            if (minStock < 0) {
                System.out.println("║  Error: El stock mínimo no puede ser negativo.");
                continue;
            }
            break;
        }
        scanner.nextLine();
        
        // Mostrar categorías
        showCategories();
        
        Category category;
        while (true) {
            System.out.print("║  Ingrese el nombre de la categoría: ");
            String categoryName = scanner.nextLine();
            category = findCategoryByName(categoryName);
            
            if (category == null) {
                System.out.println("║  Categoría no encontrada. Ingrese una categoría válida.");
                continue;
            }
            
            break;
        }
        
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
            
            inventory.addProduct(product);
            category.addProduct(product);
            
            System.out.println("║   Producto agregado exitosamente con ID: " + product.getIdProduct());
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            
        } catch (InvalidProductNameException | InvalidProductPriceException | InvalidProductStockException e) {
            System.out.println("║  Error: " + e.getMessage());
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
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
        String name;
        while (true) {
            System.out.print("Nombre del producto: ");
            name = scanner.nextLine();
            if (name.trim().isEmpty()) {
                System.out.println("Error: El nombre no puede estar vacío.");
                continue;
            }
            break;
        }
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
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  CREAR MOVIMIENTO                            ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. ENTRADA (ENTRY)                                          ║");
        System.out.println("║  2. SALIDA (EXIT)                                            ║");
        System.out.println("║  0. Volver                                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        int typeOption;
        while (true) {
            System.out.print("║  Seleccione una opción: ");
            typeOption = view.getIntInput();
            
            if (typeOption == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            if (typeOption >= 0 && typeOption <= 2) {
                break;
            }
            
            System.out.println("║  Opción no válida. Debe ser 0, 1 o 2.");
        }
        
        switch (typeOption) {
            case 1:
                handleEntryMovements();
                break;
            case 2:
                handleExitMovements();
                break;
            case 0:
                break;
        }
    }

    private static void handleEntryMovements() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                  MOVIMIENTOS DE ENTRADA                      ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  1. Adquirir Producto (nuevo)                                ║");
        System.out.println("║  2. Reabastecer Producto (existente)                         ║");
        System.out.println("║  0. Volver                                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        int option;
        while (true) {
            System.out.print("║  Seleccione una opción: ");
            option = view.getIntInput();
            
            if (option == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            if (option >= 0 && option <= 2) {
                break;
            }
            
            System.out.println("║  Opción no válida. Debe ser 0, 1 o 2.");
        }
        
        switch (option) {
            case 1:
                adquirirProducto();
                break;
            case 2:
                reabastecerProducto();
                break;
            case 0:
                break;
        }
    }

    private static void handleExitMovements() {
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                 MOVIMIENTOS DE SALIDA (VENTAS)               ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        System.out.println("║  Registrar venta de productos                                ║");
        System.out.println("║  0. Volver                                                   ║");
        System.out.println("╠══════════════════════════════════════════════════════════════╣");
        
        int option;
        while (true) {
            System.out.print("║  Seleccione una opción: ");
            option = view.getIntInput();
            
            if (option == -1) {
                System.out.println("║  Error: Debe ingresar un número.");
                continue;
            }
            
            if (option >= 0 && option <= 1) {
                break;
            }
            
            System.out.println("║  Opción no válida. Debe ser 0 o 1.");
        }
        
        switch (option) {
            case 1:
                movimientoSalida();
                break;
            case 0:
                break;
        }
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
