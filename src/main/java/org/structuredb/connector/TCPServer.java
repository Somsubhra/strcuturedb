package org.structuredb.connector;

import org.structuredb.utils.Console;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class TCPServer extends Thread {

    private String host;

    private String port;

    private ServerSocket serverSocket;

    public TCPServer(String host, String port) throws IOException {
        this.host = host;
        this.port = port;
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(this.host, Integer.parseInt(this.port)));
    }

    @Override
    public void run() {
        while(true) {
            try {
                final Socket socket = serverSocket.accept();
                Console.connection("Incoming connection from " + socket);

                Runnable runnable = () -> handleRequests(socket);
                new Thread(runnable).start();
            } catch (SocketTimeoutException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void handleRequests(Socket socket) {
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter socketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String query = null;

            while((query = socketReader.readLine()) != null) {
                Console.query(query);
                socketWriter.write("ACK\n");
                socketWriter.flush();
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
