package com.MEGR.http;
import java.io.*;
import java.net.*;
public class ThreadS extends Thread {

	    private Socket insocket;
	    ThreadS(Socket insocket) {
	        this.insocket = insocket;
	        this.start();
	    }
	    
	    @Override
	    public void run() {
	        try {
	            InputStream is = insocket.getInputStream();
	            PrintWriter out = new PrintWriter(insocket.getOutputStream());
	            BufferedReader in = new BufferedReader(new InputStreamReader(is));
	            String line;
	            line = in.readLine();
	            String request_method = line;
	            System.out.println("HTTP-HEADER: " + line);
	            line = "";
	            // Busca post data
	            int postDataI = -1;
	            while ((line = in.readLine()) != null && (line.length() != 0)) {
	                System.out.println("HTTP-HEADER: " + line);
	                if (line.indexOf("Content-Length:") > -1) {
	                    postDataI = new Integer(
	                            line.substring(
	                                    line.indexOf("Content-Length:") + 16,
	                                    line.length())).intValue();
	                }
	            }
	            String postData = "";
	            // Lee el post data
	            if (postDataI > 0) {
	                char[] charArray = new char[postDataI];
	                in.read(charArray, 0, postDataI);
	                postData = new String(charArray);
	            }
	            out.println("HTTP/1.0 200 OK");
	            out.println("Content-Type: text/html; charset=utf-8");
	            out.println("Server: SERVER");
	            // Este linea en blanco marca el final de los headers de la respuesta
	            out.println("");
	            // Envia el HTML
	            out.println("<H1>Bienvenido al Server</H1>");
	            out.println("<H2>Request Method->" + request_method + "</H2>");
	            out.println("<H2>Post->" + postData + "</H2>");
	            out.println("<form name=\"input\" action=\"form_submited\" method=\"post\">");
	            out.println("Usuario: <input type=\"text\" name=\"user\"><input type=\"submit\"></form>");
	            out.close();
	            insocket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}  