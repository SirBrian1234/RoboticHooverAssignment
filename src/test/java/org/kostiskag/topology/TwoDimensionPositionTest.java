package org.kostiskag.topology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoDimensionPositionTest {

    @Test
    public void toStringTest() {
        TwoDimensionPosition point = new TwoDimensionPosition(10, 11);
        Assertions.assertEquals(point.toString(), "[10, 11]");
    }

    @Test
    public void equalityTest() {
        TwoDimensionPosition pointA = new TwoDimensionPosition(10, 11);
        TwoDimensionPosition pointB = new TwoDimensionPosition(10, 10);

        Assertions.assertEquals(pointA, pointA);
        Assertions.assertNotEquals(pointA, pointB);

        //also test deep copy here and then equality
        TwoDimensionPosition pointC = new TwoDimensionPosition(pointA);

        //pointC should be equal with A
        Assertions.assertEquals(pointC, pointA);
    }
}
