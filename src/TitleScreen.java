import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import MouseBounds.mouseBounds;

//the JPanel for the first level
class TitleScreen extends JPanel implements MouseListener, MouseMotionListener
{
	//VARIABLES
	
	//START BUTTON VARIABLES
	Image img_Start = Main.tool.getImage("Images/pong_start.png"); //**the start button
	Image img_start2 = Main.tool.getImage("Images/pong_start2.png"); //the second start button
	Image img_title = Main.tool.getImage("Images/TitleImageComputer.png"); //the title image
	Image img_start_shell;
	int int_startx = 425; //x
	int int_starty = 300; //y
	int int_startwidth = 150; //width
	int int_startheight = 50; //height
	
	//constructor which takes no arguments
	public TitleScreen() 
	{
		setVisible(true); //make the JPanel visible
		addMouseListener(this); //make the mouse methods do something
		addMouseMotionListener(this); //check if the mouse has moved
	}
	
	//MOUSE LISTENER
	public void mouseEntered( MouseEvent e ) //mouse entered
	{
	}
	public void mouseExited( MouseEvent e ) //mouse excited
	{
	}
	public void mouseClicked( MouseEvent e ) //mouse clicked - most action will happen here
	{
		if(mouseBounds.mouseCollide(e.getX(),e.getY(),int_startx,int_starty,int_startwidth,int_startheight) == true)
		{
			CardLayout cl = (CardLayout)(Main.screens.getLayout()); //declare the cardlayout 
			cl.show(Main.screens, "gameSurface"); //tell the cardlayout to shuffle to the next card
			lvl_tutorial.init();
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
		if(mouseBounds.mouseCollide(e.getX(),e.getY(),int_startx,int_starty,int_startwidth,int_startheight) == true)
		{
			img_start_shell = img_start2; //make the button red
			repaint(); //repaint the screen
		}
		else
		{
			img_start_shell = img_Start; //make the font white
			repaint();//repaint the screen
		}
	}
	//paint method - all the graphic rendering happens here
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g); //do something important
		if(Main.bool_loading == true)
		{
			g.setColor(Color.black);
			g.fillRect(0,0,1000,500);
			g.drawImage(Main.img_loading, 250,300,this);
			g.drawImage(Main.img_loadShell, 550,300, this);
		} else {
			g.setColor(Color.black); //**set the color to black
			g.fillRect(0, 0 , 1000, 500); //**draw the background
			g.drawImage(img_title, 250,10, this); //**draw the temporary text
			g.drawImage(img_start_shell, int_startx, int_starty, this); //draw the start button
		}
	}
}

