package dk.tec.jaj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) 
	{
		System.out.println("\nServer Started\n");
		try
		(
			ServerSocket serverSocket = new ServerSocket(2000);
			Socket ioSocket = serverSocket.accept();
		)
		{
			BufferedReader in =
					new BufferedReader(
							new InputStreamReader(
									ioSocket.getInputStream()));
			
			PrintWriter out = new PrintWriter(ioSocket.getOutputStream(),true);
			
			String inStr = in.readLine();
			out.println("Client you wrote: " + inStr);
			
			System.out.println("From Client: " + inStr);
					
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nServer Ended\n");
	}
}
