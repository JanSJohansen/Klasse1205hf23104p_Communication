package dk.tec.jaj;

import javax.swing.JFrame;

public class GraphicsMain {

	public static void main(String[] args) 
	{
		MyGraphics mg = new MyGraphics();
		mg.setBounds(200, 200, 400, 400);
		mg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mg.setTitle("My Graphics");
		mg.setVisible(true);
	}
}
