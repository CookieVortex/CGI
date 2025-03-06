package com.example.planner.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("from")
    private String departureLocation;

    @JsonProperty("to")
    private String arrivalLocation;

    private LocalDate departureDate;
    private LocalDateTime departureTime;
    private Integer price;

    private String formattedDepartureTime;
    private String formattedDepartureDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getArrivalLocation() {
        return arrivalLocation;
    }

    public void setArrivalLocation(String arrivalLocation) {
        this.arrivalLocation = arrivalLocation;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getFormattedDepartureTime() {
        return formattedDepartureTime;
    }

    public void setFormattedDepartureTime(String formattedDepartureTime) {
        this.formattedDepartureTime = formattedDepartureTime;
    }

    public String getFormattedDepartureDate() {
        return formattedDepartureDate;
    }

    public void setFormattedDepartureDate(String formattedDepartureDate) {
        this.formattedDepartureDate = formattedDepartureDate;
    }
}
