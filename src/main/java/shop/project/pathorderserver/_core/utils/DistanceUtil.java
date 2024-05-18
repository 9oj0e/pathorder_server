package shop.project.pathorderserver._core.utils;

public class DistanceUtil {

    private static final double EARTH_RADIUS_KM = 6371.0;

    public static int calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                   + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                     * Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceInKm = EARTH_RADIUS_KM * c;
        double distanceInMeters = distanceInKm * 1000;
        return (int) Math.round(distanceInMeters); // 미터로 변환 후 반올림
    }
}
