
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
 * @param <E>
 */
public class Menu<E> {
    
    //Phương thức này nhận đầu vào là một danh sách ArrayList (options) chứa các phần tử kiểu E và trả về một giá trị nguyên.
    //Phương thức này sẽ hiển thị danh sách các phần tử trong options kèm theo số thứ tự của mỗi phần tử, 
    //sau đó yêu cầu người dùng chọn một tùy chọn từ 1 đến số lượng tùy chọn có sẵn. 
    //Sau khi người dùng chọn, phương thức trả về giá trị nguyên tương ứng với lựa chọn của người dùng.
    public int int_getChoice(ArrayList<E> options) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i));
        }
        System.out.print("Please choose an option 1 .. " + options.size() + ": ");
        return Integer.parseInt(sc.nextLine());
    }
    
    //Phương thức này nhận đầu vào là một danh sách ArrayList (options) chứa các phần tử kiểu E và trả về một phần tử kiểu E.
    //Phương thức này hiển thị danh sách các phần tử trong options kèm theo số thứ tự của mỗi phần tử. 
    //Sau đó, nó lặp lại việc yêu cầu người dùng chọn một tùy chọn bằng cách sử dụng phương thức int_getChoice. 
    //Lặp lại quá trình này cho đến khi người dùng chọn một tùy chọn hợp lệ (từ 1 đến số lượng tùy chọn có sẵn). 
    //Khi đó, phương thức trả về phần tử tương ứng với lựa chọn của người dùng.
    public E ref_getChoice(ArrayList<E> options) {
        int response;
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + " - " + options.get(i));
        }
        do {
            response = int_getChoice(options);
        } while (response < 0 || response > options.size());
        return options.get(response - 1);
    }
}

