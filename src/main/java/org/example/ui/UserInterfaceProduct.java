package org.example.ui;

import org.example.dao.ProductDao;
import org.example.dao.ProductDaoImpl;
import org.example.entity.Product;

import java.util.List;
import java.util.Scanner;

public class UserInterfaceProduct {
    private final ProductDao productDao = new ProductDaoImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n----- Product Management System -----");
            System.out.println("1. View All Products");
            System.out.println("2. Find Product by ID");
            System.out.println("3. Search by Name/Category");
            System.out.println("4. Add Product");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Sort Products");
            System.out.println("8. Find by Name");
            System.out.println("9. Find by Category");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAll();
                case 2 -> findById();
                case 3 -> search();
                case 4 -> addProduct();
                case 5 -> updateProduct();
                case 6 -> deleteProduct();
                case 7 -> sortMenu();
                case 8 -> findByName();
                case 9 -> findByCategory();
                case 0 -> { System.out.println("EXIT"); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void viewAll() {
        List<Product> products = productDao.findAll();
        products.forEach(System.out::println);
    }

    private void findById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Product product = productDao.findById(id);
        System.out.println(product != null ? product : "Not found");
    }

    private void search() {
        System.out.print("Enter name/category text: ");
        String text = scanner.nextLine();
        productDao.findByNameContaining(text).forEach(System.out::println);
    }

    private void addProduct() {
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        productDao.addProduct(name, category, price);
        System.out.println("Product added");
    }

    private void updateProduct() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Name: ");
        String name = scanner.nextLine();
        System.out.print("New Category: ");
        String category = scanner.nextLine();
        System.out.print("New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        productDao.updateProduct(id, name, category, price);
        System.out.println("Product updated");
    }

    private void deleteProduct() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        productDao.deleteById(id);
        System.out.println("Product deleted");
    }

    private void sortMenu() {
        System.out.println("\n--- Sort By ---");
        System.out.println("1. Price (Asc)");
        System.out.println("2. Price (Desc)");
        System.out.println("3. Name (Asc)");
        System.out.println("4. Name (Desc)");
        System.out.println("5. Category (Asc)");
        System.out.println("6. Category (Desc)");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Product> products = switch (choice) {
            case 1 -> productDao.findAllOrderByPrice();
            case 2 -> productDao.findAllOrderByPriceDesc();
            case 3 -> productDao.findAllOrderByName();
            case 4 -> productDao.findAllOrderByNameDesc();
            case 5 -> productDao.findAllOrderByCategory();
            case 6 -> productDao.findAllOrderByCategoryDesc();
            default -> { System.out.println("Invalid"); yield List.of(); }
        };
        products.forEach(System.out::println);
    }

    private void findByName() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        productDao.findByName(name).forEach(System.out::println);
    }

    private void findByCategory() {
        System.out.print("Enter category: ");
        String category = scanner.nextLine();
        productDao.findByCategory(category).forEach(System.out::println);
    }
}
