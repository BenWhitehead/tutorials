package com.github.benwhitehead.tutorials.sudoku;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Ben Whitehead
 */
public class SudokuSolverTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SudokuSolverTest.class);

    @Test
    public void solve() throws Exception {
        final int[][] puzzle = getMatrixFromResourcePath("/start-1.txt");
        final int[][] solution = getMatrixFromResourcePath("/solution-1.txt");
        final SudokuSolver solver = new SudokuSolver(puzzle);
        LOGGER.info("solver = {}", solver);
        LOGGER.info("\n{}", solver.getOriginalPuzzleAsString());
        final int[][] solve = solver.solve();
        assertThat(solve).isEqualTo(solution);
    }

    private int[][] getMatrixFromResourcePath(final String resourcePath) throws IOException {
        final InputStream inputStream = this.getClass().getResourceAsStream(resourcePath);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        final Splitter splitter = Splitter.on(" ");
        final int[][] matrix = new int[9][9];
        for (int i = 0; i < 9; i++) {
            final String line = reader.readLine().trim();
            final List<String> strings = Lists.newArrayList(splitter.split(line));
            assertThat(strings.size()).isEqualTo(9);
            for (int j = 0; j < strings.size(); j++) {
                final String string = strings.get(j);
                try {
                    matrix[i][j] = Integer.valueOf(string);
                } catch (NumberFormatException e) {
                    matrix[i][j] = 0;
                }
            }
        }
        return matrix;
    }
}
