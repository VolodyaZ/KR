package com.company;

import java.util.Date;
import java.util.Objects;

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

    public Tourism() {
        this(0, "", 0, "", new Date(), new Date(), "", 0, 0.);
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

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tourism tourism = (Tourism) o;
        return id == tourism.id &&
                tourId == tourism.tourId &&
                price == tourism.price &&
                Double.compare(tourism.rating, rating) == 0 &&
                Objects.equals(name, tourism.name) &&
                Objects.equals(tourName, tourism.tourName) &&
                Objects.equals(tourStartingDate, tourism.tourStartingDate) &&
                Objects.equals(tourEndingDate, tourism.tourEndingDate) &&
                Objects.equals(category, tourism.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tourId, tourName, tourStartingDate, tourEndingDate, category, price, rating);
    }
}
