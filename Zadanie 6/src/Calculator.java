import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;


public class Calculator extends JFrame
{
    private JTextField resultField;
    private double currentValue = 0;
    private String currentOperator = "";
    private boolean newInput = true;

    public Calculator()
    {
        setTitle("Kalkulator");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        resultField = new JTextField("0");
        resultField.setFont(new Font("Arial", Font.PLAIN, 40));
        resultField.setHorizontalAlignment(JTextField.RIGHT);
        resultField.setEditable(false);
        add(resultField, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(4, 4));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String button : buttons)
        {
            JButton tmpButton = new JButton(button);
            tmpButton.setFont(new Font("Arial", Font.PLAIN, 18));
            buttonsPanel.add(tmpButton);
            tmpButton.addActionListener(new ButtonClickListener());
        }

        add(buttonsPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String command = e.getActionCommand();

            if (command.matches("[0-9.]"))
            {
                if (newInput)
                {
                    resultField.setText("");
                    newInput = false;
                }
                resultField.setText(resultField.getText() + command);
            }
            else if (command.matches("[-/*+=]"))
            {
                if (!currentOperator.isEmpty() && !newInput)
                {
                    calculate();
                    newInput = true;
                }
                if (!command.equals("="))
                {
                    currentOperator = command;
                    currentValue = Double.parseDouble(resultField.getText());
                    newInput = true;
                }
            }
        }
    }

    private void calculate()
    {
        double newValue = Double.parseDouble(resultField.getText());
        switch (currentOperator)
        {
            case "+":
                currentValue += newValue;
                break;
            case "-":
                currentValue -= newValue;
                break;
            case "*":
                currentValue *= newValue;
                break;
            case "/":
                if (newValue != 0)
                {
                    currentValue /= newValue;
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Nie można dzielić przez zero.", "Błąd", JOptionPane.ERROR_MESSAGE);
                    currentValue = 0;
                }
                break;
        }
        resultField.setText(formatValue(currentValue));
        currentOperator = "";
    }

    private String formatValue(double value)
    {
        if (value == (int) value)
        {
            return String.valueOf((int) value);
        }
        else
        {
            BigDecimal bigDecimal = new BigDecimal(value);
            bigDecimal = bigDecimal.setScale(8, BigDecimal.ROUND_HALF_UP);
            return bigDecimal.stripTrailingZeros().toString();
        }
    }
}
