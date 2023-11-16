package dk.tec.jaj.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SocketClient extends JFrame  
{
	   JLabel lblSend, lblReceived;
	   JTextField txtSend, txtReceived;
	   JButton btnSend, btnExit;
		
	   Socket sock;
	   BufferedReader in;
	   PrintWriter out;
		
	   public static void main(String[] args) 
	   {
	      SocketClient client = new SocketClient();
	      client.setBounds(200, 200, 400, 300);
	      client.setDefaultCloseOperation(EXIT_ON_CLOSE);
	      client.setVisible(true);
	   }
		
	   public SocketClient()
	   {
	      lblSend = new JLabel("Send");
	      lblReceived = new JLabel("Received");
	      txtSend = new JTextField(20);
	      txtReceived = new JTextField(20);
	      btnSend = new JButton("Send");
	      btnExit = new JButton("Exit");
			
	      JPanel pnl1 = new JPanel();
	      JPanel pnl2 = new JPanel();
	      JPanel pnl3 = new JPanel();
			
	      pnl1.add(lblSend);
	      pnl1.add(txtSend);
	      pnl2.add(lblReceived);
	      pnl2.add(txtReceived);
	      pnl3.add(btnSend);
	      pnl3.add(btnExit);
			
	      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
	      add(pnl1);
	      add(pnl2);
	      add(pnl3);
	      
	      try {
	    	  sock = new Socket("localhost", 2000);
	    	  in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	    	  out = new PrintWriter(sock.getOutputStream(),true);
	      }
	      catch (Exception e) {
			e.printStackTrace();
		  }
	      
	      btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				try {
					out.println(txtSend.getText());
					txtReceived.setText(in.readLine());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});	      
	}
}






