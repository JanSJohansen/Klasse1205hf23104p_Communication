package dk.tec.jaj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class MyGraphics extends JFrame implements MouseListener, MouseMotionListener
{
	int xPos = 100, yPos = 100;
	int xPrev, yPrev;
	
	int size = 50;
	boolean dragging = false;
	
	@Override
	public void paint(Graphics g) 
	{		
		super.paint(g);
		
		g.setColor(Color.BLUE);
		g.fillRect(xPos, yPos, size, size);	
		
		
		g.setColor(Color.YELLOW);
		g.fillRect(xPos+ 100, yPos + 200, size, size);	
		
		
	}

	public MyGraphics()
	{
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int newX = e.getX();
		int newY = e.getY();
		if(newX > xPos && newX < xPos + size
				&& newY > yPos && newY < yPos + size)
		{
			dragging = true;
			xPrev = newX;
			yPrev = newY;
		}	
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		int newX = e.getX();
		int newY = e.getY();
		if(dragging)
		{
			xPos += newX - xPrev;
			yPos += newY - yPrev;
			
			xPrev = newX;
			yPrev = newY;
			
			repaint();
		}	
	}

	
	@Override
	public void mouseReleased(MouseEvent e) 
	{
		dragging = false;	
	}

	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
