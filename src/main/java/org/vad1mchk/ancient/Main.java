package org.vad1mchk.vserod;

import org.vad1mchk.vserod.count.AncientRussesNumber;
import org.vad1mchk.vserod.util.AncientRussesNumberStringConverter;
import org.vad1mchk.vserod.util.StringConverter;

public class Main {
    public static void main(String[] args) {
        StringConverter<AncientRussesNumber> converter = new AncientRussesNumberStringConverter();

        for (double value: new double[] {
                0, 1, 0.5, 0.25, 2048 - 1.0 / (1 << 10), 2048, -0.01
        }) {
            try {
                System.out.println(">>> " + value);
                AncientRussesNumber number = new AncientRussesNumber(value);
                System.out.println("<<< " + converter.toString(number));
            } catch (ArithmeticException e) {
                System.err.println("<<< " + e.getMessage());
            }
        }
    }
}