package org.vad1mchk.vserod.util;

import org.vad1mchk.vserod.count.AncientRussesNumber;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AncientRussesNumberStringConverter implements StringConverter<AncientRussesNumber> {
    private static final List<String> INTEGER_PART_WORDS = Collections.unmodifiableList(
            Arrays.asList("целковый", "пара", "четверик", "осьмерик", "пудовик", "медяк", "серебряк", "золотник",
                    "осьмик", "девятик", "десятик")
    );

    private static final String ZERO_WORD = "ноль";

    private static final List<String> FRACTIONAL_PART_WORDS = Collections.unmodifiableList(
            Arrays.asList("полушка", "четвертушка", "осьмушка", "пудовичок", "медячок", "серебрячок", "золотничок",
                    "осьмичок", "девятичок", "десятичок")
    );

    @Override
    public String toString(AncientRussesNumber obj) {
        StringBuilder builder = new StringBuilder();
        switch (obj.indicesCount()) {
            case 0: {
                builder.append(capitalizeFirst(ZERO_WORD));
                break;
            }
            case 1: {
                int firstIndex = obj.indices().get(0);
                builder.append(capitalizeFirst(wordForIndex(firstIndex)));
                break;
            }
            case 2: {
                int firstIndex = obj.indices().get(0);
                int secondIndex = obj.indices().get(1);
                builder.append(wordForIndex(firstIndex));
                builder.append(" да ");
                builder.append(wordForIndex(secondIndex));
                break;
            }
            default: {
                boolean first = true;
                for (int i: obj.indices()) {
                    if (first) {
                        builder.append(capitalizeFirst(wordForIndex(i)));
                        first = false;
                    } else {
                        builder.append(", да ");
                        builder.append(wordForIndex(i));
                    }
                }
            }
        }
        builder.append('.');
        return builder.toString();
    }

    private String wordForIndex(int index) {
        if (index < -AncientRussesNumber.THRESHOLD || index > AncientRussesNumber.THRESHOLD) {
            throw new IndexOutOfBoundsException("Индекс должен быть между " +
                    -AncientRussesNumber.THRESHOLD +
                    " и " +
                    AncientRussesNumber.THRESHOLD +
                    '.'
            );
        }
        return index >= 0 ? INTEGER_PART_WORDS.get(index) : FRACTIONAL_PART_WORDS.get(-index - 1);
    }

    private String capitalizeFirst(String string) {
        if (string.isEmpty()) return string;
        return Character.toUpperCase(string.charAt(0)) + string.substring(1);
    }
}
