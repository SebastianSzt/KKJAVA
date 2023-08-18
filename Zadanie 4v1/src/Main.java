import java.util.Scanner;
import java.util.Random;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj liczbę tablic: ");
        int numArrays = scanner.nextInt();

        System.out.print("Podaj długość tablicy: ");
        int arrayLength = scanner.nextInt();

        int[][] arrays = new int[numArrays][arrayLength];
        Random random = new Random();

        for (int i = 0; i < numArrays; i++)
        {
            for (int j = 0; j < arrayLength; j++)
            {
                arrays[i][j] = random.nextInt(100);
            }
        }

        SumThread[] threads = new SumThread[numArrays];

        for (int i = 0; i < numArrays; i++)
        {
            threads[i] = new SumThread(arrays[i]);
            threads[i].start();
        }

        try
        {
            for (int i = 0; i < numArrays; i++)
            {
                threads[i].join();
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        int totalSum = 0;
        for (int i = 0; i < numArrays; i++)
        {
            totalSum += threads[i].getSum();
        }

        System.out.println("Suma wszystkich elementów: " + totalSum);
    }
}