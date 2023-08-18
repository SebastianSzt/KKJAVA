import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class Chat
{
    private boolean isServer;
    private TCPServer server;
    private TCPClient client;
    private JTextArea chatArea;

    public Chat(String title)
    {
        isServer = title.equals("Server");

        JFrame frame = new JFrame("Chat " + title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        if (isServer)
            frame.setLocation(100,100);
        else
            frame.setLocation(500,100);

        JPanel topPanel = new JPanel(new FlowLayout());

        JTextField messageField = new JTextField(20);
        JButton sendButton = new JButton("Wyślij");

        topPanel.add(messageField);
        topPanel.add(sendButton);

        frame.add(topPanel, BorderLayout.NORTH);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        frame.add(new JScrollPane(chatArea), BorderLayout.CENTER);

        sendButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                String message = messageField.getText();
                if (isServer)
                {
                    chatArea.append("Serwer pisze: " + message + "\n");
                    server.sendMessage(message);
                }
                else
                {
                    chatArea.append("Klient pisze: " + message + "\n");
                    client.sendMessage(message);
                }
                messageField.setText("");
            }
        });

        frame.setVisible(true);

        connectChats();
    }

    private void connectChats()
    {
        String serverName = "localhost";
        int port = 6666;

        if (isServer)
        {
            try
            {
                server = new TCPServer(port, chatArea);
                server.start();
            }
            catch (IOException e)
            {
                chatArea.append("Wystąpił problem z uruchomieniem serwera.\n");
            }
        }
        else
        {
            try
            {
                client = new TCPClient(port, serverName, chatArea);
                client.start();
            }
            catch (IOException e)
            {
                chatArea.append("Wystąpił problem z uruchomieniem klienta.\n");
            }
        }
    }
}
