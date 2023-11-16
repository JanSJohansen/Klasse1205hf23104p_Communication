package dk.tec.jaj.server;

public class ClientMessage 
{
	String message;
	
	boolean newMessage = false;
	
	public synchronized String getMessage() {
		return message;
	}
	public synchronized void setMessage(String message) {
		this.message = message;
	}
	public synchronized boolean isNewMessage() {
		return newMessage;
	}
	public synchronized void setNewMessage(boolean newMessage) {
		this.newMessage = newMessage;
	}
	
	

}
