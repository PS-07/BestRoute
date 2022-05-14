package models;

import constants.Constants;

public class HaversineDistance implements Distance {

    @Override
    public Double calculateDistance(final Location a, final Location b) {
        final double radius = Constants.EARTH_RADIUS;

        double dLat = Math.toRadians(b.getLatitude() - a.getLatitude());
        double dLon = Math.toRadians(b.getLongitude() - a.getLongitude());

        double x = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(Math.toRadians(a.getLatitude())) *
                        Math.cos(Math.toRadians(b.getLatitude()));

        double c = 2 * Math.asin(Math.sqrt(x));
        return radius * c;

    }
}
