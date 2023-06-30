/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HELLO
 */
public class Car implements Comparable<Car> {
    String carID, color, frameID, engineID;
    Brand brand = new Brand();
    public Car() {
    }
    public Car(String carID, Brand brand, String color, String frameID, String engineID) {
        this.carID = carID;
        this.brand = brand;
        this.color = color;
        this.frameID = frameID;
        this.engineID = engineID;
    }
    public String getCarID() {
        return carID;
    }
    public void setCarID(String carID) {
        this.carID = carID;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public String getFrameID() {
        return frameID;
    }
    public void setFrameID(String frameID) {
        this.frameID = frameID;
    }
    public String getEngineID() {
        return engineID;
    }
    public void setEngineID(String engineID) {
        this.engineID = engineID;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
    public String toString() {
        return carID + ", " + brand.getBrandID() + ", " + color + ", " + frameID + ", " + engineID;
    }
    public String screenString() {
        return brand + "\n" + carID + ", " + color + ", " + frameID + ", " + engineID;
    }
    public int compareTo(Car c) {
        int d = (this.getBrand().getBrandName()).compareTo(c.getBrand().getBrandName());
        if (d != 0)
            return d;
        return this.getCarID().compareTo(c.getCarID());
    }
}
