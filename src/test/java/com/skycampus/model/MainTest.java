package com.skycampus.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class MainTest {

    @Test
    void testMainExecution() {
        assertDoesNotThrow(() -> Main.main(new String[] {}));
    }
}
