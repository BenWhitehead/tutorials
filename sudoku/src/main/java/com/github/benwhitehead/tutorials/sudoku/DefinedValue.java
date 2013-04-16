package com.github.benwhitehead.tutorials.sudoku;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Ben Whitehead
 */
@ToString
@Getter
public class DefinedValue implements CellValue {

    private final int value;

    public DefinedValue(final int value) {
        this.value = value;
    }

    @Override
    public boolean isSolved() {
        return true;
    }
}
