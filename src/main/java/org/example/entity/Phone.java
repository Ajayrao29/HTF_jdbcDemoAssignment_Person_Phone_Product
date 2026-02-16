package org.example.entity;

import java.time.LocalDate;

public class Phone {
    private int id;
    private String brand;
    private String model;
    private double price;
    private LocalDate releaseDate;

    public Phone() {}

    public Phone(int id, String brand, String model, double price) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
    }

    public Phone(int id, String brand, String model, double price, LocalDate releaseDate) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.releaseDate = releaseDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public LocalDate getReleaseDate() { return releaseDate; }
    public void setReleaseDate(LocalDate releaseDate) { this.releaseDate = releaseDate; }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", releaseDate=" + releaseDate +
                '}';
    }
}
