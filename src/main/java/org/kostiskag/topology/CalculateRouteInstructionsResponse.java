package org.kostiskag.topology;

public record CalculateRouteInstructionsResponse(TwoDimensionPosition hooverDestination, int numOfTilesCleaned) {

    public CalculateRouteInstructionsResponse(TwoDimensionPosition hooverDestination, int numOfTilesCleaned) {
        this.hooverDestination = hooverDestination;
        this.numOfTilesCleaned = numOfTilesCleaned;
    }

    public CalculateRouteInstructionsResponse(int hooverDestinationX, int hooverDestinationY, int numOfTilesCleaned) {
        this(new TwoDimensionPosition(hooverDestinationX, hooverDestinationY), numOfTilesCleaned);
    }

    public String toJson() {
        return
            """
            {
                "coords": %s,
                "patches": %d
            }
            """.formatted(this.hooverDestination, this.numOfTilesCleaned);
    }

    @Override
    public String toString() {
        return hooverDestination + ", patches: " + numOfTilesCleaned;
    }
}
