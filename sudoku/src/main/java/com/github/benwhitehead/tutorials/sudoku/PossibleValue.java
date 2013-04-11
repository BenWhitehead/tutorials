package com.github.benwhitehead.tutorials.sudoku;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import lombok.ToString;

import java.util.Set;

/**
* @author Ben Whitehead
*/
@ToString
class PossibleValue implements CellValue {
    private final Set<Integer> values;

    PossibleValue(int value, int... moreValues) {
        validateValue(value);
        this.values = Sets.newHashSet(value);
        for (int v : moreValues) {
            validateValue(v);
        }
        this.values.addAll(Ints.asList(moreValues));
    }

    PossibleValue(final Set<Integer> values) {
        this.values = values;
    }

    public Set<Integer> getValues() {
        return Sets.newHashSet(values);
    }

    public boolean addPossibleValue(int possibleValue) {
        validateValue(possibleValue);
        return values.add(possibleValue);
    }

    public boolean removePossibleValue(int possibleValue) {
        return values.remove(possibleValue);
    }

    @Override
    public boolean isSolved() {
        return values.size() == 1;
    }

    private void validateValue(final int possibleValue) {
        if (!(1 <= possibleValue && possibleValue <= 9)) {
            throw new IllegalArgumentException(String.format("Invalid value %d, must be between 1 and 9.", possibleValue));
        }
    }

    public static PossibleValue allValues() {
        return new PossibleValue(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }
}
