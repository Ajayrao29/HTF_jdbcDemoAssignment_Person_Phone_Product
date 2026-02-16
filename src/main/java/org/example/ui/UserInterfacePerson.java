package org.example.ui;

import org.example.dao.PersonDao;
import org.example.dao.PersonDaoImpl;
import org.example.entity.Person;

import java.util.List;
import java.util.Scanner;

public class UserInterfacePerson {
    private PersonDao personDao = new PersonDaoImpl();
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n-----Person Management System ----");
            System.out.println("1. View All Persons");
            System.out.println("2. Find Person by ID");
            System.out.println("3. Search by Name");
            System.out.println("4. Search by Name Containing");
            System.out.println("5. View Sorted by First Name");
            System.out.println("6. Add Person");
            System.out.println("7. Delete Person");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewAll(); break;
                case 2: findById(); break;
                case 3: searchByName(); break;
                case 4: searchByNameContaining(); break;
                case 5: viewSorted(); break;
                case 6: addPerson(); break;
                case 7: deletePerson(); break;
                case 0: System.out.println("Goodbye!"); return;
                default: System.out.println("Invalid option");
            }
        }
    }

    private void viewAll() {
        List<Person> persons = personDao.findAll();
        persons.forEach(System.out::println);
    }

    private void findById() {
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        Person person = personDao.findById(id);
        System.out.println(person != null ? person : "Not found");
    }

    private void searchByName() {
        System.out.print("Enter first name: ");
        String fname = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lname = scanner.nextLine();
        List<Person> persons = personDao.findByName(fname, lname);
        persons.forEach(System.out::println);
    }

    private void searchByNameContaining() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Person> persons = personDao.findByFnameContaining(name);
        persons.forEach(System.out::println);
    }

    private void viewSorted() {
        List<Person> persons = personDao.findSortedByFname();
        persons.forEach(System.out::println);
    }

    private void addPerson() {
        System.out.print("First name: ");
        String fname = scanner.nextLine();
        System.out.print("Last name: ");
        String lname = scanner.nextLine();
        personDao.addPerson(fname, lname);
        System.out.println("Person added");
    }

    private void deletePerson() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        personDao.deleteById(id);
        System.out.println("Person deleted");
    }
}
