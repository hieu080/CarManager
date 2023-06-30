
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class BrandList extends ArrayList<Brand> {
    Scanner sc = new Scanner(System.in);
    public BrandList() {
        super();
    }
    public boolean loadFromFile(String fName) {
        try {
            File f = new File(fName);
            if (!f.exists())
                return false;
            FileReader fr = new FileReader(f);
            BufferedReader bf = new BufferedReader(fr);
            String s;
            while ((s = bf.readLine()) != null) {
                StringTokenizer stk = new StringTokenizer(s, ",:");
                String brandID = stk.nextToken().trim().toUpperCase();
                String brandName = stk.nextToken().trim();
                String soundBrand = stk.nextToken().trim();
                double price = Double.parseDouble(stk.nextToken());
                this.add(new Brand(brandID, brandName, soundBrand, price));
            }
            bf.close();
            fr.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public boolean saveToFile(String fName) {
        if (this.size() == 0) {
            System.out.println("\nEmpty list!");
            return false;
        }
        try {
            File f = new File(fName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Brand x: this)
                pw.println(x.getBrandID() + ", " + x.getBrandName() + ", " + x.getSoundBrand() + ": " + x.getPrice() + "\n") ;
            pw.close();
            fw.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }
    public int searchID(String bID) {
        for (int i = 0; i < this.size(); i++)
            if (this.get(i).getBrandID().equals(bID.toUpperCase()))
                return i;
        return -1;
    }
    public Brand getUserChoice() {
        Menu mnu = new Menu();
        return (Brand)mnu.ref_getChoice(this);
    }
    public void addBrand() {
        String newBrandID, newBrandName, newSoundBrand;
        double price;
        System.out.println("\nEnter the brand details:");
        do {
            System.out.print("  Brand ID: ");
            newBrandID = sc.nextLine().toUpperCase();
            if (searchID(newBrandID) >= 0)
                System.out.println("    The brand ID is duplicated!");
            else break;
        }
        while (true);
        do {
            System.out.print("  Brand name: ");
            newBrandName = sc.nextLine();
            if (newBrandName.equals(""))
                System.out.println("    The brand name is empty!");
            else break;
        }
        while (true);
        do {
            System.out.print("  Sound brand: ");
            newSoundBrand = sc.nextLine();
            if (newSoundBrand.equals(""))
                System.out.println("    The sound brand is empty!");
            else break;
        }
        while (true);
        do {
            System.out.print("  Price: ");
            price = Double.parseDouble(sc.nextLine());
            if (price <= 0)
                System.out.println("    The price is not greater than 0!");
            else break;
        }
        while (true);
        this.add(new Brand(newBrandID, newBrandName, newSoundBrand, price));
        System.out.println("New brand has been added!\n");
    }
    public void updateBrand() {
        System.out.print("\nEnter the brand ID to update: ");
        String newBrandID = sc.nextLine().toUpperCase();
        if (searchID(newBrandID) < 0)
            System.out.println("Not found!\n");
        else {
            String newBrandName, newSoundBrand;
            double price;
            System.out.println("Enter the brand details:");
            do {
                System.out.print("  Brand name: ");
                newBrandName = sc.nextLine();
                if (newBrandName.equals(""))
                    System.out.println("    The brand name is empty!");
                else break;
            }
            while (true);
            do {
                System.out.print("  Sound brand: ");
                newSoundBrand = sc.nextLine();
                if (newSoundBrand.equals(""))
                    System.out.println("    The sound brand is empty!");
                else break;
            }
            while (true);
            do {
                System.out.print("  Price: ");
                price = Double.parseDouble(sc.nextLine());
                if (price <= 0)
                    System.out.println("    The price is not greater than 0!");
                else break;
            }
            while (true);
            this.get(searchID(newBrandID)).setBrandName(newBrandName);
            this.get(searchID(newBrandID)).setSoundBrand(newSoundBrand);
            this.get(searchID(newBrandID)).setPrice(price);
            System.out.println("The brand with ID " + newBrandID + " has been updated!\n");
        }
    }
    public void listBrand() {
        if (this.size() == 0) {
            System.out.println("Empty list!");
            return ;
        }
        System.out.println("\nBRAND LIST");
        System.out.println("-----------------------------------------");
        for (Brand x: this){
            System.out.println(x.toString());
        }
        System.out.println("\n");
    }
}
