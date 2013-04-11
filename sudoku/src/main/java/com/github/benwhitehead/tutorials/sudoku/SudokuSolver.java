package com.github.benwhitehead.tutorials.sudoku;

import lombok.ToString;

/**
 * @author Ben Whitehead
 */
@ToString
public class SudokuSolver {
    private final int[][] originalPuzzle;

    private CellValue[][] possibleSolution;

    public SudokuSolver(int[][] puzzle) {
        originalPuzzle = puzzle;
        possibleSolution = new CellValue[9][9];
        for (int i = 0; i < puzzle.length; i++) {
            int[] ints = puzzle[i];
            for (int j = 0; j < ints.length; j++) {
                int val = ints[j];
                if (0 < val && val <= 9) {
                    possibleSolution[i][j] = new DefinedValue(val);
                }
            }
        }
    }

    public String getOriginalPuzzleAsString() {
        final StringBuilder sb = new StringBuilder();
        for (int[] row : originalPuzzle) {
            for (int i = 0; i < row.length; i++) {
                final int val = row[i];
                if (0 < val && val <= 9) {
                    sb.append(val);
                } else {
                    sb.append("_");
                }

                if (i + 1 < row.length) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void solve() {

    }

}
