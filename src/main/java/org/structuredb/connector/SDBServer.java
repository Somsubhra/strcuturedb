package org.structuredb.connector;

import org.structuredb.query.data.RawQuery;
import org.structuredb.query.handler.QueryExecutor;
import org.structuredb.structure.Structure;
import org.structuredb.utils.Console;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class SDBServer extends Thread {

    private String host;

    private Integer port;

    private Integer poolSize;

    private ServerSocket serverSocket;

    private QueryExecutor queryExecutor;

    public SDBServer(String host, Integer port, Integer poolSize) throws IOException {
        this.host = host;
        this.port = port;
        this.poolSize = poolSize;
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress(this.host, this.port));
        this.queryExecutor = new QueryExecutor(poolSize);
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

            String query;

            while((query = socketReader.readLine()) != null) {
                RawQuery rawQuery = new RawQuery(query);
                Console.query(rawQuery);
                Structure structure = queryExecutor.handle(rawQuery);
                socketWriter.write(structure.serialize() + "\n");
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

    public Integer getPort() {
        return port;
    }

    public Integer getPoolSize() {
        return poolSize;
    }
}
