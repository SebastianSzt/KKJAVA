public class Main
{
    public static void main(String[] args)
    {
        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6}
        };

        int[][] matrix2 = {
                {7, 8},
                {9, 10},
                {11, 12}
        };

        try
        {
            int[][] result = MatrixMultiplier.multiply(matrix1, matrix2);

            for (int[] row : result)
            {
                for (int num : row)
                {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
        catch (IllegalArgumentException e)
        {
            System.out.println("Nie można pomnożyć macierzy: " + e.getMessage());
        }
    }
}