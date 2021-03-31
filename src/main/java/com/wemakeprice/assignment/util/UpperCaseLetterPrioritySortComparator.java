package com.wemakeprice.assignment.util;

import java.util.Comparator;

public class UpperCaseLetterPrioritySortComparator implements Comparator<Character> {

    @Override
    public int compare(Character c1, Character c2) {

        int upperC1 = Character.toUpperCase(c1);
        int upperC2 = Character.toUpperCase(c2);

        if (upperC1 == upperC2) {
            return c1.compareTo(c2);
        } else if (upperC1 > upperC2) {
            return 1;
        } else {
            return -1;
        }
    }
}