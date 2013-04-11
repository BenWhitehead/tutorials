package com.github.benwhitehead.tutorials.sudoku;

import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * @author Ben Whitehead
 */
public final class CellValueFactory {
    private CellValueFactory() {
    }

    public static CellValue getCellValueForValuesSet(@NotNull final Set<Integer> values) {
        if (values.size() == 1) {
            return new SolvedValue(values.iterator().next());
        } else {
            return new PossibleValue(values);
        }
    }
}
