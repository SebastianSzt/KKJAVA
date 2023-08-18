import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Matrix
{
    public static void Convert(double[][] matrix)
    {
        int numRows = matrix.length;
        int numCols = matrix[0].length;
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (int pivotRow = 0; pivotRow < numRows - 1; pivotRow++)
        {
            final int pivot = pivotRow;
            for (int row = pivotRow + 1; row < numRows; row++)
            {
                final int targetRow = row;
                executor.execute(() -> {
                    double factor = matrix[targetRow][pivot] / matrix[pivot][pivot];
                    for (int col = pivot; col < numCols; col++)
                    {
                        matrix[targetRow][col] -= factor * matrix[pivot][col];
                    }
                });
            }
        }

        executor.shutdown();
        while (!executor.isTerminated()) { }
    }
}
