import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Interface extends Frame
{
    private String fileName = "wizytowki";
    private static TextArea textArea;
    public Interface()
    {
        setTitle("Wizytówki");
        setSize(600,400);

        Panel fileNamePanel = new Panel();
        fileNamePanel.setLayout(new FlowLayout());
        add(fileNamePanel, BorderLayout.NORTH);

        Label fileNameLabel1 = new Label("Nazwa pliku:");
        TextField fileNameInputField = new TextField(fileName, 20);
        Label fileNameLabel2 = new Label(".csv");
        Button changeFileNameButton = new Button("Zmień nazwę");

        fileNamePanel.add(fileNameLabel1);
        fileNamePanel.add(fileNameInputField);
        fileNamePanel.add(fileNameLabel2);
        fileNamePanel.add(changeFileNameButton);

        Panel mainPanel = new Panel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        Panel buttonsPanel = new Panel();
        buttonsPanel.setLayout(new FlowLayout());
        mainPanel.add(buttonsPanel, BorderLayout.NORTH);

        Button displayButton = new Button("Wyświetl wizytówki");
        Button addButton = new Button("Dodaj wizytówkę");
        Button searchButton = new Button("Szukaj wizytówki");
        Button exitButton = new Button("Zakończ");

        buttonsPanel.add(displayButton);
        buttonsPanel.add(addButton);
        buttonsPanel.add(searchButton);
        buttonsPanel.add(exitButton);

        textArea = new TextArea();
        textArea.setEditable(false);
        mainPanel.add(textArea, BorderLayout.CENTER);

        changeFileNameButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                fileName = fileNameInputField.getText();
            }
        });

        displayButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String result = readFile("Zadanie 5v1/src/" + fileName + ".csv");

                if (!result.isEmpty())
                {
                    String[] lines = result.split("\r\n");
                    textArea.setText("");

                    for (String line : lines)
                    {
                        String[] fields = line.split(";");
                        textArea.append("Imię: " + fields[0] + "\n");
                        textArea.append("Nazwisko: " + fields[1] + "\n");
                        textArea.append("Telefon: " + fields[2] + "\n");
                        textArea.append("Email: " + fields[3] + "\n\n");
                    }
                }
            }
        });

        addButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Frame addFrame = new Frame("Dodaj nową wizytówkę");
                addFrame.setSize(300, 200);
                addFrame.setLayout(new GridLayout(5, 2));

                Label firstNameLabel = new Label("Imię:");
                TextField firstNameField = new TextField();
                Label lastNameLabel = new Label("Nazwisko:");
                TextField lastNameField = new TextField();
                Label phoneLabel = new Label("Telefon:");
                TextField phoneField = new TextField();
                Label emailLabel = new Label("Email:");
                TextField emailField = new TextField();
                Button addButton = new Button("Dodaj");
                Button cancelButton = new Button("Anuluj");

                addButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String newLine = firstNameField.getText() + ";" + lastNameField.getText() + ";" + phoneField.getText() + ";" + emailField.getText() + "\n";
                        writeFile("Zadanie 5v1/src/" + fileName + ".csv", newLine);
                        addFrame.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        addFrame.dispose();
                    }
                });

                addFrame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent windowEvent)
                    {
                        addFrame.dispose();
                    }
                });

                addFrame.add(firstNameLabel);
                addFrame.add(firstNameField);
                addFrame.add(lastNameLabel);
                addFrame.add(lastNameField);
                addFrame.add(phoneLabel);
                addFrame.add(phoneField);
                addFrame.add(emailLabel);
                addFrame.add(emailField);
                addFrame.add(addButton);
                addFrame.add(cancelButton);

                addFrame.setVisible(true);
            }
        });

        searchButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                Frame searchFrame = new Frame("Szukaj wizytówki");
                searchFrame.setSize(300, 100);
                searchFrame.setLayout(new GridLayout(2, 2));

                Label searchLabel = new Label("Podaj nazwisko:");
                TextField searchField = new TextField();
                Button searchButton = new Button("Szukaj");
                Button cancelButton = new Button("Anuluj");

                searchButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        String search = readFile("Zadanie 5v1/src/" + fileName + ".csv");
                        if (!search.isEmpty())
                        {
                            textArea.setText("");

                            int i = 0;

                            String[] Lines = search.split("\r\n");

                            for (String Line : Lines)
                            {
                                String[] fields = Line.split(";");
                                if (fields[1].equals(searchField.getText()))
                                {
                                    textArea.append("Imię: " + fields[0] + "\n");
                                    textArea.append("Nazwisko: " + fields[1] + "\n");
                                    textArea.append("Telefon: " + fields[2] + "\n");
                                    textArea.append("Email: " + fields[3] + "\n\n");
                                    i++;
                                }
                            }

                            if (i == 0)
                                textArea.setText("Brak wyników, nie znaleziono wizytówek z takim nazwiskiem.");
                        }

                        searchFrame.dispose();
                    }
                });

                cancelButton.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        searchFrame.dispose();
                    }
                });

                searchFrame.addWindowListener(new WindowAdapter()
                {
                    public void windowClosing(WindowEvent windowEvent)
                    {
                        searchFrame.dispose();
                    }
                });

                searchFrame.add(searchLabel);
                searchFrame.add(searchField);
                searchFrame.add(searchButton);
                searchFrame.add(cancelButton);

                searchFrame.setVisible(true);
            }
        });

        exitButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
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

    public static String readFile(String fileName)
    {
        String result = "";
        try
        {
            File myFile = new File(fileName);
            Scanner myScanner = new Scanner(myFile);
            while ( myScanner.hasNextLine() )
            {
                result += (myScanner.nextLine() + "\r\n");
            }
            myScanner.close();
        }
        catch (FileNotFoundException e)
        {
            textArea.setText("Ooops... Plik jeszcze nie istnieje. ");
            e.printStackTrace();
        }
        return result;
    }

    public static void writeFile(String fileName, String line)
    {
        try
        {
            FileWriter fw = new FileWriter(fileName, true);
            fw.write(line);
            fw.flush();
            fw.close();
        }
        catch (IOException e)
        {
            textArea.setText("Ooops... Wystąpił błąd: ");
            e.printStackTrace();
        }
    }
}
