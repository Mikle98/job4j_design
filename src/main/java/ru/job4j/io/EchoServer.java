package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        boolean isClosed = false;
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!isClosed) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    for (String string = input.readLine(); string != null && !string.isEmpty(); string = input.readLine()) {
                        if ("GET".equals(string.split(" ")[0])
                            && "Bye".equals(string.substring(string.indexOf("/?msg=") + 6, string.indexOf(" HTTP/1.1")))) {
                            isClosed = true;
                        }
                        System.out.println(string);
                    }
                    output.flush();
                }
            }
        }
    }
}
