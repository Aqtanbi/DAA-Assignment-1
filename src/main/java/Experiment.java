import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class Experiment {

    private static final int[] SIZES = {
            100,
            500,
            1000,
            5000,
            10000,
            20000
    };

    private static final int REPETITIONS = 5;

    private static final Random RANDOM = new Random(42);

    public static void main(String[] args) {

        try (PrintWriter writer =
                     new PrintWriter(
                             new FileWriter("results.csv"))) {

            writer.println(
                    "Algorithm,InputType,Size," +
                            "TimeNs,ExtraMetric"
            );

            runSortingExperiments(writer);

            runSelectionExperiments(writer);

            runClosestPairExperiments(writer);

            System.out.println(
                    "Experiments completed."
            );

            System.out.println(
                    "Results saved to results.csv"
            );

        } catch (IOException e) {

            System.out.println(
                    "Error writing CSV: " +
                            e.getMessage()
            );
        }
    }

    private static void runSortingExperiments(
            PrintWriter writer) {

        String[] types = {
                "RANDOM",
                "SORTED",
                "REVERSE"
        };

        for (String type : types) {

            for (int size : SIZES) {

                long totalMergeTime = 0;
                long totalQuickTime = 0;

                for (int repetition = 0;
                     repetition < REPETITIONS;
                     repetition++) {

                    int[] data =
                            generateArray(size, type);

                    // Merge Sort
                    int[] mergeArray =
                            data.clone();

                    MergeSorting mergeSorter =
                            new MergeSorting(size);

                    long start =
                            System.nanoTime();

                    mergeSorter.sort(
                            mergeArray,
                            0,
                            mergeArray.length - 1
                    );

                    long end =
                            System.nanoTime();

                    totalMergeTime +=
                            end - start;

                    // Quick Sort
                    int[] quickArray =
                            data.clone();

                    QuickSorting quickSorter =
                            new QuickSorting();

                    start =
                            System.nanoTime();

                    quickSorter.sort(
                            quickArray,
                            0,
                            quickArray.length - 1
                    );

                    end =
                            System.nanoTime();

                    totalQuickTime +=
                            end - start;
                }

                long averageMergeTime =
                        totalMergeTime / REPETITIONS;

                long averageQuickTime =
                        totalQuickTime / REPETITIONS;

                writer.println(
                        "MergeSort," +
                                type + "," +
                                size + "," +
                                averageMergeTime + "," +
                                "comparisons_not_tracked"
                );

                writer.println(
                        "QuickSort," +
                                type + "," +
                                size + "," +
                                averageQuickTime + "," +
                                "comparisons_not_tracked"
                );

                System.out.println(
                        "Sorting: " +
                                type +
                                " n=" +
                                size
                );
            }
        }
    }

    private static void runSelectionExperiments(
            PrintWriter writer) {

        for (int size : SIZES) {

            long totalTime = 0;

            for (int repetition = 0;
                 repetition < REPETITIONS;
                 repetition++) {

                int[] data =
                        generateArray(
                                size,
                                "RANDOM"
                        );

                int k = size / 2;

                DeterministicSelector selector =
                        new DeterministicSelector();

                long start =
                        System.nanoTime();

                selector.select(data, k);

                long end =
                        System.nanoTime();

                totalTime +=
                        end - start;
            }

            long averageTime =
                    totalTime / REPETITIONS;

            writer.println(
                    "DeterministicSelect," +
                            "RANDOM," +
                            size + "," +
                            averageTime + "," +
                            "k=" + size / 2
            );

            System.out.println(
                    "Selection: n=" +
                            size
            );
        }
    }

    private static void runClosestPairExperiments(
            PrintWriter writer) {

        int[] pointSizes = {
                100,
                500,
                1000,
                2000
        };

        for (int size : pointSizes) {

            long totalTime = 0;

            for (int repetition = 0;
                 repetition < REPETITIONS;
                 repetition++) {

                Point[] points =
                        generatePoints(size);

                ClosestPairSolver solver =
                        new ClosestPairSolver();

                long start =
                        System.nanoTime();

                solver.closestPair(points);

                long end =
                        System.nanoTime();

                totalTime +=
                        end - start;
            }

            long averageTime =
                    totalTime / REPETITIONS;

            writer.println(
                    "ClosestPair," +
                            "RANDOM," +
                            size + "," +
                            averageTime + "," +
                            "points=" + size
            );

            System.out.println(
                    "Closest Pair: n=" +
                            size
            );
        }
    }

    private static int[] generateArray(
            int size,
            String type) {

        int[] array =
                new int[size];

        for (int i = 0; i < size; i++) {
            array[i] =
                    RANDOM.nextInt(1_000_000);
        }

        if (type.equals("SORTED")) {
            Arrays.sort(array);
        }

        if (type.equals("REVERSE")) {
            Arrays.sort(array);

            for (int i = 0;
                 i < size / 2;
                 i++) {

                int temp = array[i];

                array[i] =
                        array[size - 1 - i];

                array[size - 1 - i] =
                        temp;
            }
        }

        return array;
    }

    private static Point[] generatePoints(
            int size) {

        Point[] points =
                new Point[size];

        for (int i = 0; i < size; i++) {

            double x =
                    RANDOM.nextDouble() * 10000;

            double y =
                    RANDOM.nextDouble() * 10000;

            points[i] =
                    new Point(x, y);
        }

        return points;
    }
}