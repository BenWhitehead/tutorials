package com.github.benwhitehead.tutorials.sudoku;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Ben Whitehead
 */
public class SudokuPuzzleTest {

    @Test
    public void isSolved() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {4, 5, 6, 7, 8, 9, 1, 2, 3},
                        {7, 8, 9, 1, 2, 3, 4, 5, 6},
                        {2, 3, 4, 5, 6, 7, 8, 9, 1},
                        {5, 6, 7, 8, 9, 1, 2, 3, 4},
                        {8, 9, 1, 2, 3, 4, 5, 6, 7},
                        {3, 4, 5, 6, 7, 8, 9, 1, 2},
                        {6, 7, 8, 9, 1, 2, 3, 4, 5},
                        {9, 1, 2, 3, 4, 5, 6, 7, 8}
                }
        );
        assertThat(puzzle.isSolved()).isTrue();
    }

    @Test
    public void isSolved_false() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isSolved()).isFalse();
    }

    @Test
    public void isRowSolved_row0() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isRowSolved(0)).isTrue();
    }

    @Test
    public void isRowSolved_row8() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 2, 3, 4, 5, 6, 7, 8, 9}
                }
        );
        assertThat(puzzle.isRowSolved(8)).isTrue();
    }

    @Test
    public void isRowSolved_row4() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {1, 2, 3, 4, 5, 6, 7, 8, 9},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isRowSolved(4)).isTrue();
    }

    @Test
    public void isColumnSolved_col0() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {2, 0, 0, 0, 0, 0, 0, 0, 0},
                        {3, 0, 0, 0, 0, 0, 0, 0, 0},
                        {4, 0, 0, 0, 0, 0, 0, 0, 0},
                        {5, 0, 0, 0, 0, 0, 0, 0, 0},
                        {6, 0, 0, 0, 0, 0, 0, 0, 0},
                        {7, 0, 0, 0, 0, 0, 0, 0, 0},
                        {8, 0, 0, 0, 0, 0, 0, 0, 0},
                        {9, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isColumnSolved(0)).isTrue();
    }

    @Test
    public void isColumnSolved_col8() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {0, 0, 0, 0, 0, 0, 0, 0, 1},
                        {0, 0, 0, 0, 0, 0, 0, 0, 2},
                        {0, 0, 0, 0, 0, 0, 0, 0, 3},
                        {0, 0, 0, 0, 0, 0, 0, 0, 4},
                        {0, 0, 0, 0, 0, 0, 0, 0, 5},
                        {0, 0, 0, 0, 0, 0, 0, 0, 6},
                        {0, 0, 0, 0, 0, 0, 0, 0, 7},
                        {0, 0, 0, 0, 0, 0, 0, 0, 8},
                        {0, 0, 0, 0, 0, 0, 0, 0, 9}
                }
        );
        assertThat(puzzle.isColumnSolved(8)).isTrue();
    }

    @Test
    public void isColumnSolved_col4() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 0, 2, 0, 0, 0, 0},
                        {0, 0, 0, 0, 3, 0, 0, 0, 0},
                        {0, 0, 0, 0, 4, 0, 0, 0, 0},
                        {0, 0, 0, 0, 5, 0, 0, 0, 0},
                        {0, 0, 0, 0, 6, 0, 0, 0, 0},
                        {0, 0, 0, 0, 7, 0, 0, 0, 0},
                        {0, 0, 0, 0, 8, 0, 0, 0, 0},
                        {0, 0, 0, 0, 9, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isColumnSolved(4)).isTrue();
    }


    @Test
    public void isSubSquareSolved() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {1, 4, 7, 0, 0, 0, 0, 0, 0},
                        {2, 5, 8, 0, 0, 0, 0, 0, 0},
                        {3, 6, 9, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        assertThat(puzzle.isSubSquareSolved(0, 0)).isTrue();
    }

    @Test(expected = IllegalStateException.class)
    public void getSolution_unsolved() throws Exception {
        final SudokuPuzzle puzzle = new SudokuPuzzle(
                new int[][]{
                        {1, 4, 7, 0, 0, 0, 0, 0, 0},
                        {2, 5, 8, 0, 0, 0, 0, 0, 0},
                        {3, 6, 9, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }
        );
        puzzle.getSolution();
    }
}
