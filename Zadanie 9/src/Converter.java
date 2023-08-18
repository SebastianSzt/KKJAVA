import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class Converter
{
    private JTextField exchangeRateField;
    private JTextField amountField;
    private JLabel resultLabel;

    public Converter()
    {
        JFrame frame = new JFrame("Konwerter Walut");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLayout(new GridLayout(3, 2));

        JLabel exchangeRateLabel = new JLabel("Kurs waluty (PLN do waluty):");
        frame.add(exchangeRateLabel);
        exchangeRateField = new JTextField();
        frame.add(exchangeRateField);

        JLabel amountLabel = new JLabel("Kwota w PLN:");
        frame.add(amountLabel);
        amountField = new JTextField();
        frame.add(amountField);

        JButton calculateButton = new JButton("Oblicz");
        frame.add(calculateButton);
        resultLabel = new JLabel("Wynik:");
        frame.add(resultLabel);

        calculateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                calculate();
            }
        });

        frame.setVisible(true);
    }

    private void calculate()
    {
        try
        {
            double exchangeRate = Double.parseDouble(exchangeRateField.getText());
            double amount = Double.parseDouble(amountField.getText());
            double result = amount / exchangeRate;

            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            resultLabel.setText("Wynik: " + decimalFormat.format(result));
        }
        catch (NumberFormatException ex)
        {
            resultLabel.setText("Wynik: Błędne dane");
        }
    }
}
