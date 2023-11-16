package dk.tec.jaj.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientWorker implements Runnable 
{
	private Socket socket;
	BufferedReader in;
	PrintWriter out;

	public ClientWorker(Socket socket) 
	{
		this.socket = socket;
	}

	@Override
	public void run() 
	{		
		String line;	
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(socket.getOutputStream(), true);
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		while(true)
		{
			try {
				line = in.readLine();
				out.println("Client Wrote: " + line);
			} catch (IOException e) {
				
				e.printStackTrace();
			}	
		}	
	}
}
