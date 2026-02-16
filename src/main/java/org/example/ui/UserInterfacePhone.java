package org.example.ui;

import org.example.dao.PhoneDao;
import org.example.dao.PhoneDaoImpl;
import org.example.entity.Phone;

import java.util.List;
import java.util.Scanner;

public class UserInterfacePhone {
    private final PhoneDao phoneDao = new PhoneDaoImpl();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n----- Phone Management System -----");
            System.out.println("1. View All Phones");
            System.out.println("2. Find Phone by ID");
            System.out.println("3. Search by Brand/Model");
            System.out.println("4. Add Phone");
            System.out.println("5. Update Phone");
            System.out.println("6. Delete Phone");
            System.out.println("7. Sort Phones");
            System.out.println("8. Find by Brand");
            System.out.println("9. Find by Model");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> viewAll();
                case 2 -> findById();
                case 3 -> search();
                case 4 -> addPhone();
                case 5 -> updatePhone();
                case 6 -> deletePhone();
                case 7 -> sortMenu();
                case 8 -> findByBrand();
                case 9 -> findByModel();
                case 0 -> { System.out.println("Goodbye!"); return; }
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void viewAll() {
        List<Phone> phones = phoneDao.findAll();
        phones.forEach(System.out::println);
    }

    private void findById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Phone phone = phoneDao.findById(id);
        System.out.println(phone != null ? phone : "Not found");
    }

    private void search() {
        System.out.print("Enter brand/model text: ");
        String text = scanner.nextLine();
        phoneDao.findByBrandContaining(text).forEach(System.out::println);
    }

    private void addPhone() {
        System.out.print("Brand: ");
        String brand = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Release Date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();

        phoneDao.addPhone(brand, model, price, releaseDate);
        System.out.println("Phone added");
    }

    private void deletePhone() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        phoneDao.deleteById(id);
        System.out.println("Phone deleted");
    }

    private void updatePhone() {
        System.out.print("Enter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("New Brand: ");
        String brand = scanner.nextLine();
        System.out.print("New Model: ");
        String model = scanner.nextLine();
        System.out.print("New Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        phoneDao.updatePhone(id, brand, model, price);
        System.out.println("Phone updated");
    }

    private void sortMenu() {
        System.out.println("\n--- Sort By ---");
        System.out.println("1. Price (Asc)");
        System.out.println("2. Price (Desc)");
        System.out.println("3. Brand (Asc)");
        System.out.println("4. Brand (Desc)");
        System.out.println("5. Model (Asc)");
        System.out.println("6. Model (Desc)");
        System.out.println("7. Release Date");
        System.out.print("Choose: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        List<Phone> phones = switch (choice) {
            case 1 -> phoneDao.findAllOrderByPrice();
            case 2 -> phoneDao.findAllOrderByPriceDesc();
            case 3 -> phoneDao.findAllOrderByBrand();
            case 4 -> phoneDao.findAllOrderByBrandDesc();
            case 5 -> phoneDao.findAllOrderByModel();
            case 6 -> phoneDao.findAllOrderByModelDesc();
            case 7 -> phoneDao.findAllOrderByReleaseDate();
            default -> { System.out.println("Invalid"); yield List.of(); }
        };
        phones.forEach(System.out::println);
    }

    private void findByBrand() {
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        phoneDao.findByBrand(brand).forEach(System.out::println);
    }

    private void findByModel() {
        System.out.print("Enter model: ");
        String model = scanner.nextLine();
        phoneDao.findByModel(model).forEach(System.out::println);
    }
}
