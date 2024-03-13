package ru.job4j.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String string = input.readLine();
                    if (string != null && !string.isEmpty()) {
                        if ("GET".equals(string.split(" ")[0])) {
                            switch (string.substring(string.indexOf("/?msg=") + 6, string.indexOf(" HTTP/1.1"))) {
                                case "Hello" -> {
                                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                    output.write("Hello, dear friend.".getBytes());
                                }
                                case "Exit" -> server.close();
                                default -> {
                                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                                    output.write("What?".getBytes());
                                }
                            }
                        }
                    }
                    output.flush();
                }
            }
        }
    }
}
