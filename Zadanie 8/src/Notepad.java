import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Notepad
{
    private JTextArea notepadArea;

    public Notepad()
    {
        JFrame frame = new JFrame("Notatnik");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 750);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("Plik");
        menuBar.add(fileMenu);

        JMenuItem newMenuItem = new JMenuItem("Nowy");
        fileMenu.add(newMenuItem);
        JMenuItem saveMenuItem = new JMenuItem("Zapisz");
        fileMenu.add(saveMenuItem);
        JMenuItem openMenuItem = new JMenuItem("Otwórz");
        fileMenu.add(openMenuItem);

        notepadArea = new JTextArea();
        frame.add(notepadArea);
        JScrollPane sp = new JScrollPane(notepadArea);
        frame.add(sp);

        newMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                notepadArea.setText("");
            }
        });

        saveMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    saveFile(file);
                }
            }
        });

        openMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File file = fileChooser.getSelectedFile();
                    openFile(file);
                }
            }
        });

        frame.setVisible(true);
    }

    private void saveFile(File file)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file)))
        {
            String content = notepadArea.getText();
            writer.write(content);
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas zapisywania pliku.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openFile(File file)
    {
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            StringBuilder content = new StringBuilder();
            String line;

            if ((line = reader.readLine()) != null)
                content.append(line);

            while ((line = reader.readLine()) != null)
                content.append("\n").append(line);

            notepadArea.setText(content.toString());
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wystąpił błąd podczas otwierania pliku.", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
