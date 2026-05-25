package edu.unl.cc;

import edu.unl.cc.jbrew.Domain.*;
import edu.unl.cc.jbrew.View.View;
import java.util.Date;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventory inventory = new Inventory();
    private static View view = new View();
    private static int productIdCounter = 1;
    private static int categoryIdCounter = 1;
    private static int supplierIdCounter = 1;
    private static int movementIdCounter = 1;
    private static int saleIdCounter = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== SISTEMA DE GESTIÓN DE INVENTARIO =====");
            System.out.println("1. Agregar Categoría");
            System.out.println("2. Agregar Producto");
            System.out.println("3. Ver Productos");
            System.out.println("4. Ver Categorías");
            System.out.println("5. Buscar Producto");
            System.out.println("6. Crear Movimiento (Venta/Reabastecimiento)");
            System.out.println("7. Registrar Venta");
            System.out.println("8. Agregar Proveedor");
            System.out.println("9. Generar Reportes");
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
        scanner.nextLine();

        Product product = new Product(productIdCounter++, name, description, salePrice, purchasePrice, stock);
        inventory.addProduct(product);
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
        System.out.println("1. VENTA (SALE)");
        System.out.println("2. REABASTECIMIENTO (RESTOCK)");
        System.out.print("Seleccione: ");
        int typeOption = scanner.nextInt();
        scanner.nextLine();

        MovementType type = (typeOption == 1) ? MovementType.SALE : MovementType.RESTOCK;

        Movement movement = new Movement();
        movement.setIdMovement(movementIdCounter++);
        movement.setMovementType(type);
        movement.setDate(new Date());

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

            movement.addItem(product, quantity, unitPrice);
            System.out.println("Producto agregado al movimiento.");
        }

        movement.processMovement();
        view.displayMovementDetails(movement);
    }

    private static void registerSale() {
        System.out.println("\n--- REGISTRAR VENTA ---");
        System.out.print("Total de la venta: ");
        double total = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Método de pago: ");
        String paymentMethod = scanner.nextLine();

        Sale sale = new Sale(saleIdCounter++, new Date(), total, paymentMethod);
        view.displaySaleDetails(sale);
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
        view.displaySupplierDetails(supplier);
        System.out.println("Proveedor agregado exitosamente.");
    }

    private static void generateReports() {
        System.out.println("\n--- GENERAR REPORTES ---");
        Report report = new Report(1, "General Report", new Date());
        report.generateReportStock();
        report.generateReportSale();
        report.generateReportMovements();
        report.exportReport();
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
