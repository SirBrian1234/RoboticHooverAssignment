package org.kostiskag;

import org.kostiskag.io.FileUtils;
import org.kostiskag.topology.CalculateRouteInstructionsResponse;
import org.kostiskag.topology.TwoDimensionPosition;
import org.kostiskag.topology.TwoDimensionRoomWithNoObstaclesAndDirtPatches;
import org.kostiskag.topology.TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl;
import org.json.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static org.kostiskag.topology.TwoDimensionPosition.getTwoDimensionPositionFromJSONArrayOfIntCoordinates;


/**
 * Robotic Hoover Assignment
 *
 * @author Konstantinos Kagiampakis
 */
public class App {
    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Please provide a valid file path.");
            return;
        }

        System.out.println("Reading script from file");
        String scriptStr = "";
        try {
            scriptStr = FileUtils.readFromFile(args[0]);
        } catch (IOException e) {
            System.out.println("App wil exit");
            throw new RuntimeException(e);
        }

        //sanitize input elements phase
        JSONObject scriptJsonObject = new JSONObject(scriptStr);

        JSONArray roomSize = scriptJsonObject.getJSONArray("roomSize");
        JSONArray coords = scriptJsonObject.getJSONArray("coords");
        JSONArray patches = scriptJsonObject.getJSONArray("patches");
        String instructions = (String) scriptJsonObject.get("instructions");

        List<TwoDimensionPosition> li = new LinkedList<>();
        for(int i=0; i < patches.length(); i++) {
            JSONArray coordinatePair = patches.getJSONArray(i);
            TwoDimensionPosition patchPosition = getTwoDimensionPositionFromJSONArrayOfIntCoordinates(coordinatePair);
            if (li.contains(patchPosition)) {
                System.out.println("Found duplicate patch tile with coordinates: "+patchPosition+", will not be added again");
            }
            li.add(patchPosition);
        }

        TwoDimensionPosition roomSizeDimensions = getTwoDimensionPositionFromJSONArrayOfIntCoordinates(roomSize);
        TwoDimensionPosition start = getTwoDimensionPositionFromJSONArrayOfIntCoordinates(coords);
        Set<TwoDimensionPosition> tiles = new HashSet<>(li);
        //end of sanitization phase

        TwoDimensionRoomWithNoObstaclesAndDirtPatches room = null;
        try {
            room = new TwoDimensionRoomWithNoObstaclesAndDirtPatchesArrayImpl(start, tiles, roomSizeDimensions.x(), roomSizeDimensions.y());
            CalculateRouteInstructionsResponse result = room.calculateRouteInstructions(instructions);

            System.out.println("Output was calculated to: ");
            System.out.println(result.toJson());

            System.out.println("Will also be stored into file output.json");
            FileUtils.writeResultToFile(result);

        } catch (Exception e) {
            System.out.println(e.getMessage()+" App will exit.");
        }
    }

}
