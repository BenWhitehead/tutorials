package com.github.benwhitehead.tutorials.sudoku;

import com.google.common.base.Stopwatch;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.Arrays;
import java.util.Set;

/**
 * @author Ben Whitehead
 */
@ToString
public class SudokuSolver {
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuSolver.class);

    private final int[][] originalPuzzle;


    public SudokuSolver(int[][] puzzle) {
        originalPuzzle = puzzle;
    }

    public int[][] solve() {
        final SudokuPuzzle sudokuPuzzle = new SudokuPuzzle(originalPuzzle);
        LOGGER.info("Attempting to Solve provided puzzle:\n" +
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

        final SudokuPuzzle solvedPuzzle = solvePuzzle(sudokuPuzzle, MarkerFactory.getMarker(String.format("")));
        stopwatch.stop();
        final int[][] solution = solvedPuzzle.getSolution();
        LOGGER.info("Solved puzzle in " + stopwatch + ".  Solution is:\n" +
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

    private SudokuPuzzle solvePuzzle(final SudokuPuzzle sudokuPuzzle, final Marker marker) {
        LOGGER.debug(marker,
                "solvePuzzle(sudokuPuzzle : \n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               {}\n" +
                "               )", sudokuPuzzle.getSolution(false)
        );
        int[][] progress = null;
        while (!sudokuPuzzle.isSolved()) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final CellValue cellValue = sudokuPuzzle.getCellValue(i, j);
                    if (!cellValue.isSolved()) {
                        final CellValue newValueForCell = getPossibleValueForCell(i, j, sudokuPuzzle);
                        LOGGER.trace(marker, "Setting cell ({}, {}) value to: {}", new Object[]{i, j, newValueForCell});
                        sudokuPuzzle.setCellValue(i, j, newValueForCell);
                    }
                }
            }
            final int[][] newProgress = sudokuPuzzle.getSolution(false);
            if (Arrays.deepEquals(progress, newProgress)) {
                LOGGER.debug(marker, "Unable to make any progress on solving the puzzle, a guess is required.");
                LOGGER.trace(marker, "guess(sudokuPuzzle : {})", sudokuPuzzle);
                final SudokuPuzzle puzzleForGuess = sudokuPuzzle.cloneKeepingType();
                GetCellToGuessValueOf cellToGuessValueOf = new GetCellToGuessValueOf(sudokuPuzzle).invoke();
                int row = cellToGuessValueOf.getRow();
                int col = cellToGuessValueOf.getCol();

                final CellValue cellValue = sudokuPuzzle.getCellValue(row, col);
                LOGGER.debug(marker, "Cell to guess value of located at: ({}, {}). Cell is: {} ", new Object[]{row, col, cellValue});
                if (cellValue instanceof PossibleValue) {
                    final PossibleValue value = (PossibleValue) cellValue;
                    // This is the guess, just loop over the values trying to see if the value will work.
                    final Set<Integer> possibleValues = value.getValues();
                    for (Integer possibleValue : possibleValues) {
                        final SudokuPuzzle puzzleWithGuess = puzzleForGuess.cloneKeepingType();
                        LOGGER.debug(marker, "Guessing value: {}", possibleValue);
                        puzzleWithGuess.setCellValue(row, col, new SolvedValue(possibleValue));
                        final SudokuPuzzle possiblySolvedPuzzleWithGuess = solvePuzzle(puzzleWithGuess, MarkerFactory.getMarker(String.format("<(%d, %d): %d>", row, col, possibleValue)));
                        if (possiblySolvedPuzzleWithGuess != null && possiblySolvedPuzzleWithGuess.isSolved()) {
                            return possiblySolvedPuzzleWithGuess;
                        } else {
                            LOGGER.debug(marker, "Guess value: {} did not yield a solution.", possibleValue);
                        }
                    }
                } else {
                    throw new IllegalStateException("Trying to guess for a cell that isn't a possible value.");
                }
                LOGGER.debug(marker, "Unable to find a solution when guessing for cell located at: ({}, {}). Cell is: {} ", new Object[]{row, col, cellValue});
                return null;
            } else {
                progress = newProgress;
            }
            LOGGER.debug(marker, "Solution Progress:\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}\n" +
                        "               {}", progress
            );
        }
        return sudokuPuzzle;
    }

    public CellValue getPossibleValueForCell(final int row, final int col, final SudokuPuzzle puzzle) {
        LOGGER.trace("getPossibleValueForCell(row : {}, col : {})", row, col);
        final PossibleValue possibleValueForRow = getPossibleValueForRow(puzzle, row);
        LOGGER.trace("possibleValueForRow = {}", possibleValueForRow);
        final PossibleValue possibleValueForColumn = getPossibleValueForColumn(puzzle, col);
        LOGGER.trace("possibleValueForColumn = {}", possibleValueForColumn);
        final PossibleValue possibleValueForSubSquare = getPossibleValueForSubSquare(puzzle, row / 3, col / 3);
        LOGGER.trace("possibleValueForSubSquare = {}", possibleValueForSubSquare);
        final Sets.SetView<Integer> temp = Sets.intersection(possibleValueForRow.getValues(), possibleValueForColumn.getValues());
        final ImmutableSet<Integer> intersection = Sets.intersection(temp, possibleValueForSubSquare.getValues()).immutableCopy();
        LOGGER.trace("intersection = {}", intersection);
        return CellValueFactory.getCellValueForValuesSet(intersection);
    }

    public PossibleValue getPossibleValueForRow(final SudokuPuzzle puzzle, final int row) {
        final PossibleValue returnValue = PossibleValue.allValues();
        for (int i = 0; i < 9; i++) {
            final CellValue cellValue = puzzle.getCellValue(row, i);
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

    public PossibleValue getPossibleValueForColumn(final SudokuPuzzle puzzle, final int col) {
        final PossibleValue returnValue = PossibleValue.allValues();
        for (int i = 0; i < 9; i++) {
            final CellValue cellValue = puzzle.getCellValue(i, col);
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

    public PossibleValue getPossibleValueForSubSquare(final SudokuPuzzle puzzle, final int row, final int col) {
        final PossibleValue returnValue = PossibleValue.allValues();
        final int rowOffset = row * 3;
        final int colOffset = col * 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final CellValue cellValue = puzzle.getCellValue(rowOffset + i, colOffset + j);
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

    private class GetCellToGuessValueOf {
        private final SudokuPuzzle sudokuPuzzle;
        private int row;
        private int col;

        public GetCellToGuessValueOf(final SudokuPuzzle sudokuPuzzle) {
            this.sudokuPuzzle = sudokuPuzzle;
            this.row = -1;
            this.col = -1;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public GetCellToGuessValueOf invoke() {
            int minPossibleValueCount = Integer.MAX_VALUE;
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    final CellValue cellValue = sudokuPuzzle.getCellValue(i, j);
                    if (cellValue instanceof PossibleValue) {
                        final PossibleValue possibleValue = (PossibleValue) cellValue;
                        final int numberOfPossibleValues = possibleValue.getValues().size();
                        if (numberOfPossibleValues < minPossibleValueCount) {
                            minPossibleValueCount = numberOfPossibleValues;
                            row = i;
                            col = j;
                        }
                    }
                }
            }
            return this;
        }
    }
}
