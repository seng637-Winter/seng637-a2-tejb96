package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Mockery;
import org.jmock.Expectations;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
    private Mockery context;
    private Values2D values;

    @Before
    public void setUp() {
        context = new Mockery();
        values = context.mock(Values2D.class);
    }	

    @Test
    public void testCreateNumberArray() {
        double[] inputData = {2.0, 3.5, 4.7};
        Number[] expectedOutput = {2.0, 3.5, 4.7};

        Number[] actualOutput = DataUtilities.createNumberArray(inputData);

        assertArrayEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testCreateNumberArray2D() {
        double[][] inputData = {{2.0, 3.5}, {4.7, 5.2}};
        Number[][] expectedOutput = {{2.0, 3.5}, {4.7, 5.2}};

        Number[][] actualOutput = DataUtilities.createNumberArray2D(inputData);

        assertArrayEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testCreateNumberArray2DWithNull() {
        double[][] inputData = null;

        try {
            Number[][] result = DataUtilities.createNumberArray2D(inputData);
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // Test passed!
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNumberArray2DWithEmptyArray() {
        double[][] inputData = {};
        DataUtilities.createNumberArray2D(inputData);
    }
    
    @Test
    public void testCalculateColumnTotal() {
        context.checking(new Expectations() {{
            allowing(values).getValue(with(any(Integer.class)), with(equal(0)));
            will(onConsecutiveCalls(
                returnValue(2.0), 
                returnValue(3.5), 
                returnValue(4.7),
                returnValue(null) 
            ));
            allowing(values).getRowCount();
            will(returnValue(3));  
        }});
        double total = DataUtilities.calculateColumnTotal(values, 0);
        assertEquals(10.2, total, 0.0001);
    }


    @Test
    public void testCalculateColumnTotalWithNullData() {
        double total = 0;
        try {
            total = DataUtilities.calculateColumnTotal(null, 0);
        } catch (NullPointerException e) {}

        assertEquals(0.0, total, 0.0001);
    }

    @Test
    public void testCalculateColumnTotalWithInvalidColumn() {
        context.checking(new Expectations() {{
            allowing(values).getValue(with(any(Integer.class)), with(equal(1)));
            will(returnValue(null));
            allowing(values).getRowCount();
            will(returnValue(3));
        }});
        double total = DataUtilities.calculateColumnTotal(values, 1);
        assertEquals(0.0, total, 0.0001);
    }

    
    @Test
    public void testCalculateRowTotal() {
        context.checking(new Expectations() {{
            allowing(values).getValue(with(equal(0)), with(any(Integer.class)));
            will(onConsecutiveCalls(
                returnValue(2.0), 
                returnValue(3.5), 
                returnValue(4.7),
                returnValue(null)  // default value for out-of-range columns
            ));
            allowing(values).getColumnCount();
            will(returnValue(3));  // adjust this to match your actual number of columns
        }});
        double total = DataUtilities.calculateRowTotal(values, 0);
        assertEquals(10.2, total, 0.0001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCalculateRowTotalWithNullData() {
        try {
            DataUtilities.calculateRowTotal(null, 0);
        } catch (NullPointerException e) {}
    }


    @Test
    public void testCalculateRowTotalWithInvalidRow() {
        context.checking(new Expectations() {{
            allowing(values).getValue(with(equal(1)), with(any(Integer.class)));
            will(returnValue(null));
            allowing(values).getColumnCount();
            will(returnValue(3));  
        }});
        double total = DataUtilities.calculateRowTotal(values, 1);
        assertEquals(0.0, total, 0.0001);
    }
    
    @Test
    public void testGetCumulativePercentages() {
        // Create input KeyedValues
        DefaultKeyedValues keyedValues = new DefaultKeyedValues();
        keyedValues.addValue("0", 5.0);
        keyedValues.addValue("1", 9.0);
        keyedValues.addValue("2", 2.0);

        // Expected output KeyedValues
        DefaultKeyedValues expectedOutput = new DefaultKeyedValues();
        expectedOutput.addValue("0", 0.3125);
        expectedOutput.addValue("1", 0.875);
        expectedOutput.addValue("2", 1.0);

        KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

     // Assert each value in the output matches the expected output
        for (int i = 0; i < expectedOutput.getItemCount(); i++) {
            assertEquals(expectedOutput.getValue(i).doubleValue(), result.getValue(i).doubleValue(), 0.0001);
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesWithNullData() {
        DataUtilities.getCumulativePercentages(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCumulativePercentagesWithEmptyData() {
        DefaultKeyedValues keyedValues = new DefaultKeyedValues(); // Create an empty KeyedValues object

        DataUtilities.getCumulativePercentages(keyedValues);
    }

}
