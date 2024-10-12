package org.kostiskag.topology;

final class TwoDimensionRoomUtils {

    static boolean calculateValidRoomDimension(int dim) {
        return dim >= 0 && dim < Integer.MAX_VALUE;
    }

    static boolean calculateValidCoordinate(int coordinate, int maxExclusive) {
        return coordinate >=0 && coordinate < maxExclusive;
    }

    static boolean calculateValidCoordinatePair(int width, int height, TwoDimensionPosition position) {
        return calculateValidCoordinate(position.x(), width) &&
                calculateValidCoordinate(position.y(), height);
    }

    /**
     * Attempts to rectify a broken coordinate pair to the closest valid spot
     *
     * @param width
     * @param height
     * @param position
     * @return
     */
    static TwoDimensionPosition rectifyCoordinatePair(int width, int height, TwoDimensionPosition position) {
        int x = position.x();
        int y = position.y();

        if (x <0) {
            x = 0;
        } else if (x >= width) {
            x = width-1;
        }

        if (y <0) {
            y = 0;
        } else if (y >= height) {
            y = height-1;
        }

        //create a new coordinate pair only when changed
        return x == position.x() && y == position.y()?
            position:
            new TwoDimensionPosition(x, y);
    }
}
