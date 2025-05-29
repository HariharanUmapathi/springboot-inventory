package com.drivers.application.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ServiceArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serviceId")
    Integer serviceId;
    @Column(name = "city")
    String city;
    @Column(name = "city_ascii")
    String cityAscii;
    @Column(name = "lat")
    Double lat;
    @Column(name = "lng")
    Double lng;
    @Column(name = "country")
    String country;
    @Column(name = "iso2")
    String iso2;
    @Column(name = "iso3")
    String iso3;
    @Column(name = "admin_name")
    String adminName;
    @Column(name = "capital")
    String capital;
    @Column(name = "population")
    Integer population;
    @Column(name = "sheet_id")
    Integer sheetId;
}
