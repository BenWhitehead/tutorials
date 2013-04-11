package com.github.benwhitehead.tutorials.sudoku;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

/**
 * @author Ben Whitehead
 */
public class PossibleValueTest {

    @Test(expected = IllegalArgumentException.class)
    public void instantiate_0() throws Exception {
        new PossibleValue(0);
    }

    @Test
    public void instantiate_1() throws Exception {
        new PossibleValue(1);
    }

    @Test
    public void instantiate_9() throws Exception {
        new PossibleValue(9);
    }

    @Test
    public void instantiate_1to9() throws Exception {
        new PossibleValue(1, 2, 3, 4, 5, 6, 7, 8, 9);
    }

    @Test(expected = IllegalArgumentException.class)
    public void instantiate_10() throws Exception {
        new PossibleValue(10);
    }

    @Test
    public void instantiate_dupValues() throws Exception {
        new PossibleValue(1, 2, 3, 1, 2, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPossibleValue_0() throws Exception {
        new PossibleValue(3).addPossibleValue(0);
    }

    @Test
    public void addPossibleValue_1() throws Exception {
        assertThat(new PossibleValue(3).addPossibleValue(1)).isTrue();
    }

    @Test
    public void addPossibleValue_3() throws Exception {
        assertThat(new PossibleValue(3).addPossibleValue(3)).isFalse();
    }

    @Test
    public void addPossibleValue_9() throws Exception {
        assertThat(new PossibleValue(3).addPossibleValue(9)).isTrue();
    }

    @Test(expected = IllegalArgumentException.class)
    public void addPossibleValue_10() throws Exception {
        new PossibleValue(3).addPossibleValue(10);
    }

    @Test
    public void removePossibleValue_0() throws Exception {
        assertThat(new PossibleValue(3).removePossibleValue(0)).isFalse();
    }

    @Test
    public void removePossibleValue_1() throws Exception {
        assertThat(new PossibleValue(3).removePossibleValue(1)).isFalse();
    }

    @Test
    public void removePossibleValue_3() throws Exception {
        assertThat(new PossibleValue(3).removePossibleValue(3)).isTrue();
    }

    @Test
    public void removePossibleValue_9() throws Exception {
        assertThat(new PossibleValue(3).removePossibleValue(9)).isFalse();
    }

    @Test
    public void removePossibleValue_10() throws Exception {
        assertThat(new PossibleValue(3).removePossibleValue(10)).isFalse();
    }

    @Test
    public void isSolved() throws Exception {
        assertThat(new PossibleValue(3).isSolved()).isTrue();
    }

    @Test
    public void isSolved_false() throws Exception {
        assertThat(new PossibleValue(3, 4).isSolved()).isFalse();
    }
}
