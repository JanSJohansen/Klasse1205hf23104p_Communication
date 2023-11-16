package dk.tec.jaj.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

	public static void main(String[] args) 
	{
		System.out.println("Server Started");
		
		try(ServerSocket serverSocket = new ServerSocket(2000))
		{
			while(true)
			{
				Socket socket = serverSocket.accept();
				ClientWorker c = new ClientWorker(socket);
				Thread t = new Thread(c);
				t.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
