package roman_calculator;

/*
 * roman-calculator
 *
 * @author      Paulo Gil
 * @date        06/02/2017
 * @email       paulogil93@gmail.com
 * @version      1.0
 */

public class RomanNumber {
    private String numeral;
    private int value;

    public RomanNumber(String str) {
        this.numeral = str.toUpperCase();
        if(!isValid()) {
            this.numeral = null;
            this.value = 0;
        } else {
            this.value = calcValue();
        }
    }

    public RomanNumber(int v) {
        this.value = v;
        if(v > 0 && v < 4000) {
            this.numeral = calcNumeral();
        } else {
            this.numeral = null;
            this.value = 0;
        }
    }

    public String getNumeral() {
        return this.numeral;
    }

    public int getValue() {
        return this.value;
    }

    //Calculates the value of the Roman Number
    public int calcValue() {
        int value = 0;
        int i = 0;
        while(i < this.numeral.length()) {
            String c1 = String.valueOf(this.numeral.charAt(i));
            String c2 = "";

            if((i+1) < this.numeral.length()) {
                c2 = String.valueOf(this.numeral.charAt(i+1));
            }

            String str = new StringBuilder().append(c1).append(c2).toString();

            if(isNumeral(str, Numeral.class)) {
                value += Numeral.valueOf(str).getValue();
                i += 2;
            } else {
                str = new StringBuilder().append(c1).toString();
                if(isNumeral(str, Numeral.class)) {
                    value += Numeral.valueOf(str).getValue();
                }
                i++;
            }
        }

        return value;
    }

    //Calculates the Roman Numeral, based on an integer
    private String calcNumeral() {
        StringBuilder sb = new StringBuilder();
        int tmp;
        int thousands;
        int hundreds;
        int tens;
        int units;

        thousands = (int) Math.ceil(this.value/1000);
        tmp = this.value - (1000*thousands);
        hundreds = (int) Math.ceil(tmp/100);
        tmp = tmp - (100*hundreds);
        tens = (int) Math.ceil(tmp/10);
        tmp = tmp - (10*tens);
        units = tmp;

        int i = 1;
        while(i <= thousands) {
            sb.append("M");
            i++;
        }

        switch(hundreds) {
            case 1:
                sb.append("C");
                break;
            case 2:
                sb.append("CC");
                break;
            case 3:
                sb.append("CCC");
                break;
            case 4:
                sb.append("CD");
                break;
            case 5:
                sb.append("D");
                break;
            case 6:
                sb.append("DC");
                break;
            case 7:
                sb.append("DCC");
                break;
            case 8:
                sb.append("DCCC");
                break;
            case 9:
                sb.append("CM");
                break;
            default:
                sb.append("");
                break;
        }

        switch (tens) {
            case 1:
                sb.append("X");
                break;
            case 2:
                sb.append("XX");
                break;
            case 3:
                sb.append("XXX");
                break;
            case 4:
                sb.append("XL");
                break;
            case 5:
                sb.append("L");
                break;
            case 6:
                sb.append("LX");
                break;
            case 7:
                sb.append("LXX");
                break;
            case 8:
                sb.append("LXXX");
                break;
            case 9:
                sb.append("XC");
                break;
            default:
                sb.append("");
                break;
        }

        switch (units) {
            case 1:
                sb.append("I");
                break;
            case 2:
                sb.append("II");
                break;
            case 3:
                sb.append("III");
                break;
            case 4:
                sb.append("IV");
                break;
            case 5:
                sb.append("V");
                break;
            case 6:
                sb.append("VI");
                break;
            case 7:
                sb.append("VII");
                break;
            case 8:
                sb.append("VIII");
                break;
            case 9:
                sb.append("IX");
                break;
            default:
                sb.append("");
                break;
        }

        return sb.toString();
    }

    //Checks if a given string is a Numeral(if exists in Enum class)
    private <E extends Enum<E>> boolean isNumeral(String s, Class<E> c) {
        for(E e : c.getEnumConstants()) {
            if(e.name().equals(s)) {
                return true;
            }
        }
        return false;
    }

    //Checks if the Roman Number is valid, based on Roman Number rules
    private boolean isValid() {
        int lastValue = Integer.MAX_VALUE;
        int i = 0;
        while(i < this.numeral.length()) {

            String c1 = String.valueOf(this.numeral.charAt(i));
            String c2 = "";

            if((i+1) < this.numeral.length()) {
                c2 = String.valueOf(this.numeral.charAt(i+1));
            }

            String str = new StringBuilder().append(c1).append(c2).toString();

            if(isNumeral(str, Numeral.class)) {
                i += 2;
            } else {
                i++;
                str = new StringBuilder().append(c1).toString();
            }

            if(hasRepetition()) {
                return false;
            } else if(!canRepeat(str, lastValue)) {
                return false;
            } else if(lastValue < 10) {
                if(lastValue == 9) {
                    return false;
                } else if(lastValue == 4) {
                    return false;
                } else if (Numeral.valueOf(str).getValue() != 1) {
                    return false;
                }
            } else if(isNumeral(str, Numeral.class)) {
               if(Numeral.valueOf(str).getValue() > lastValue) {
                   return false;
               }
            } else {
                return false;
            }

            lastValue = Numeral.valueOf(str).getValue();
        }

        return true;
    }

    //Checks is a given Numeral can be repeated (Roman Number rules)
    private boolean canRepeat(String s, int last) {
        if(s.equals("V") && last == 5) return false;
        else if(s.equals("L") && last == 50) return false;
        else return !(s.equals("D") && last == 500);
    }

    //Checks for 4 consecutive chars (Roman Number rules)
    private boolean hasRepetition() {
        int MAX = 0;
        int count = 0;
        for(int i = 0; i < this.numeral.length(); i++) {
            if((i+3) < this.numeral.length()) {
                String sub = this.numeral.substring(i, (i+4));
                for(int k = 0; k < sub.length(); k++) {
                    if (sub.charAt(0) == sub.charAt(k)) {
                        count++;
                    }
                }
                if(count > MAX) {
                    MAX = count;
                }
                count = 0;
            }
        }
        return MAX == 4;
    }
}
