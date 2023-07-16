
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
        
        //Đoạn code khởi tạo một đối tượng Scanner để đọc dữ liệu từ bàn phím.
        //Sau đó, tạo một danh sách ArrayList<String> có tên là options và khởi tạo các phần tử trong danh sách với các tùy chọn cho người dùng.
        Scanner sc = new Scanner(System.in);
        ArrayList<String> options = new ArrayList<String>();
        options.add("List all brands");
        options.add("Add a new brand");
        options.add("Search a brand based on its ID");
        options.add("Update a brand");
        options.add("Save brands to the file, named brands.txt");
        options.add("List all cars in ascending order of brand names");
        options.add("List cars based on a part of an input brand name");
        options.add("Add a car");
        options.add("Remove a car based on its ID");
        options.add("Update a car based on its ID");
        options.add("Save cars to file, named cars.txt\n");
        
        //Tiếp theo, tạo đối tượng BrandList và CarList để quản lý danh sách các thương hiệu và danh sách các xe.
        BrandList brandList = new BrandList();
        brandList.loadFromFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\brands.txt");
        CarList carList = new CarList(brandList);
        carList.loadFromFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\cars.txt");
        
        //Sau đó, tạo một vòng lặp do-while để hiển thị menu và xử lý lựa chọn của người dùng.
        int choice;
        Menu menu = new Menu();
        do {
            choice = menu.int_getChoice(options);
            switch (choice) {
                //Chức năng: Liệt kê tất cả các thương hiệu.
                //Gọi phương thức listBrand() từ đối tượng brandList.
                case 1:
                    brandList.listBrand();
                    break;
                //Chức năng: Thêm một thương hiệu mới.
                //Gọi phương thức addBrand() từ đối tượng brandList.
                case 2:
                    brandList.addBrand();
                    break;
                //Chức năng: Tìm kiếm một thương hiệu dựa trên ID.
                //Yêu cầu người dùng nhập ID của thương hiệu cần tìm kiếm.
                //Nếu ID không tồn tại trong danh sách thương hiệu (searchID() trả về -1), in ra thông báo "Not found!".
                //Nếu ID tồn tại, in ra thông tin của thương hiệu đó (get() từ brandList).
                case 3:
                    System.out.print("\nEnter ID of searched brand: ");
                    String bID = sc.nextLine();
                    if (brandList.searchID(bID) == -1) {
                        System.out.println("Not found!\n");
                    } else {
                        System.out.println(brandList.get(brandList.searchID(bID)));
                    }
                    System.out.println("\n");
                    break;
                //Chức năng: Cập nhật thông tin của một thương hiệu.
                //Gọi phương thức updateBrand() từ đối tượng brandList.
                case 4:
                    brandList.updateBrand();
                    break;
                //Chức năng: Lưu danh sách thương hiệu vào file "brands.txt".
                //Gọi phương thức saveToFile() từ đối tượng brandList để lưu danh sách thương hiệu.
                case 5:
                    brandList.saveToFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\brands.txt");
                    System.out.println("Save successfully!\n");
                    break;
                //Chức năng: Liệt kê tất cả các xe.
                //Gọi phương thức listCars() từ đối tượng carList.
                case 6:
                    carList.listCars();
                    System.out.println("\n");
                    break;
                //Chức năng: Liệt kê các xe dựa trên một phần của tên thương hiệu.
                //Gọi phương thức printBasedBrandName() từ đối tượng carList.
                case 7:
                    carList.printBasedBrandName();
                    System.out.println("\n");
                    break;
                //Chức năng: Thêm một xe mới.
                //Gọi phương thức addCar() từ đối tượng carList.
                case 8:
                    carList.addCar();
                    break;
                //Chức năng: Xóa một xe dựa trên ID.
                //Gọi phương thức removeCar() từ đối tượng carList.
                case 9:
                    carList.removeCar();
                    break;
                //Chức năng: Cập nhật thông tin của một xe dựa trên ID.
                //Gọi phương thức updateCar() từ đối tượng carList.
                case 10:
                    carList.updateCar();
                    break;
                //Chức năng: Lưu danh sách xe vào file "cars.txt".
                //Gọi phương thức saveToFile() từ đối tượng carList để lưu danh sách xe.
                case 11:
                    carList.saveToFile("C:\\Users\\HELLO\\Documents\\NetBeansProjects\\CarProject\\cars.txt");
                    break;
                //Chức năng: Kết thúc chương trình.
                //In ra thông báo "Done!" và kết thúc phương thức main.
                default:
                    System.out.println("Done!");
                    return;
            }
        } while (choice >= 1 && choice <= 11);
    }
}
