import java.awt.*;
import java.awt.event.*;

public class Timer extends Frame
{
    private int initialTime = 0;
    private int remainingTime = 0;
    private boolean counting = false;

    private Thread countdownThread;

    public Timer()
    {
        setTitle("Minutnik");
        setSize(300, 150);
        setLayout(new FlowLayout());

        Label timerLabel = new Label("Pozostały czas w sekundach: " + remainingTime);
        add(timerLabel);

        TextField inputField = new TextField(10);
        add(inputField);

        Button startButton = new Button("Start");
        add(startButton);

        Button stopButton = new Button("Stop");
        add(stopButton);

        Button resetButton = new Button("Reset");
        add(resetButton);

        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                if (remainingTime == 0 || initialTime != Integer.parseInt(inputField.getText()))
                {
                    initialTime = Integer.parseInt(inputField.getText());
                    remainingTime = initialTime;
                    counting = false;
                    if (countdownThread != null)
                        countdownThread.interrupt();
                    setBackground(Color.WHITE);
                }

                if (!counting)
                {
                    counting = true;
                    countdownThread = new Thread(new Runnable()
                    {
                        public void run()
                        {
                            while (remainingTime > 0 && counting)
                            {
                                timerLabel.setText("Pozostały czas w sekundach: " + remainingTime);
                                try
                                {
                                    Thread.sleep(1000);
                                    remainingTime--;
                                }
                                catch (InterruptedException e)
                                {
                                    Thread.currentThread().interrupt();
                                }
                            }
                            if (remainingTime == 0)
                            {
                                counting = false;
                                timerLabel.setText("Minutnik zakończony!");
                                setBackground(Color.GREEN);
                            }
                        }
                    });
                    countdownThread.start();
                }
            }
        });

        stopButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                counting = false;
                if (countdownThread != null)
                    countdownThread.interrupt();
            }
        });

        resetButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                counting = false;
                if (countdownThread != null)
                    countdownThread.interrupt();
                remainingTime = initialTime;
                timerLabel.setText("Pozostały czas w sekundach: " + remainingTime);
                setBackground(Color.WHITE);
            }
        });

        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowEvent)
            {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}