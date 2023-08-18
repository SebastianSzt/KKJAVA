import javax.swing.*;
import java.io.*;
import java.net.*;

public class TCPServer extends Thread
{
    private ServerSocket serverSocket;
    private DataInputStream in;
    private DataOutputStream out;
    private JTextArea chatArea;

    public TCPServer(int port, JTextArea chatArea) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(1000);
        this.chatArea = chatArea;
    }

    public void run()
    {
        try
        {
            chatArea.append("Oczekiwanie na klienta na porcie " + serverSocket.getLocalPort() + "...\n");
            Socket server = serverSocket.accept();
            chatArea.append("Połączenie z klientem " + server.getRemoteSocketAddress() + "\n");

            in = new DataInputStream(server.getInputStream());
            out = new DataOutputStream(server.getOutputStream());

            while (true)
            {
                String receivedMessage = in.readUTF();
                updateChatArea(receivedMessage);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (serverSocket != null)
                    serverSocket.close();
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
                chatArea.append("Klient pisze: " + message + "\n");
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
