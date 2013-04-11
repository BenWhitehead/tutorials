package com.github.benwhitehead.tutorials.sudoku;

/**
 * @author Ben Whitehead
 */
public class EmptyCell implements CellValue {
    @Override
    public boolean isSolved() {
        return false;
    }
}
