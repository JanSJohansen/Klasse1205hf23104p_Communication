package dk.tec.jaj.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer 
{
	public static ArrayList<Socket> sockets = new ArrayList<Socket>();
	public static ClientMessage dataMessage = new ClientMessage();

	public static void main(String[] args) 
	{
		System.out.println("Server Started");
		
		Thread t1 = new Thread(new SendAll(sockets, dataMessage));
		t1.start();
		
		try(ServerSocket serverSocket = new ServerSocket(2000))
		{
			ClientWorker cw;
			Socket s;
			while(true)
			{
				s = serverSocket.accept();
				sockets.add(s);
				cw = new ClientWorker(s, dataMessage);
				Thread t2 = new Thread(cw);
				t2.start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
}
