package com.github.benwhitehead.tutorials.sudoku;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @author Ben Whitehead
 */
@ToString
public class SudokuSolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuSolver.class);

    private final int[][] originalPuzzle;

    private SudokuPuzzle sudokuPuzzle;

    public SudokuSolver(int[][] puzzle) {
        originalPuzzle = puzzle;
        sudokuPuzzle = new SudokuPuzzle(puzzle);
    }

    public String getOriginalPuzzleAsString() {
        return getMatrixAsString(originalPuzzle);
    }

    public int[][] solve() {
        LOGGER.info("Solving puzzle:\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}", originalPuzzle);
        final Stopwatch stopwatch = new Stopwatch().start();

        int[][] progress = null;
        while (!sudokuPuzzle.isSolved()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final CellValue cellValue = sudokuPuzzle.getCellValue(i, j);
                    if (!cellValue.isSolved()) {
                        final CellValue newValueForCell = getPossibleValueForCell(i, j);
                        LOGGER.trace("Setting cell ({}, {}) value to: {}", new Object[]{i, j, newValueForCell});
                        sudokuPuzzle.setCellValue(i, j, newValueForCell);
                    }
                }
            }
            final int[][] newProgress = sudokuPuzzle.getSolution(false);
            if (Arrays.deepEquals(progress, newProgress)) {
                throw new IllegalStateException("Unable to make any progress on solving the puzzle.");
            } else {
                progress = newProgress;
            }
            LOGGER.debug("Solution Progress:\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}\n" +
                        "{}", progress
            );

        }
        stopwatch.stop();
        final int[][] solution = sudokuPuzzle.getSolution();
        LOGGER.info("Solved puzzle in "+ stopwatch + ". Solution is:\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}\n" +
                    "{}", solution);
        return solution;
    }

    public CellValue getPossibleValueForCell(final int row, final int col) {
        LOGGER.trace("getPossibleValueForCell(row : {}, col : {})", row, col);
        final PossibleValue possibleValueForRow = getPossibleValueForRow(row);
        LOGGER.trace("possibleValueForRow = {}", possibleValueForRow);
        final PossibleValue possibleValueForColumn = getPossibleValueForColumn(col);
        LOGGER.trace("possibleValueForColumn = {}", possibleValueForColumn);
        final PossibleValue possibleValueForSubSquare = getPossibleValueForSubSquare(row / 3, col / 3);
        LOGGER.trace("possibleValueForSubSquare = {}", possibleValueForSubSquare);
        final Sets.SetView<Integer> temp = Sets.intersection(possibleValueForRow.getValues(), possibleValueForColumn.getValues());
        final ImmutableSet<Integer> intersection = Sets.intersection(temp, possibleValueForSubSquare.getValues()).immutableCopy();
        LOGGER.trace("intersection = {}", intersection);
        return CellValueFactory.getCellValueForValuesSet(intersection);
    }

    public PossibleValue getPossibleValueForRow(final int row) {
        final PossibleValue returnValue = PossibleValue.allValues();
        for (int i = 0; i < 9; i++) {
            final CellValue cellValue = sudokuPuzzle.getCellValue(row, i);
            if (cellValue.isSolved()) {
                if (cellValue instanceof DefinedValue) {
                    final DefinedValue definedValue = (DefinedValue) cellValue;
                    returnValue.removePossibleValue(definedValue.getValue());
                } else if (cellValue instanceof SolvedValue) {
                    final SolvedValue solvedValue = (SolvedValue) cellValue;
                    returnValue.removePossibleValue(solvedValue.getValue());
                }
            }
        }
        return returnValue;
    }

    public PossibleValue getPossibleValueForColumn(final int col) {
        final PossibleValue returnValue = PossibleValue.allValues();
        for (int i = 0; i < 9; i++) {
            final CellValue cellValue = sudokuPuzzle.getCellValue(i, col);
            if (cellValue.isSolved()) {
                if (cellValue instanceof DefinedValue) {
                    final DefinedValue definedValue = (DefinedValue) cellValue;
                    returnValue.removePossibleValue(definedValue.getValue());
                } else if (cellValue instanceof SolvedValue) {
                    final SolvedValue solvedValue = (SolvedValue) cellValue;
                    returnValue.removePossibleValue(solvedValue.getValue());
                }
            }
        }
        return returnValue;
    }

    public PossibleValue getPossibleValueForSubSquare(final int row, final int col) {
        final PossibleValue returnValue = PossibleValue.allValues();
        final int rowOffset = row * 3;
        final int colOffset = col * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final CellValue cellValue = sudokuPuzzle.getCellValue(rowOffset + i, colOffset + j);
                if (cellValue.isSolved()) {
                    if (cellValue instanceof DefinedValue) {
                        final DefinedValue definedValue = (DefinedValue) cellValue;
                        returnValue.removePossibleValue(definedValue.getValue());
                    } else if (cellValue instanceof SolvedValue) {
                        final SolvedValue solvedValue = (SolvedValue) cellValue;
                        returnValue.removePossibleValue(solvedValue.getValue());
                    }
                }
            }
        }
        return returnValue;
    }

    private String getMatrixAsString(final int[][] matrix) {
        final StringBuilder sb = new StringBuilder();
        for (int[] row : matrix) {
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
}
