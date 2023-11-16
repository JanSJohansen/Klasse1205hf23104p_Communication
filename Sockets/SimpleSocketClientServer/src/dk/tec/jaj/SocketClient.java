package dk.tec.jaj;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	public static void main(String[] args) 
	{
		try
		(
				Socket ioSocket = new Socket("localhost", 2000)
		)
		{
			InputStream in = ioSocket.getInputStream();
			OutputStream out = ioSocket.getOutputStream();
			
			String str = "Hej fra Client\n";
			byte[] bytes = str.getBytes();
			out.write(bytes);
			
			System.out.println("\nClint send and get from Server:");
			int ch;
			while((ch = in.read()) != -1 )
			{
				System.out.print((char)ch);
			}		
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {		
			e.printStackTrace();
		}
			System.out.println("Client Ended\n");
	}

}
