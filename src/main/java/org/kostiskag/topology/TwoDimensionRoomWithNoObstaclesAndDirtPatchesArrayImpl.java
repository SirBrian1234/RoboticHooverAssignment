package org.kostiskag.topology;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;


public class TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl implements TwoDimensionRoomWithNoObstaclesAndDirtPatches {

    private enum RouteInstruction {
        NORTH,
        SOUTH,
        WEST,
        EAST;

        private static RouteInstruction fromValue(char value) {
            if (value == 'N') {
                return RouteInstruction.NORTH;
            } else if (value == 'S') {
                return RouteInstruction.SOUTH;
            } else if (value == 'W') {
                return RouteInstruction.WEST;
            } else if (value == 'E') {
                return RouteInstruction.EAST;
            } else {
                return null;
            }
        }
    }

    private static final int MAX_ALLOWED_ROOM_SIZE = 500_000_000;
    private static final String ROUTE_INSTRUCTIONS_PATTERN = "[NSWE]+";

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
        System.out.println("Created a room of size: "+this.width+", "+this.height);

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
    public CalculateRouteInstructionsResponse calculateRouteInstructions(String instructions) {
        //validate instructions
        if (!instructions.matches(ROUTE_INSTRUCTIONS_PATTERN)) {
            //instructions were unclear
            System.out.println("The given path instructions were unclear, please correct them by using only the 'N', 'S', 'W', 'E' symbols");
            return new CalculateRouteInstructionsResponse(this.hooverStartingPoint,0);
        }

        List<RouteInstruction> instructionList = instructions.chars()
                .mapToObj(i -> RouteInstruction.fromValue((char)i))
                .collect(Collectors.toList());

        List<TwoDimensionPosition> cleanedTiles = new ArrayList<>();

        System.out.println("Starting from:"+ this.hooverStartingPoint);
        int numOfTilesCleaned = 0;
        if (this.room[this.hooverStartingPoint.x()][this.hooverStartingPoint.y()] == TileType.PATCH) {
            cleanedTiles.add(this.hooverStartingPoint);
            System.out.println("Hoover's starting point fell into a patch, the patch is cleaned");
            numOfTilesCleaned++;
        }

        var currentPoint = this.hooverStartingPoint;
        for(var instruction: instructionList) {
            TwoDimensionPosition nextPoint;
            if (instruction == RouteInstruction.NORTH) {
                nextPoint = new TwoDimensionPosition(currentPoint.x(), currentPoint.y()+1);
            } else if (instruction == RouteInstruction.SOUTH) {
                nextPoint = new TwoDimensionPosition(currentPoint.x(), currentPoint.y()-1);
            } else if (instruction == RouteInstruction.WEST) {
                nextPoint = new TwoDimensionPosition(currentPoint.x()-1, currentPoint.y());
            } else {
                nextPoint = new TwoDimensionPosition(currentPoint.x()+1, currentPoint.y());
            }
            System.out.println("Moving to :" + nextPoint);

            var corrected = TwoDimensionRoomUtils.rectifyCoordinatePair(this.width, this.height, nextPoint);
            if (!nextPoint.equals(corrected)) {
                nextPoint = corrected;
                System.out.println("Hoover hit a wall and cannot move further:" + nextPoint);
            }

            if (this.room[nextPoint.x()][nextPoint.y()] == TileType.PATCH
                    && !cleanedTiles.contains(nextPoint)) {

                cleanedTiles.add(nextPoint);
                System.out.println("Cleaning  :" + nextPoint);
                numOfTilesCleaned++;
            }
            currentPoint = nextPoint;
        }

        return new CalculateRouteInstructionsResponse(currentPoint, numOfTilesCleaned);
    }

}
