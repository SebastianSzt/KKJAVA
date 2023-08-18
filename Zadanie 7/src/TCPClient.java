import javax.swing.*;
import java.io.*;
import java.net.*;

public class TCPClient extends Thread
{
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private JTextArea chatArea;

    public TCPClient(int port, String serverName, JTextArea chatArea) throws IOException
    {
        client = new Socket(serverName, port);
        this.chatArea = chatArea;
    }

    public void run()
    {
        try
        {
            chatArea.append("Połączenie z " + client.getInetAddress().getHostAddress() + " na porcie " + client.getPort() + "\n");
            chatArea.append("Połączono z " + client.getRemoteSocketAddress() + "\n");

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());

            while (true)
            {
                String receivedMessage = in.readUTF();
                updateChatArea(receivedMessage);
            }
        }
        catch (IOException e )
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (client != null)
                    client.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void updateChatArea(String message)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                chatArea.append("Serwer pisze: " + message + "\n");
            }
        });
    }

    public void sendMessage(String message)
    {
        try
        {
            out.writeUTF(message);
        }
        catch (IOException e)
        {
            chatArea.append("Wystąpił błąd przy wysyłaniu wiadomości.\n");
            e.printStackTrace();
        }
    }
}
