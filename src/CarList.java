
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HELLO
 */
public class CarList extends ArrayList<Car> {
    BrandList brandList;
    Scanner sc = new Scanner(System.in);
    public CarList(BrandList bList) {
        brandList = bList;
    }
    public boolean loadFromFile(String fileName){
        try {
            File f = new File(fileName);
            if (!f.exists())
                return false;
            else {
                FileReader fr = new FileReader(f);
                BufferedReader bf = new BufferedReader(fr);
                String s;
                while ((s = bf.readLine()) != null) {
                    StringTokenizer stk = new StringTokenizer(s, ",");
                    String carID = stk.nextToken().trim().toUpperCase();
                    String brandID = stk.nextToken().trim().toUpperCase();
                    String color = stk.nextToken().trim();
                    String frameID = stk.nextToken().trim().toUpperCase();
                    String engineID = stk.nextToken().trim().toUpperCase();
                    Brand b = brandList.get(brandList.searchID(brandID));
                    this.add(new Car(carID, b, color, frameID, engineID));
                }
                bf.close();
                fr.close();
            }
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public boolean saveToFile(String fileName) {
        if (this.size() == 0) { 
            System.out.println("\nEmpty list!");
            return false;
        }
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car i: this)
                pw.println(i.toString());
            pw.close();
            fw.close();
        }  
        catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Save sucessfully!\n");
        return true;
    }
    public int searchID(String carID) {
       for (int i = 0; i < this.size(); i++)
           if (this.get(i).getCarID().equals(carID.toUpperCase()))
               return i;
       return -1;
    }
    public int searchFrame(String fID) {
       for (int i = 0; i < this.size(); i++)
           if (this.get(i).getFrameID().equals(fID.toUpperCase()))
               return i;
       return -1;
    }
    public int searchEngine(String eID) {
       for (int i = 0; i < this.size(); i++)
           if (this.get(i).getEngineID().equals(eID.toUpperCase()))
               return i;
       return -1;
    }
    public void addCar() {
        String newCarID, newColor, newFrameID, newEngineID;
        System.out.println("\nEnter the car detail:");
        while (true) {
            System.out.print("  Car ID: ");
            newCarID = sc.nextLine().toUpperCase();
            if (searchID(newCarID) >= 0)
                System.out.println("The car ID is duplicated!");
            else
                break;
        }
        Menu menu = new Menu();
        System.out.println("  Brand:");
        Brand b = (Brand)menu.ref_getChoice(brandList);
        do {
            System.out.print("  Color: ");
            newColor = sc.nextLine();
            if (newColor.length() == 0)
                System.out.println("    The color is empty!");
        }
        while (newColor.length() == 0);
        while (true) {
            System.out.print("  Frame ID (format F00000): ");
            newFrameID = sc.nextLine().toUpperCase();
            if (searchFrame(newFrameID) >= 0)
                System.out.println("    The frame ID is duplicated!");
            else
                if (!newFrameID.matches("[F][0-9]{5}"))
                    System.out.println("    The frame ID: F and 5 digits!");     
                else
                    break;
        }
        while (true) {
            System.out.print("  Engine ID (format E00000): ");
            newEngineID = sc.nextLine().toUpperCase();
            if (searchEngine(newEngineID) >= 0)
                System.out.println("    The engine ID is duplicated!");
            else
                if (!newEngineID.matches("[E][0-9]{5}"))
                    System.out.println("    The engine ID: E and 5 digits!");     
                else
                    break;
        }
        this.add(new Car(newCarID, b, newColor, newFrameID, newEngineID));
        System.out.println("New car has been added!\n");
    }
    public void printBasedBrandName() {
        System.out.print("\nEnter part of brand name: ");
        String aPartOfBrandName = sc.nextLine();
        int count = 0;
        for (int i = 0; i < this.size(); i++) {
            Car c = this.get(i);
            if (c.getBrand().getBrandName().contains(aPartOfBrandName)) {
                System.out.println(c.screenString());
                count++;
            }
        }
        if (count == 0)
            System.out.println("No car is detected!");
    }
    public boolean removeCar() {
        System.out.print("\nEnter removed car's ID: ");
        String removedID = sc.nextLine().toUpperCase();
        if (searchID(removedID) < 0) {
            System.out.println("Not found!\n");
            return false;
        }
        else{
            this.remove(searchID(removedID));
            System.out.println("Remove successfully!\n");
        }
        return true;
    }
    public boolean updateCar() {
        String newColor, newFrameID, newEngineID;
        System.out.print("\nEnter updated car's ID: ");
        String carID = sc.nextLine().toUpperCase();
        if (searchID(carID) < 0) {
            System.out.println("Not found!\n");
            return false;
        }
        else {
            Menu m = new Menu();
            System.out.println("  New brand:");
            Brand b = (Brand)m.ref_getChoice(brandList);
            do {
                System.out.print("  New color: ");
                newColor = sc.nextLine();
                if (newColor.length() == 0)
                    System.out.println("    The color is empty!");
            }
            while (newColor.length() == 0);
            while (true) {
                System.out.print("  Frame ID (format F00000): ");
                newFrameID = sc.nextLine().toUpperCase();
                if (searchFrame(newFrameID) >= 0)
                    System.out.println("    The frame ID is duplicated!");
                else
                    if (!newFrameID.matches("[F][0-9]{5}"))
                        System.out.println("    The frame ID: F and 5 digits!");     
                    else
                        break;
            }
            while (true) {
                System.out.print("  Engine ID (format E00000): ");
                newEngineID = sc.nextLine().toUpperCase();
                if (searchEngine(newEngineID) >= 0)
                    System.out.println("    The engine ID is duplicated!");
                else
                    if (!newEngineID.matches("[E][0-9]{5}"))
                        System.out.println("    The engine ID: E and 5 digits!");     
                    else
                        break;
            }
            this.get(searchID(carID)).setBrand(b);
            this.get(searchID(carID)).setColor(newColor);
            this.get(searchID(carID)).setFrameID(newFrameID);
            this.get(searchID(carID)).setEngineID(newEngineID);
            System.out.println("The car " + carID + " has been updated!\n");
            return true;
        }
    }
    public void listCars() {
        Collections.sort(this);
        for (Car i: this)
            System.out.println(i.screenString());
    }
}
