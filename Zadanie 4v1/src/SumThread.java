public class SumThread extends Thread
{
    private int[] array;
    private int sum;

    public SumThread(int[] array)
    {
        this.array = array;
        this.sum = 0;
    }

    public int getSum()
    {
        return sum;
    }

    @Override
    public void run()
    {
        for (int num : array)
        {
            sum += num;
        }
    }
}
