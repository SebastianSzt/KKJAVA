public class Main {
    public static void main(String[] args)
    {
        double[][] matrix = {
                {3, 2, 1},
                {1, 3, 2},
                {2, 1, 3}
        };

        Matrix.Convert(matrix);

        for (double[] row : matrix)
        {
            for (double num : row)
            {
                System.out.print(String.format("%.1f  ", num));
            }
            System.out.println();
            System.out.println();
        }
    }
}