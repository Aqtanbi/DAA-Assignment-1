import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class ClosestPairSolver {

    public double closestPair(Point[] points) {
        if (points == null || points.length < 2) {
            throw new IllegalArgumentException("At least two points are required");
        }

        Point[] pointsByX = points.clone();
        Point[] pointsByY = points.clone();

        Arrays.sort(pointsByX, Comparator.comparingDouble(Point::getX));
        Arrays.sort(pointsByY, Comparator.comparingDouble(Point::getY));

        return closestPair(pointsByX, pointsByY);
    }

    private double closestPair(Point[] pointsByX, Point[] pointsByY) {
        int n = pointsByX.length;
        if (n <= 3) {
            return bruteForce(pointsByX);
        }
        int middle = n / 2;

        Point[] leftX = Arrays.copyOfRange(pointsByX, 0, middle);
        Point[] rightX = Arrays.copyOfRange(pointsByX, middle, n);

        double middleX = pointsByX[middle].getX();
        Set<Point> leftSet = new HashSet<>(Arrays.asList(leftX));

        Point[] leftY = new Point[leftX.length];
        Point[] rightY = new Point[rightX.length];

        int leftIndex = 0;
        int rightIndex = 0;

        for (Point point : pointsByY) {
            if (leftSet.contains(point)) {
                leftY[leftIndex++] = point;
            } else {
                rightY[rightIndex++] = point;
            }
        }

        double leftDistance = closestPair(leftX, leftY);
        double rightDistance = closestPair(rightX, rightY);

        double minDistance = Math.min(leftDistance, rightDistance);

        Point[] strip = new Point[n];
        int stripSize = 0;

        for (Point point : pointsByY) {
            if (Math.abs(point.getX() - middleX) < minDistance) {
                strip[stripSize++] = point;
            }
        }
        for (int i = 0; i < stripSize; i++) {
            for (int j = i + 1;
                 j < stripSize &&
                         strip[j].getY() - strip[i].getY() < minDistance;
                 j++) {

                minDistance = Math.min(
                        minDistance,
                        pointDistance(strip[i], strip[j])
                );
            }
        }

        return minDistance;
    }

    private double pointDistance(Point a, Point b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();

        return Math.sqrt(dx * dx + dy * dy);
    }

    private double bruteForce(Point[] points) {
        double minDistance = Double.POSITIVE_INFINITY;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                minDistance = Math.min(
                        minDistance,
                        pointDistance(points[i], points[j])
                );
            }
        }

        return minDistance;
    }
}