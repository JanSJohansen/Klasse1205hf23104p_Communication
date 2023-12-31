package dk.tec.jaj.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class SendAll implements Runnable {

	private ArrayList<Socket> sockets;
	private ClientMessage dataMessage;

	public SendAll(ArrayList<Socket> sockets, ClientMessage dataMessage) 
	{
		this.sockets = sockets;
		this.dataMessage = dataMessage;
	}

	@Override
	public void run() 
	{
		PrintWriter out = null;
		
		while(true)
		{	
			
			// DataMessage skal være trådsikker, ellers fejler det her.
			// Det går for stærkt!
			// (Et lille delay fik det også til at virke)

			if(dataMessage.isNewMessage())
			{		
				String mess = dataMessage.getMessage();		
				
				for(Socket s : sockets)
				{
					try {
						out = new PrintWriter(s.getOutputStream(), true);
						out.println(mess);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				dataMessage.setNewMessage(false);
			}
		}
	}
}
