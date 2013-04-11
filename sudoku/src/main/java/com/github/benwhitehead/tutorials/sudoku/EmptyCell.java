package com.github.benwhitehead.tutorials.sudoku;

import lombok.ToString;

/**
 * @author Ben Whitehead
 */
@ToString
public class EmptyCell implements CellValue {
    @Override
    public boolean isSolved() {
        return false;
    }
}
