package com.github.benwhitehead.tutorials.sudoku;

/**
 * @author Ben Whitehead
 */
public class SudokuPuzzle implements Puzzle {

    private CellValue[][] possibleSolution;

    public SudokuPuzzle(int[][] puzzle) {
        possibleSolution = new CellValue[9][9];
        for (int i = 0; i < puzzle.length; i++) {
            int[] ints = puzzle[i];
            for (int j = 0; j < ints.length; j++) {
                int val = ints[j];
                final CellValue value;
                if (0 < val && val <= 9) {
                    value = new DefinedValue(val);
                } else {
                    value = new EmptyCell();
                }
                possibleSolution[i][j] = value;
            }
        }
    }

    @Override
    public boolean isSolved() {
        for (int i = 0; i < possibleSolution.length; i++) {
            if (!isRowSolved(i)) {
                return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!isSubSquareSolved(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isRowSolved(int rowIndex) {
        for (int i = 0; i < possibleSolution[rowIndex].length; i++) {
            final CellValue cellValue = possibleSolution[rowIndex][i];
            if (!cellValue.isSolved()) {
                return false;
            }
        }
        return true;
    }

    public boolean isColumnSolved(int columnIndex) {
        for (final CellValue[] aPossibleSolution : possibleSolution) {
            final CellValue cellValue = aPossibleSolution[columnIndex];
            if (!cellValue.isSolved()) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubSquareSolved(final int row, final int col) {
        final int rowOffset = row * 3;
        final int colOffset = col * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final CellValue cellValue = possibleSolution[rowOffset + i][colOffset + j];
                if (!cellValue.isSolved()) {
                    return false;
                }
            }
        }
        return true;
    }
}
