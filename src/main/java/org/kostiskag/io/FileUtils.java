package org.kostiskag.io;

import org.kostiskag.topology.CalculateRouteInstructionsResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

/**
 *
 * @author Konstantinos Kagiampakis
 */
public class FileUtils {

    private static final String OUTPUT_FILENAME = "output.json";

    public static String readFromFile(String inputFile) throws IOException {
        var path = Path.of(inputFile);
        if (!Files.isRegularFile(path)) {
            System.out.println();
            throw new IOException("File does not exist in the defined path. " + path.toString());
        }
        return Files.readString(path);
    }

    public static void writeResultToFile(CalculateRouteInstructionsResponse result) throws IOException {
        Files.writeString(Path.of("output.json"), result.toJson(), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
    }

}
