
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HELLO
 */
public class CarManager {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<String>();
        options.add("List all brands"); options.add("Add a new brand");
        options.add("Search a brand based on its ID"); options.add("Update a brand");
        options.add("Save brands to the file, named brands.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car"); options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID"); options.add("Save cars to file, named cars.txt\n");
        BrandList brandList = new BrandList();
        brandList.loadFromFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\cars.txt");
        int choice;
        Menu menu = new Menu();
        do {
            choice = menu.int_getChoice(options);
            switch (choice) {
                case 1:
                    brandList.listBrand();
                    break;
                case 2:
                    brandList.addBrand();
                    break;
                case 3:
                    System.out.print("\nEnter ID of searched brand: ");
                    String bID = sc.nextLine();
                    if (brandList.searchID(bID) == -1)
                        System.out.println("Not found!\n");
                    else
                        System.out.println(brandList.get(brandList.searchID(bID)));
                        System.out.println("\n");
                    break;
                case 4:
                    brandList.updateBrand();
                    break;
                case 5:
                    brandList.saveToFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\brands.txt");
                    System.out.println("Save successfully!\n");
                    break;
                case 6:
                    carList.listCars();
                    System.out.println("\n");
                    break;
                case 7:
                    carList.printBasedBrandName();
                    System.out.println("\n");
                    break;
                case 8:
                    carList.addCar();
                    break;
                case 9:
                    carList.removeCar();
                    break;
                case 10:
                    carList.updateCar();
                    break;
                case 11:
                    carList.saveToFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\cars.txt");
                    break;
                default:
                    System.out.println("Done!");
                    return ;
            }
        }
        while (choice >= 1 && choice <= 11);
    }
}
