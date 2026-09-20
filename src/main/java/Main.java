import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        testMergeSort();

        testQuickSort();

        testDeterministicSelect();

        testClosestPair();

        testClosestPairRandom();

        System.out.println(
                "\nAll tests completed."
        );
    }

    private static void testMergeSort() {

        int[] array = {
                5, 2, 8, 1, 3, 9, 4
        };

        int[] expected =
                array.clone();

        Arrays.sort(expected);

        MergeSorting sorter =
                new MergeSorting(array.length);

        sorter.sort(
                array,
                0,
                array.length - 1
        );

        System.out.println(
                "MergeSort: " +
                        Arrays.equals(array, expected)
        );
    }

    private static void testQuickSort() {

        int[] array = {
                7, 3, 9, 1, 5, 2, 8
        };

        int[] expected =
                array.clone();

        Arrays.sort(expected);

        QuickSorting sorter =
                new QuickSorting();

        sorter.sort(
                array,
                0,
                array.length - 1
        );

        System.out.println(
                "QuickSort: " +
                        Arrays.equals(array, expected)
        );
    }

    private static void testDeterministicSelect() {

        Random random =
                new Random();

        DeterministicSelector selector =
                new DeterministicSelector();

        boolean passed = true;

        for (int test = 0;
             test < 100;
             test++) {

            int size =
                    1 + random.nextInt(100);

            int[] array =
                    new int[size];

            for (int i = 0;
                 i < size;
                 i++) {

                array[i] =
                        random.nextInt(1000);
            }

            int[] sorted =
                    array.clone();

            Arrays.sort(sorted);

            for (int k = 0;
                 k < size;
                 k++) {

                int[] copy =
                        array.clone();

                int result =
                        selector.select(copy, k);

                if (result != sorted[k]) {

                    passed = false;

                    System.out.println(
                            "Select failed: k=" +
                                    k +
                                    ", expected=" +
                                    sorted[k] +
                                    ", result=" +
                                    result
                    );

                    return;
                }
            }
        }

        System.out.println(
                "Deterministic Select: " +
                        passed
        );
    }

    private static void testClosestPair() {

        Point[] points = {

                new Point(0, 0),
                new Point(5, 5),
                new Point(1, 1),
                new Point(10, 10),
                new Point(1, 2)
        };

        ClosestPairSolver solver =
                new ClosestPairSolver();

        double result =
                solver.closestPair(points);

        double expected = 1.0;

        boolean passed =
                Math.abs(
                        result - expected
                ) < 1e-9;

        System.out.println(
                "Closest Pair: " +
                        passed +
                        " | distance = " +
                        result
        );
    }

    private static void testClosestPairRandom() {

        Random random =
                new Random();

        ClosestPairSolver solver =
                new ClosestPairSolver();

        boolean passed = true;

        for (int test = 0;
             test < 20;
             test++) {

            int n =
                    2 + random.nextInt(1999);

            Point[] points =
                    new Point[n];

            for (int i = 0;
                 i < n;
                 i++) {

                double x =
                        random.nextDouble() * 10000;

                double y =
                        random.nextDouble() * 10000;

                points[i] =
                        new Point(x, y);
            }

            double result =
                    solver.closestPair(points);

            double expected =
                    bruteForce(points);

            if (Math.abs(
                    result - expected
            ) > 1e-9) {

                passed = false;

                System.out.println(
                        "Closest Pair failed on test " +
                                test
                );

                break;
            }
        }

        System.out.println(
                "Closest Pair random tests: " +
                        passed
        );
    }

    private static double bruteForce(
            Point[] points) {

        double minDistance =
                Double.POSITIVE_INFINITY;

        for (int i = 0;
             i < points.length;
             i++) {

            for (int j = i + 1;
                 j < points.length;
                 j++) {

                double dx =
                        points[i].getX() -
                                points[j].getX();

                double dy =
                        points[i].getY() -
                                points[j].getY();

                double distance =
                        Math.sqrt(
                                dx * dx +
                                        dy * dy
                        );

                minDistance =
                        Math.min(
                                minDistance,
                                distance
                        );
            }
        }

        return minDistance;
    }
}