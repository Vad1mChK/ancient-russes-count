package org.vad1mchk.ancient;

import org.vad1mchk.ancient.count.AncientRussesNumber;
import org.vad1mchk.ancient.util.AncientRussesNumberStringConverter;
import org.vad1mchk.ancient.util.StringConverter;

public class Main {
    public static void main(String[] args) {
        StringConverter<AncientRussesNumber> converter = new AncientRussesNumberStringConverter();

        double value;

        try {
            value = Double.parseDouble(args[0]);
            if (!Double.isFinite(value)) {
                throw new ArithmeticException("Древние русы использовали только конечные числа.");
            }
            AncientRussesNumber number = new AncientRussesNumber(value);
            System.out.println(converter.toString(number));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Передайте программе хотя бы один аргумент. Первый из них должен быть числом.");
            System.exit(1);
        } catch (NullPointerException | NumberFormatException e) {
            System.err.println("Аргумент, который вы ввели, - не число.");
            System.exit(2);
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
            System.exit(3);
        }
    }
}