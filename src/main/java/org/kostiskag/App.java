package org.kostiskag;

import org.kostiskag.topology.CalculateRouteInstructionsResponse;
import org.kostiskag.topology.TwoDimensionPosition;
import org.kostiskag.topology.TwoDimensionRoomWithNoObstaclesAndDirtPatches;
import org.kostiskag.topology.TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

/**
 * Robotic Hoover Assignment
 *
 * @author Konstantinos Kagiampakis
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World! "+ Integer.MAX_VALUE);

        //sanitize input elements phase
        TwoDimensionPosition start = new TwoDimensionPosition(10,10);
        Set<TwoDimensionPosition> tiles = new HashSet<>();
        tiles.add(new TwoDimensionPosition(2,3));
        tiles.add(new TwoDimensionPosition(5,7));
        //end of sanitization phase

        TwoDimensionRoomWithNoObstaclesAndDirtPatches room = null;
        try {
            room = new TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl(start, tiles, 10, 500_000);
            CalculateRouteInstructionsResponse result = room.calculateRouteInstructions("SSSNNN");
            System.out.println("Output was calculated to: ");
            System.out.println(result.toJson());
            System.out.println("Will also be stored into file output.json");
            Files.writeString(Path.of("output.json"), result.toJson(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        } catch (Exception e) {
            System.out.println(e.getMessage()+" App will exit.");
        }
    }
}
