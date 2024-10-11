package org.kostiskag.topology;

import java.util.Objects;

/**
 * This is a single 2D position
 * It can be defined by providing two coordinates,
 *   one for the x axis and one for the y
 *
 * One position is equal with another
 *   if they have the same x and y coordinates
 *
 * @param x
 * @param y
 */
public record TwoDimensionPosition(int x, int y) {

    public TwoDimensionPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This is a clone constructior, to copy a TwoDimensionPosition object
     * @param pos
     */
    public TwoDimensionPosition(TwoDimensionPosition pos) {
        this(pos.x(), pos.y());
    }

    @Override
    public String toString() {
        return "[" + this.x + ", " + this.y + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TwoDimensionPosition that = (TwoDimensionPosition) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
