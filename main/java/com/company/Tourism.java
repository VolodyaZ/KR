package com.company;

import java.util.Date;

public class Tourism {
    private int id;
    private String name;
    private int tourId;
    private String tourName;
    private Date tourStartingDate;
    private Date tourEndingDate;
    private String category;
    private int price;
    private double rating;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ", " +
                name + ", " +
                tourId + ", " +
                tourName + ", " +
                tourStartingDate + ", " +
                tourEndingDate + ", " +
                category + ", " +
                price + ", " +
                rating;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public Date getTourStartingDate() {
        return tourStartingDate;
    }

    public void setTourStartingDate(Date tourStartingDate) {
        this.tourStartingDate = tourStartingDate;
    }

    public Date getTourEndingDate() {
        return tourEndingDate;
    }

    public void setTourEndingDate(Date tourEndingDate) {
        this.tourEndingDate = tourEndingDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Tourism() {

    }

    public Tourism(int id,
                   String name,
                   int tourId,
                   String tourName,
                   Date tourStartingDate,
                   Date tourEndingDate,
                   String category,
                   int price,
                   double rating) {
        this.id = id;
        this.name = name;
        this.tourId = tourId;
        this.tourName = tourName;
        this.tourStartingDate = tourStartingDate;
        this.tourEndingDate = tourEndingDate;
        this.category = category;
        this.price = price;
        this.rating = rating;
    }
}
