package org.yukung.maven;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
    @Test
    public void testMultiply() {
        App app = new App();
        assertEquals("Result", 50, app.multiply(10, 5));
    }
}
