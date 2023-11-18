package dk.tec.jaj.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientWorker implements Runnable 
{

	private Socket socket;
	private ClientMessage dataMessage;

	public ClientWorker(Socket s, ClientMessage dataMessage) 
	{
		this.socket = s;
		this.dataMessage = dataMessage;
	}

	@Override
	public void run() 
	{
		BufferedReader in = null;
		
		try {
			in = 	new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
		} catch (IOException e) {
		
			e.printStackTrace();
		}
		
		String message;
		String name = "N/A";
		
		try {
			name = in.readLine();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println(name);
		
		while(true)
		{
			try {
				message = in.readLine();
				
				System.out.println(message);
				
				dataMessage.setMessage(name + ": " + message);
				dataMessage.setNewMessage(true);
				
			} catch (IOException e) {
				e.printStackTrace();
			}		
		}
	}
}





