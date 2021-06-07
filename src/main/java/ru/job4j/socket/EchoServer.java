package ru.job4j.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EchoServer {

    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (BufferedWriter out = new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    String answer = "";
                    String str = in.readLine();
                    while (!(str).isEmpty()) {
                        System.out.println(str);
                        if (str.startsWith("GET") && str.contains("?msg=")) {
                            answer = str.split(" ")[1].split("=")[1];
                        }
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\r\n");
                    out.flush();
                    out.write("Hello, dear friend.\r\n\r\n");
                    out.flush();
                    if (answer.equals("Hello") || answer.equals("What")) {
                        out.write(answer + "\r\n\r\n");
                        out.flush();
                    } else if (answer.equals("Exit")) {
                        server.close();
                    } else {
                        out.write(answer + "\r\n\r\n");
                        out.flush();
                    }
                    socket.close();
                } catch (IOException e) {
                    LOG.debug("Exception in EchoServer NIO stream", e);
                }
            }
        } catch (IOException e) {
            LOG.debug("Exception in EchoServer in ServerSocket", e);
        }
    }
}
