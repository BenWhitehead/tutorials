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

    PossibleValue(int... values) {
        this.values = Sets.newHashSet(Ints.asList(values));
    }

    private boolean addPossibleValue(int possibleValue) {
        return values.add(possibleValue);
    }

    private boolean removePossibleValue(int possibleValue) {
        return values.remove(possibleValue);
    }

    @Override
    public boolean isSolved() {
        return values.size() == 1;
    }
}
