package com.github.benwhitehead.tutorials.sudoku;

import lombok.ToString;

/**
 * @author Ben Whitehead
 */
@ToString
public class SudokuPuzzle implements Puzzle {

    private CellValue[][] possibleSolution;

    public SudokuPuzzle(int[][] puzzle) {
        this();
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

    private SudokuPuzzle() {
        possibleSolution = new CellValue[9][9];
    }

    public int[][] getSolution() {
        return getSolution(true);
    }

    public int[][] getSolution(final boolean failIfUnsolved) {
        final int[][] solution = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                final CellValue cellValue = possibleSolution[i][j];
                if (cellValue instanceof DefinedValue) {
                    final DefinedValue definedValue = (DefinedValue) cellValue;
                    solution[i][j] = definedValue.getValue();
                } else if (cellValue instanceof SolvedValue) {
                    final SolvedValue solvedValue = (SolvedValue) cellValue;
                    solution[i][j] = solvedValue.getValue();
                } else {
                    if (failIfUnsolved) {
                        throw new IllegalStateException(String.format("Puzzle has not yet been solved, unable to provide solution. [cell (%d, %d)]", i, j));
                    } else {
                        solution[i][j] = 0;
                    }
                }
            }
        }
        return solution;
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

    public CellValue getCellValue(final int row, final int col) {
        return possibleSolution[row][col];
    }

    public void setCellValue(final int row, final int col, final CellValue value) {
        /*todo add validation to prevent changing of defined values*/
        possibleSolution[row][col] = value;
    }

    public SudokuPuzzle cloneKeepingType() {
        final SudokuPuzzle clone = new SudokuPuzzle();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                CellValue cellValue = this.possibleSolution[i][j];
                // All other cell types are immutable,
                // We only need to account for cells of PossibleValue.
                if (cellValue instanceof PossibleValue) {
                    final PossibleValue possibleValue = (PossibleValue) cellValue;
                    cellValue = new PossibleValue(possibleValue.getValues());
                }
                clone.possibleSolution[i][j] = cellValue;
            }
        }
        return clone;
    }
}
