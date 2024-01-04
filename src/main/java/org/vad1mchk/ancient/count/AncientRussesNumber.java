package org.vad1mchk.vserod.count;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;

public class AncientRussesNumber extends Number {
    public static final int THRESHOLD = 10;

    private final BitSet integerDigitsField;
    private final BitSet fractionalDigitsField;

    public AncientRussesNumber() {
        integerDigitsField = new BitSet();
        fractionalDigitsField = new BitSet();
    }

    public AncientRussesNumber(double value) {
        this();
        if (value < 0) {
            throw new ArithmeticException("Древние русы не использовали отрицательные числа.");
        }
        if (value >= 2 << THRESHOLD) {
            throw new ArithmeticException("Древние русы такими большими числами не считали.");
        }

        int intPart = (int) value;
        for (int i = 0; i <= THRESHOLD; ++i) {
            if (((intPart >> i) & 1) != 0) {
                integerDigitsField.set(i);
            }
        }
        for (int i = 1; i <= THRESHOLD; ++i) {
            if ((((int) (value * (1 << i))) & 1) != 0) {
                fractionalDigitsField.set(i - 1);
            }
        }
    }

    public boolean hasIndex(int index) {
        return index >= 0 ? integerDigitsField.get(index) : fractionalDigitsField.get(-index - 1);
    }

    public List<Integer> indices() {
        List<Integer> list = new LinkedList<>();
        for (int i = THRESHOLD; i >= -THRESHOLD; --i) {
            if (hasIndex(i)) {
                list.add(i);
            }
        }
        return list;
    }

    public int indicesCount() {
        return integerDigitsField.cardinality() + fractionalDigitsField.cardinality();
    }

    @Override
    public int intValue() {
        int value = 0;
        for (int i = 0; i <= THRESHOLD; ++i) {
            value |= (integerDigitsField.get(i) ? 1 : 0) << i;
        }
        return value;
    }

    @Override
    public long longValue() {
        long value = 0;
        for (int i = 0; i <= THRESHOLD; ++i) {
            value |= (integerDigitsField.get(i) ? 1 : 0) << i;
        }
        return value;
    }

    @Override
    public float floatValue() {
        float value = intValue();
        for (int i = 1; i <= THRESHOLD; ++i) {
            value += fractionalDigitsField.get(i - 1) ? (1.0 / (1 << i)) : 0;
        }
        return value;
    }

    @Override
    public double doubleValue() {
        double value = intValue();
        for (int i = 1; i <= THRESHOLD; ++i) {
            value += fractionalDigitsField.get(i - 1) ? (1.0 / (1 << i)) : 0;
        }
        return value;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ARN(");
        for (int i = THRESHOLD; i >= 0; --i) {
            builder.append(integerDigitsField.get(i) ? '1' : '0');
        }
        builder.append('.');
        for (int i = 1; i <= THRESHOLD; ++i) {
            builder.append(fractionalDigitsField.get(i - 1) ? '1' : '0');
        }
        builder.append(')');
        return builder.toString();
    }
}
