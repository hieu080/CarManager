
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

    //brandList: Đối tượng BrandList được sử dụng để lưu trữ danh sách các thương hiệu.
    BrandList brandList;
    Scanner sc = new Scanner(System.in);

    //Phương thức khởi tạo có tham số: Khởi tạo một đối tượng CarList mới với một danh sách thương hiệu đã cho.
    public CarList(BrandList bList) {
        brandList = bList;
    }

    //Phương thức loadFromFile(String fileName): Đọc dữ liệu từ một tệp tin và nạp vào CarList. 
    //Trả về true nếu tệp tin tồn tại và thành công, ngược lại trả về false.
    public boolean loadFromFile(String fileName) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                return false;
            } else {
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
        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    //Phương thức saveToFile(String fileName): Lưu danh sách xe vào một tệp tin. 
    //Trả về true nếu danh sách không rỗng và lưu thành công, ngược lại trả về false.
    public boolean saveToFile(String fileName) {
        if (this.size() == 0) {
            System.out.println("\nEmpty list!");
            return false;
        }
        try {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            for (Car i : this) {
                pw.println(i.toString());
            }
            pw.close();
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("Save sucessfully!\n");
        return true;
    }

    //Phương thức searchID(String carID): Tìm kiếm xe trong danh sách dựa trên ID. Trả về chỉ số của xe trong danh sách nếu tìm thấy, ngược lại trả về -1.
    public int searchID(String carID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getCarID().equals(carID.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    //Phương thức searchFrame(String fID): Tìm kiếm xe trong danh sách dựa trên ID khung. Trả về chỉ số của xe trong danh sách nếu tìm thấy, ngược lại trả về -1.
    public int searchFrame(String fID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getFrameID().equals(fID.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    //Phương thức searchEngine(String eID): Tìm kiếm xe trong danh sách dựa trên ID động cơ. Trả về chỉ số của xe trong danh sách nếu tìm thấy, ngược lại trả về -1.
    public int searchEngine(String eID) {
        for (int i = 0; i < this.size(); i++) {
            if (this.get(i).getEngineID().equals(eID.toUpperCase())) {
                return i;
            }
        }
        return -1;
    }

    //Phương thức addCar(): Thêm một xe mới vào danh sách. 
    //Yêu cầu người dùng nhập thông tin về xe (ID, thương hiệu, màu sắc, ID khung, ID động cơ) và thực hiện kiểm tra điều kiện hợp lệ trước khi thêm xe mới.
    public void addCar() {
        String newCarID, newColor, newFrameID, newEngineID;
        System.out.println("\nEnter the car detail:");
        while (true) {
            System.out.print("  Car ID: ");
            newCarID = sc.nextLine().toUpperCase();
            if (searchID(newCarID) >= 0) {
                System.out.println("The car ID is duplicated!");
            } else {
                break;
            }
        }
        Menu menu = new Menu();
        System.out.println("  Brand:");
        Brand b = (Brand) menu.ref_getChoice(brandList);
        do {
            System.out.print("  Color: ");
            newColor = sc.nextLine();
            if (newColor.length() == 0) {
                System.out.println("    The color is empty!");
            }
        } while (newColor.length() == 0);
        while (true) {
            System.out.print("  Frame ID (format F00000): ");
            newFrameID = sc.nextLine().toUpperCase();
            if (searchFrame(newFrameID) >= 0) {
                System.out.println("    The frame ID is duplicated!");
            } else if (!newFrameID.matches("[F][0-9]{5}")) {
                System.out.println("    The frame ID: F and 5 digits!");
            } else {
                break;
            }
        }
        while (true) {
            System.out.print("  Engine ID (format E00000): ");
            newEngineID = sc.nextLine().toUpperCase();
            if (searchEngine(newEngineID) >= 0) {
                System.out.println("    The engine ID is duplicated!");
            } else if (!newEngineID.matches("[E][0-9]{5}")) {
                System.out.println("    The engine ID: E and 5 digits!");
            } else {
                break;
            }
        }
        this.add(new Car(newCarID, b, newColor, newFrameID, newEngineID));
        System.out.println("New car has been added!\n");
    }

    //Phương thức printBasedBrandName(): In ra các xe dựa trên một phần của tên thương hiệu. 
    //Yêu cầu người dùng nhập một phần của tên thương hiệu và in ra danh sách các xe có tên thương hiệu chứa phần đó.
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
        if (count == 0) {
            System.out.println("No car is detected!");
        }
    }

    //Phương thức removeCar(): Xóa một xe khỏi danh sách dựa trên ID. Yêu cầu người dùng nhập ID của xe cần xóa và xóa xe đó khỏi danh sách.
    public boolean removeCar() {
        System.out.print("\nEnter removed car's ID: ");
        String removedID = sc.nextLine().toUpperCase();
        if (searchID(removedID) < 0) {
            System.out.println("Not found!\n");
            return false;
        } else {
            this.remove(searchID(removedID));
            System.out.println("Remove successfully!\n");
        }
        return true;
    }

    //Phương thức updateCar(): Cập nhật thông tin của một xe trong danh sách dựa trên ID. 
    //Yêu cầu người dùng nhập ID của xe cần cập nhật và nhập thông tin mới về xe. Kiểm tra điều kiện hợp lệ trước khi cập nhật thông tin.
    public boolean updateCar() {
        String newColor, newFrameID, newEngineID;
        System.out.print("\nEnter updated car's ID: ");
        String carID = sc.nextLine().toUpperCase();
        if (searchID(carID) < 0) {
            System.out.println("Not found!\n");
            return false;
        } else {
            Menu m = new Menu();
            System.out.println("  New brand:");
            Brand b = (Brand) m.ref_getChoice(brandList);
            do {
                System.out.print("  New color: ");
                newColor = sc.nextLine();
                if (newColor.length() == 0) {
                    System.out.println("    The color is empty!");
                }
            } while (newColor.length() == 0);
            while (true) {
                System.out.print("  Frame ID (format F00000): ");
                newFrameID = sc.nextLine().toUpperCase();
                if (searchFrame(newFrameID) >= 0) {
                    System.out.println("    The frame ID is duplicated!");
                } else if (!newFrameID.matches("[F][0-9]{5}")) {
                    System.out.println("    The frame ID: F and 5 digits!");
                } else {
                    break;
                }
            }
            while (true) {
                System.out.print("  Engine ID (format E00000): ");
                newEngineID = sc.nextLine().toUpperCase();
                if (searchEngine(newEngineID) >= 0) {
                    System.out.println("    The engine ID is duplicated!");
                } else if (!newEngineID.matches("[E][0-9]{5}")) {
                    System.out.println("    The engine ID: E and 5 digits!");
                } else {
                    break;
                }
            }
            this.get(searchID(carID)).setBrand(b);
            this.get(searchID(carID)).setColor(newColor);
            this.get(searchID(carID)).setFrameID(newFrameID);
            this.get(searchID(carID)).setEngineID(newEngineID);
            System.out.println("The car " + carID + " has been updated!\n");
            return true;
        }
    }

    //Phương thức listCars(): Liệt kê tất cả các xe trong danh sách theo thứ tự tên thương hiệu tăng dần.
    public void listCars() {
        Collections.sort(this);
        for (Car i : this) {
            System.out.println(i.screenString());
        }
    }
}
