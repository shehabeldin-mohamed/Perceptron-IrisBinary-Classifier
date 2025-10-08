import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java Perceptron <learning_rate> <train_file> <test_file>");
            return;
        }

        try {
            double learningRate = Double.parseDouble(args[0]);
            String trainFile = args[1];
            String testFile = args[2];

            Perceptron perceptron = new Perceptron(learningRate, 1000);

            double[][] X_train = loadDataset(trainFile);
            int[] y_train = loadLabels(trainFile);
            double[][] X_test = loadDataset(testFile);
            int[] y_test = loadLabels(testFile);

            perceptron.train(X_train, y_train);
            double accuracy = perceptron.accuracy(X_test, y_test);
            System.out.printf("Test Set Accuracy: %.2f%%\n", accuracy);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                manualInput(perceptron, X_train[0].length);
                System.out.print("Test another? (y/n): ");
                if (!scanner.next().equalsIgnoreCase("y")) break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter numeric values correctly.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    private static double[][] loadDataset(String filename) throws IOException {
        List<double[]> data = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            double[] row = new double[values.length - 1];
            for (int i = 0; i < values.length - 1; i++) {
                row[i] = Double.parseDouble(values[i]);
            }
            data.add(row);
        }
        br.close();
        return data.toArray(new double[0][0]);
    }

    private static int[] loadLabels(String filename) throws IOException {
        List<Integer> labels = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            labels.add(values[values.length - 1].equals("Iris-virginica") ? 1 : 0);
        }
        br.close();
        return labels.stream().mapToInt(i -> i).toArray();
    }

    private static void manualInput(Perceptron perceptron, int numFeatures) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nEnter a feature vector as comma-separated :");
        String input = scanner.nextLine();

        try {
            double[] vector = Arrays.stream(input.split(","))
                    .mapToDouble(Double::parseDouble)
                    .toArray();

            // Validate the number of features
            if (vector.length != numFeatures) {
                System.out.println("Error: Expected " + numFeatures + " features, but got " + vector.length);
                return;
            }

            // Debugging: Print the parsed vector
            System.out.println("Vector Entered: " + Arrays.toString(vector));

            // Make a prediction
            int prediction = perceptron.predict(vector);
            System.out.println("Raw Prediction Output: " + prediction);

            // Map prediction to class name
            String species = (prediction == 1) ? "Iris-virginica" : "Iris-versicolor";
            System.out.println("✅ Predicted Class: " + species);

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter numeric values separated by commas.");
        }
    }
}

