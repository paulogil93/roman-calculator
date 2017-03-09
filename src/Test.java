package roman_calculator;

/*
 * roman-calculator
 *
 * @author      Paulo Gil
 * @date        06/02/2017
 * @email       paulogil93@gmail.com
 * @version      1.0
 */

import java.io.IOException;
import java.io.PrintWriter;

public class Test {

    public static void main(String[] args) {
        try{
            PrintWriter writer = new PrintWriter("debug.txt", "UTF-8");

            for(int i = 1; i < 4000; i++) {
                RomanNumber num1 = new RomanNumber(i);
                RomanNumber num2 = new RomanNumber(num1.getNumeral());
                writer.print(i + ": ");
                if(num1.getValue() == num2.getValue()) writer.print("True");
                else writer.print("False");
                writer.print("\n");
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("FILE NOT FOUND!");
        }

    }
}
