package dk.tec.jaj.client;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.*;


public class ChatClient extends JFrame  
	{
	   JLabel lblName, lblSend, lblReceived;
	   JTextField txtSend; 
	   JTextField txtName;
	   JTextArea txtReceived;
	   JButton btnConnect, btnSend, btnExit;
		
	   Socket sock;
	   BufferedReader in;
	   PrintWriter out;
	   
	   boolean connected = false;
		
	   public static void main(String[] args) 
	   {
	      ChatClient client = new ChatClient();
	      client.setTitle("Chat client");
	      client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      client.setBounds(200, 200, 550, 450);
	      client.setVisible(true);
	   }
		
	   public ChatClient() 
	   {
	      lblSend = new JLabel("Send");
	      lblReceived = new JLabel("Received");
	      txtSend = new JTextField(20);
	      txtSend.setMaximumSize(txtSend.getPreferredSize());//At den ikke fylder hele højden
	      txtReceived = new JTextArea(20, 20);
	      txtReceived.setLineWrap(true);
	      
	      lblName = new JLabel("Connect Name");
	      txtName = new JTextField(10);
	      btnConnect = new JButton("Connect");
	      
	      btnSend = new JButton("Send");
	      btnExit = new JButton("Exit");
	      btnExit.setPreferredSize(btnSend.getPreferredSize());
	      
	      JScrollPane sp = new JScrollPane(txtReceived); 
	      sp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
	      //------------------------------
	      JPanel pnl0 = new JPanel();
	      pnl0.add(lblName);
	      pnl0.add(txtName);
	      pnl0.add(btnConnect);
	      
	      //------------------------------	      
	      
	      JPanel pnl1 = new JPanel();
	      pnl1.setLayout(new GridBagLayout());
	      GridBagConstraints gs = new GridBagConstraints();
	      gs.insets = new Insets(10, 10, 10, 10); 
	      // Indsætter margin omkring cellen, hvilket påvirker hele rækken og kolonnen. 
	      // Når den bruges på alle bliver det pænt.
	      
	      gs.gridx = 1;
	      gs.gridy = 1;
	      pnl1.add(lblName, gs);
	      gs.gridx = 1;
	      gs.gridy = 2;
	      pnl1.add(txtName, gs);
	      gs.gridx = 1;
	      gs.gridy = 3;
	      pnl1.add(btnConnect, gs);
	      //-------------------------
	      gs.gridx = 1;
	      gs.gridy = 4;
	      pnl1.add(lblSend, gs);
	      gs.gridx = 1;
	      gs.gridy = 5;
	      gs.gridwidth = 3;
	      pnl1.add(txtSend,gs);
	      gs.gridwidth = 1;
	      gs.gridx = 1;
	      gs.gridy = 6;
	      pnl1.add(btnSend, gs);
	      gs.gridx = 1;
	      gs.gridy = 7;     
	      pnl1.add(btnExit, gs);
	      
	      JPanel pnl2 = new JPanel();
	      
	      pnl2.setLayout(new GridBagLayout());  
	      gs.gridx = 1;
	      gs.gridy = 1;
	//Skal rykkes lidt mere til højre fordi teksten er længere end i labelen Send
	      gs.insets = new Insets(10, 30, 10, 10);      pnl2.add(lblReceived, gs);
	      gs.gridx = 1;
	      gs.gridy = 2;
	      gs.gridwidth = 2;
	      gs.gridheight = 15;
	      gs.insets = new Insets(10, 10, 10, 10);
	      pnl2.add(sp, gs);
			
	      getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.X_AXIS));
	      //add(pnl0); //
	      add(pnl1);
	      add(pnl2);
	      
	  
	      
	      btnConnect.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
			    try {
					sock = new Socket("localhost", 2000);
					in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
					out = new PrintWriter(sock.getOutputStream(),true);
					
				} catch (IOException e1) {	
					e1.printStackTrace();
				}
				
			    out.println(txtName.getText());
			    
			}
		});
	      
	      btnSend.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				out.println(txtSend.getText()); // + "\n"); // med den kommer der en ekstra tom linie		
			}
		});
	      
	      Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() 
			{
				String message;
				
				while(true)
				{
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(connected)
					{
						try {
							message = in.readLine();
							txtReceived.setText(txtReceived.getText() + message  + "\n" );
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}	
			}
		  });
	      t.start();
	 }   
}
