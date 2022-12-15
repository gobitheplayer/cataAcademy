import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите два числа (либо только арабские, либо только римские!):");
        String expression = scanner.nextLine();
        System.out.println(parse(expression));
    }

    public static String parse(String expression) throws Exception {
        int number1;
        int number2;
        String operation;
        String result;
        boolean isRoman;
        String[] operands = expression.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Error: формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        operation = detectOperation(expression);
        if (operation == null) throw new Exception("Error: формат математической операции не удовлетворяет заданию - два операнда и один оператор");
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            number1 = Roman.convertToArabian(operands[0]);
            number2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            number1 = Integer.parseInt(operands[0]);
            number2 = Integer.parseInt(operands[1]);
            isRoman = false;
        } else {
            throw new Exception("Error: используются одновременно разные системы счисления");
        }
        if(number1 > 10 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int tempResult = calculate(number1, number2, operation);
        if (isRoman) {
            if(tempResult <= 0) {
                throw new Exception("Error: в римской системе нет отрицательных чисел");
            }
            result = Roman.convertToRoman(tempResult);
        } else {
            result = String.valueOf(tempResult);
        }
        return result;
    }

    static String detectOperation(String exp) {
        if (exp.contains("+")) return "+";
        else if (exp.contains("-")) return "-";
        else if (exp.contains("*")) return "*";
        else if (exp.contains("/")) return "/";
        return null;
    }

    static int calculate(int num1, int num2, String oper) {
        if(oper.equals("+")) return num1+num2;
        else if(oper.equals("-")) return num1-num2;
        else if(oper.equals("*")) return num1*num2;
        else return num1/num2;
    }

}

class Roman {
    static String[] romanArray = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };
    public static boolean isRoman(String num) {
        for (int i = 0; i < romanArray.length; i++) {
            if(num.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }
    public static int convertToArabian(String roman){
        for(int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }
    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}
