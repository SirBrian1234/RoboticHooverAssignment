package org.kostiskag.topology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class CalculateRouteInstructionsResponseTest {

    @Test
    public void toStringTest() {
        CalculateRouteInstructionsResponse response = new CalculateRouteInstructionsResponse(10, 10, 10);
        Assertions.assertEquals(response.toString(), "[10, 10], patches: 10");
    }

    @Test
    public void toJsonTest() {
        CalculateRouteInstructionsResponse response = new CalculateRouteInstructionsResponse(10, 10, 10);
        Assertions.assertEquals(response.toJson(), """
                {
                    "coords": [10, 10],
                    "patches": 10
                }
                """);
    }
}
