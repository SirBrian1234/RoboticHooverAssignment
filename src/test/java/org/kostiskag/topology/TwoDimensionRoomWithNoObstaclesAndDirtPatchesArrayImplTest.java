package org.kostiskag.topology;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImplTest {

    //uncomment to use
    //@Test
    public void testTest() {
        TwoDimensionPosition start = new TwoDimensionPosition(10,10);
        Set<TwoDimensionPosition> tiles = new HashSet<>();
        tiles.add(new TwoDimensionPosition(2,3));
        tiles.add(new TwoDimensionPosition(5,7));

        TwoDimensionRoomWithNoObstaclesAndDirtPatches room = null;
        try {
            room = new TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl(start, tiles, 10, 500_000);

            CalculateRouteInstructionsResponse result = room.calculateRouteInstructions("SSSNNN04141");
            System.out.println(result.toJson());

            result = room.calculateRouteInstructions("SS_SN_NN");
            System.out.println(result.toJson());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //uncomment to use
    //@Test
    public void test2Test() {
        TwoDimensionPosition start = new TwoDimensionPosition(1,3);
        Set<TwoDimensionPosition> tiles = new HashSet<>();
        tiles.add(new TwoDimensionPosition(1,3));
        tiles.add(new TwoDimensionPosition(2,3));
        tiles.add(new TwoDimensionPosition(5,7));

        TwoDimensionRoomWithNoObstaclesAndDirtPatches room = null;
        try {
            room = new TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl(start, tiles, 10, 500_000, true);

            CalculateRouteInstructionsResponse result = room.calculateRouteInstructions("EWNNNNEEEE");
            System.out.println(result.toJson());

            result = room.calculateRouteInstructions("EWNNNNEEEE");
            System.out.println(result.toJson());

            result = room.calculateRouteInstructions("WWWW");
            System.out.println(result.toJson());

            result = room.calculateRouteInstructions("WWWW");
            System.out.println(result.toJson());

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
