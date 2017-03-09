package roman_calculator;

/*
 * roman-calculator
 *
 * @author      Paulo Gil
 * @date        06/02/2017
 * @email       paulogil93@gmail.com
 * @version      1.0
 */

public enum Numeral {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000),
    IV(4), IX(9), XL(40), XC(90), CD(400), CM(900);

    private int value;

    Numeral(int value) {
        this.value = value;
    }

    protected int getValue() {
        return this.value;
    }

}
