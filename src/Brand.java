/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HELLO
 */
public class Brand {
    String brandID, brandName, soundBrand;
    double price;
    public Brand() {
    }
    public Brand(String brandID, String brandName, String soundBrand, double price) {
        this.brandID = brandID;
        this.brandName = brandName;
        this.soundBrand = soundBrand;
        this.price = price;
    }
    public String getBrandID() {
        return brandID;
    }
    public void setBrandID(String brandID) {
        this.brandID = brandID;
    }
    public String getBrandName() {
        return brandName;
    }
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
    public String getSoundBrand() {
        return soundBrand;
    }
    public void setSoundBrand(String soundBrand) {
        this.soundBrand = soundBrand;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String toString() {
        return brandID + "\t" + brandName + "\t" + soundBrand + "\t" + price;
    }
}
