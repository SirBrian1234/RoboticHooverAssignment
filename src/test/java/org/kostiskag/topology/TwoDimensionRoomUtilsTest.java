package org.kostiskag.topology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TwoDimensionRoomUtilsTest {

    @Test
    public void calculateValidRoomDimensionTest() {
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidRoomDimension(-1));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidRoomDimension(-Integer.MAX_VALUE));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidRoomDimension(-Integer.MAX_VALUE - 1));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidRoomDimension(Integer.MAX_VALUE));

        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidRoomDimension(0));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidRoomDimension(10));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidRoomDimension(1_000_000_000));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidRoomDimension(Integer.MAX_VALUE-1));
    }

    @Test
    public void calculateValidCoordinateTest() {
        int maxLimit = 10_000_000;
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinate(10_000_000, 10_000_000));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinate(-1, 10_000_000));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinate(-10_000_000, 10_000_000));

        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinate(0, 10_000_000));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinate(10, 10_000_000));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinate(10_000, 10_000_000));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinate(10_000_000 - 1, 10_000_000));
    }

    @Test
    public void calculateValidCoordinatePairTest() {
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,-1)));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(-1,0)));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,500_000)));
        Assertions.assertFalse(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(10_000_000,0)));

        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,0)));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,500_000 -1)));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(10_000_000-1,0)));
        Assertions.assertTrue(TwoDimensionRoomUtils.calculateValidCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(10_000,1234)));
    }

    @Test
    public void rectifyCoordinatePairTest() {
        Assertions.assertEquals(new TwoDimensionPosition(0,0), TwoDimensionRoomUtils.rectifyCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,-1)));
        Assertions.assertEquals(new TwoDimensionPosition(0,0), TwoDimensionRoomUtils.rectifyCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(-1,0)));
        Assertions.assertEquals(new TwoDimensionPosition(0,500_000 -1), TwoDimensionRoomUtils.rectifyCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(0,500_000)));
        Assertions.assertEquals(new TwoDimensionPosition(10_000_000 -1,0), TwoDimensionRoomUtils.rectifyCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(10_000_000,0)));
        Assertions.assertEquals(new TwoDimensionPosition(10_000_000 -1,500_000 -1), TwoDimensionRoomUtils.rectifyCoordinatePair(10_000_000, 500_000, new TwoDimensionPosition(10_000_000,500_000)));
    }
}
