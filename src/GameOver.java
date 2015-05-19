import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import MouseBounds.mouseBounds;


public class GameOver extends JPanel implements MouseListener, MouseMotionListener
{
	
	//the menu button's variables
	int int_menux = 425;
	int int_menuy = 300;
	int int_menuWidth = 115;
	int int_menuHeight = 50;
	
	//the shell image
	Image img_menu = Main.tool.getImage("Images/pong_menu.png");
	Image img_menu2 = Main.tool.getImage("Images/pong_menu2.png");
	Image img_menu_shell = img_menu;
	
	public GameOver()
	{
		setVisible(true);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void mouseEntered( MouseEvent e ) //mouse entered
	{
	}
	public void mouseExited( MouseEvent e ) //mouse excited
	{
	}
	public void mouseClicked( MouseEvent e ) //mouse clicked - most action will happen here
	{
		if(mouseBounds.mouseCollide(e.getX(),e.getY(),int_menux,int_menuy,int_menuWidth,int_menuHeight) == true)
		{
			CardLayout cl = (CardLayout)(Main.screens.getLayout()); //declare the cardlayout 
			cl.show(Main.screens, "titlescreen"); //tell the cardlayout to shuffle to the next card
		}
	}
	public void mousePressed( MouseEvent e ) //mouse pressed
	{
	}
	public void mouseReleased( MouseEvent e ) //mouse released - accompanies mouse clicked
	{  
	}
	public void mouseDragged(MouseEvent e) //if the mouse is dragged
	{
	}
	public void mouseMoved(MouseEvent e) //if the mouse is moved
	{
		//if the mouse has been moved on the start button
		if(mouseBounds.mouseCollide(e.getX(),e.getY(),int_menux,int_menuy,int_menuWidth,int_menuHeight) == true)
		{
			img_menu_shell = img_menu2; //make the button red
			repaint(); //repaint the screen
		}
		else
		{
			img_menu_shell = img_menu;; //make the font white
			repaint();//repaint the screen
		}
	}
	
	public void paintComponent(Graphics g)
	{
		g.setColor(Color.black);
		g.fillRect(0,0,1000,500);
		g.drawImage(Main.img_gameOver, 350,150,this);
		g.drawImage(img_menu_shell,int_menux,int_menuy,this);
	}
}
