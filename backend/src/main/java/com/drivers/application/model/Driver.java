package com.drivers.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="DRIVERS")
public class Driver {
    @Id
    @GeneratedValue
    Integer DriverId;
    String Name;
    public Driver getDriver(){
        return this;
    }
    public Integer getDriverId(){
        return this.DriverId;
    }
    public String getName(){
        return this.Name;
    }
    @Override 
    public String toString(){
        return "["+this.DriverId+"-"+this.Name+"]";
    }
}
