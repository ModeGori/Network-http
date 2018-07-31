package com.MEGR.http;
import java.io.*;
import java.net.*;
public class Server {
	private static final int PORT = 8080;
    public static void main(String[] args) {
        try {
            @SuppressWarnings("resource")
			ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server active " + PORT);
            while (true) {
                new ThreadS(server.accept());
            }
        } catch (Exception e) {
        }
    }
}
