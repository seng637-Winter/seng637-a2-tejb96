package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

    @Test
    public void testCentralValue() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Central value of exampleRange", 0, exampleRange.getCentralValue(), 1e-9);
        assertEquals("Central value of positiveRange", 5.5, positiveRange.getCentralValue(), 1e-9);
        assertEquals("Central value of negativeRange", -5.5, negativeRange.getCentralValue(), 1e-9);
        assertEquals("Central value of wideRange", 0, wideRange.getCentralValue(), 1e-9);
    }

    @Test
    public void testLength() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Length of exampleRange", 2, exampleRange.getLength(), 1e-9);
        assertEquals("Length of positiveRange", 9, positiveRange.getLength(), 1e-9);
        assertEquals("Length of negativeRange", 9, negativeRange.getLength(), 1e-9);
        assertEquals("Length of wideRange", 200, wideRange.getLength(), 1e-9);
    }

    @Test
    public void testLowerBound() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);

        assertEquals("Lower bound of exampleRange", -1, exampleRange.getLowerBound(), 1e-9);
        assertEquals("Lower bound of positiveRange", 1, positiveRange.getLowerBound(), 1e-9);
        assertEquals("Lower bound of negativeRange", -10, negativeRange.getLowerBound(), 1e-9);
    }
    
    @Test
    public void testUpperBound() {
        // Create local Range instances for this specific test
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);
        Range singlePointRange = new Range(5, 5); // Test a range where the lower and upper bounds are the same

        // Assert that getUpperBound returns the correct value for each range
        assertEquals("Upper bound of exampleRange", 1, exampleRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of positiveRange", 10, positiveRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of negativeRange", -1, negativeRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of wideRange", 100, wideRange.getUpperBound(), 1e-9);
        assertEquals("Upper bound of singlePointRange", 5, singlePointRange.getUpperBound(), 1e-9);
    }

    @Test
    public void testConstrain() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertEquals("Constraining 0 in exampleRange", 0, exampleRange.constrain(0), 1e-9);
        assertEquals("Constraining 5 in positiveRange", 5, positiveRange.constrain(5), 1e-9);
        assertEquals("Constraining -5 in negativeRange", -5, negativeRange.constrain(-5), 1e-9);
        assertEquals("Constraining 50 in wideRange", 50, wideRange.constrain(50), 1e-9);

        assertEquals("Constraining -2 in exampleRange", -1, exampleRange.constrain(-2), 1e-9);
        assertEquals("Constraining 11 in positiveRange", 10, positiveRange.constrain(11), 1e-9);
    }

    @Test
    public void testContains() {
        Range exampleRange = new Range(-1, 1);
        Range positiveRange = new Range(1, 10);
        Range negativeRange = new Range(-10, -1);
        Range wideRange = new Range(-100, 100);

        assertTrue("exampleRange contains 0", exampleRange.contains(0));
        assertFalse("positiveRange does not contain 0", positiveRange.contains(0));
        assertTrue("negativeRange contains -5", negativeRange.contains(-5));
        assertFalse("wideRange does not contain 150", wideRange.contains(150));
    }

    
}