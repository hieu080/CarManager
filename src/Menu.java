
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
    public int int_getChoice(ArrayList<E> options) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < options.size(); i++)
            System.out.println((i + 1) + " - " + options.get(i));
        System.out.print("Please choose an option 1 .. " + options.size() + ": ");
        return Integer.parseInt(sc.nextLine());
    }
    public E ref_getChoice(ArrayList<E> options) {
        int response;
        for (int i = 0; i < options.size(); i++)
            System.out.println((i + 1) + " - " + options.get(i));
        do 
            response = int_getChoice(options);
        while (response < 0 || response > options.size());
        return options.get(response - 1);
    }
}
