package org.kostiskag.room;

import java.math.BigInteger;
import java.util.Set;

public class TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl implements TwoDimensionRoomWithNoObstaclesAndDirtPatches {

    private static final int MAX_ALLOWED_ROOM_SIZE = 500_000_000;

    private final int width;
    private final int height;
    private final TileType[][] room;

    private final TwoDimensionPosition hooverStartingPoint;

    public TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl(TwoDimensionPosition hooverStartingPoint, Set<TwoDimensionPosition> dirtPatches, int width, int height) throws RoomException {

        //we can't recover from bad dimensions
        //element construction will fail
        if (!TwoDimensionRoomUtils.calculateValidRoomDimension(width)) {
            throw new RoomException("Could not init Room. The given room width was out of bounds");
        }
        if (!TwoDimensionRoomUtils.calculateValidRoomDimension(height)) {
            throw new RoomException("Could not init Room. The given room height was out of bounds");
        }
        if (BigInteger.valueOf(width).multiply(BigInteger.valueOf(height)).compareTo(BigInteger.valueOf(MAX_ALLOWED_ROOM_SIZE)) > 0) {
            throw new RoomException("Could not init Room. The given room exceeds the maximum allowed room size of "+MAX_ALLOWED_ROOM_SIZE+" floor tiles");
        }

        this.width = width;
        this.height = height;
        this.room = new TileType[width][height];

        for(int x=0; x<width; x++) {
            for(int y=0; y<height; y++) {
                this.room[x][y] = TileType.PLAIN;
            }
        }

        //we can recover from a bad dirt tile
        //but we will not try to rectify it
        for(TwoDimensionPosition dirt: dirtPatches) {
            if (!TwoDimensionRoomUtils.calculateValidCoordinatePair(this.width, this.height, dirt)) {
                //we want to recover so there will be a silent handling
                System.out.println("Tile position "+dirt+" was invalid and will not be included in the room");
            } else {
                //valid
                this.room[dirt.x()][dirt.y()] = TileType.PATCH;
            }
        }

        //we can recover from a bad starting point,
        //the handling would be to reset a bad coordinate to the closest possible position
        //ex if we had (4,100) in a 50,50 map the position will be corrected to (4,49)
        if (!TwoDimensionRoomUtils.calculateValidCoordinatePair(width, height, hooverStartingPoint)) {
            hooverStartingPoint = TwoDimensionRoomUtils.rectifyCoordinatePair(this.width, this.height, hooverStartingPoint);
            System.out.println("Starting point was invalid and was corrected to: " + hooverStartingPoint);
        }
        this.hooverStartingPoint = hooverStartingPoint;
    }

    /**
     * The room is initialized only once
     * but multiple routes may be calculated on the same map
     *
     * @param instructions
     */
    public void calculateRouteInstructions(String instructions) {

    }

}
