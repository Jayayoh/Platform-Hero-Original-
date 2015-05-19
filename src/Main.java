
/***************************************
*   John Omeljaniuk & Phil Bystrican   *
*      Computer Tech Culminating       *
*          November 21 2011            *
***************************************/

/*
 * This project is for a grade 10 computer Technology coarse. It is a modern platform
 * runner game, which features a diceman, who travels across the platforms, defeating enemies,
 * and progressing to the next consecutive level. The Physics engine, and levels were
 * created by Both Phil and John. While Phil is not fluent with Java, he acted as a logic support role,
 * as well as introducing many of the animations, textures and ideas. John Primarily coded the project.
 * A new version of this project was also being coded by Phil in Objective C, in which case, the positions
 * are reversed.
 */

import javax.swing.*; //import the swing API
import java.awt.*; //import the AWT API
import java.awt.event.*; //import the AWT API's event API for event handling

public class Main  extends JFrame implements KeyListener //the main class
{
	static Toolkit tool = Toolkit.getDefaultToolkit(); //this toolkit will allow us to import images
	static JPanel screens = new JPanel(new CardLayout()); //this JPanel will act as an organizer to shuffle through other JPanels
	static TitleScreen menuScreen = new TitleScreen();
	static gameSurface playSurface = new gameSurface();
	static GameOver gameOver = new GameOver();
	//the images are here for loading
	//image for the enemies (animation) right
	static Image img_sprite_right[] = {
			tool.getImage("Images/diceStandRight.png"),
			tool.getImage("Images/diceWalkRight1.png"),
			tool.getImage("Images/diceWalkRight2.png"),
			tool.getImage("Images/diceWalkRight3.png"),
			tool.getImage("Images/diceWalkRight4.png"),
			tool.getImage("Images/diceWalkRight5.png")
	};
	//images for the enemies (animation) left
	static Image img_sprite_left[] = {
			tool.getImage("Images/diceStandLeft.png"),
			tool.getImage("Images/diceWalkLeft1.png"),
			tool.getImage("Images/diceWalkLeft2.png"),
			tool.getImage("Images/diceWalkLeft3.png"),
			tool.getImage("Images/diceWalkLeft4.png"),
			tool.getImage("Images/diceWalkLeft5.png")
	};
	static Image img_background1 = tool.getImage("Images/background_1.png");
	static Image img_background2 = tool.getImage("Images/background_3.png");
	static Image img_background3 = tool.getImage("Images/background_4.png"); 
	static Image img_background4 = tool.getImage("Images/background_2.png");
	static Image img_block = tool.getImage("Images/GreenGrassDirtBlock.png"); //image of the platform
	static Image img_green_grass = tool.getImage("Images/GreenGrassTop.png"); //get the grass
	static Image img_yellow_grass = tool.getImage("Images/YellowGrassTop.png"); // the yellow grass
	static Image img_black_grass = tool.getImage("Images/BlackGrassTop.png"); //the black grass
	static Image img_steel = tool.getImage("Images/block.gif"); //the steel platform image
	static Image img_sprite_falling_right = tool.getImage("Images/diceFallRight.png");
	static Image img_sprite_falling_left = tool.getImage("Images/diceFallLeft.png");
	static Image img_coin = tool.getImage("Images/COIN.gif");
	static Image img_life = tool.getImage("Images/heart.gif");
	static Image img_portal = tool.getImage("Images/portal.gif");
	static Image img_loading  = tool.getImage("Images/Loading.png");
	static Image img_gameOver = tool.getImage("Images/gameOver.png");
	static Image img_menu = tool.getImage("Images/pong_menu.png");
	static Image img_menu2 = tool.getImage("Images/pong_menu2.png");
	//end of images
	
	//the media tracker to load the images
	static MediaTracker mt = new MediaTracker(menuScreen);
	
	//the loading boolean
	static boolean bool_loading = false;
	
	//the load animator shell
	static Image img_loadShell = img_sprite_right[0];
	//the load animator counter
	static int int_load_counter = 0;
	
	//the THread which animates diceman while loading
	static Thread loadAnimator = new Thread(new Runnable(){
		public void run()
		{
			int_load_counter += 1;
			
			switch(int_load_counter)
			{
				case 0: img_loadShell = img_sprite_right[0]; break;
				case 1: img_loadShell = img_sprite_right[1]; break;
				case 2: img_loadShell = img_sprite_right[2]; break;
				case 3: img_loadShell = img_sprite_right[3]; break;
				case 4: img_loadShell = img_sprite_right[4]; break;
				case 5: img_loadShell = img_sprite_right[5]; break;
			}
			
			menuScreen.repaint();
			
			try
			{
				Thread.sleep(100);
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			
			if(int_load_counter >= 5)
			{
				int_load_counter = 0;
			}
			
			run();
		}
	});
	
//Main Method
	public static void main(String[] args) 
	{
		//create the game window
		Main window = new Main(1000,500);
		
		mt.addImage(img_background1, 0);
		mt.addImage(img_background2, 1);
		mt.addImage(img_background3, 2);
		mt.addImage(img_background4, 3);
		mt.addImage(img_block, 4);
		mt.addImage(img_green_grass, 5);
		mt.addImage(img_yellow_grass, 6);
		mt.addImage(img_black_grass, 7);
		mt.addImage(img_steel, 8);
		mt.addImage(img_sprite_falling_right, 9);
		mt.addImage(img_sprite_falling_left, 10);
		mt.addImage(img_coin, 11);
		mt.addImage(img_life, 12);
		mt.addImage(img_portal, 13);
		mt.addImage(img_gameOver, 14);
		mt.addImage(img_menu, 15);
		
		loadAnimator.start();
		bool_loading = true;
		menuScreen.repaint();
		
		try
		{
			mt.waitForAll();
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		bool_loading = false;
		menuScreen.repaint();
		loadAnimator.stop();
	}
	
//Constructor which takes the arguments width and height to create the window
	public Main(int width, int height)
	{
		super("Platformer"); //give it a name
		setVisible(true); //make it visible
		setSize(width,height); //give it a 2D size
		setDefaultCloseOperation(EXIT_ON_CLOSE); //allow it to exit when the X button is clicked
		setResizable(false); //prevent the user from resizing the screen
		add(screens); //add the organizer
		screens.add(menuScreen, "titlescreen"); //add the titlescreen to the organizer
		screens.add(playSurface, "gameSurface"); //add the playing surface
		screens.add(gameOver, "GameOver");
		addKeyListener(this); //allow key input to be used
	}
	
	//if a key was pressed then
	public void keyPressed(KeyEvent e)
	{
		//check which key was pressed
		if(playSurface.Enabled == true)
		{
			//if it was the right arrow
			if(e.getKeyCode() == 68)
			{
				playSurface.hero.bool_right = true;//tell the player to go right
			}
			//if it was the left arrow
			else if(e.getKeyCode() == 65)
			{
				playSurface.hero.bool_left = true; //tell the player to go left
			}
			
			//if it was the up arrow
			if(e.getKeyCode() == 87)
			{
				//if the hero can jump
				if(playSurface.hero.bool_jumpable == true)
				{
					//make the player jump
					playSurface.hero.bool_jumping = true;
					//reset the jumping counter
					playSurface.hero.int_jump_counter = 0;
				}
			}
		}
	}
	//if a key was typed
	public void keyTyped(KeyEvent e)
	{
		//do nothing
	}
	//if a key was released
	public void keyReleased(KeyEvent e)
	{
		//check which key was released
		if(playSurface.Enabled == true)
		{
			//if it was the right key
			if(e.getKeyCode() == 68)
			{
				playSurface.hero.bool_right = false; //tell the player to stop moving right
			}
			else if(e.getKeyCode() == 65) //if it was the left key
			{
				playSurface.hero.bool_left = false; //tell the player to stop moving left
			}
		}
	}

}