package ru.job4j;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class AppTest {
    @Test
    public void whenGetOne() {
        assertThat(new App().getOne(), is(1));
    }
}