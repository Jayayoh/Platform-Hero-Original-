//import all of the necessary libraries and codes
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

class lvl_tutorial
{
	// platform x coordinates -- every 30 platforms is a new line
		static final int int_platformsX[] = {2,2,2,2,2,2,2,3,4,5,6,7,8,9,10,11,12,13,14,15,15,15,15,15,15,15,25,26,30,31,
			32,32,32,32,32,32,33,34,35,35,35,35,35,35,36,37,38,39,40,41,42,42,43,44,45,46,47,48,49,50}; 
		//platform y coordinates -- every 30 platforms is a new line
		static final int int_platformsY[] = {2,3,4,5,6,7,8,8,8,8,8,8,8,8,8,8,8,8,8,8,7,6,5,4,3,2,2,2,2,2,
			2,1,0,-1,-2,-3,2,2,2,1,0,-1,-2,-3,2,2,2,2,2,2,2,1,2,2,2,2,2,2,2,2};
		//the horizontal speed of the platforms
		static final int int_platformsDX[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		//the vertical speed of the platforms
		static final int int_platformsDY[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		//the enemies x coordinates
		static final int int_enemyX[] = {35};
		//the enemy y coordinate
		static final int int_enemyY[] = {1};
		//the enemy speed
		static final int int_enemyDX[] = {1};
		//the x coordinates for the money
		static final int int_moneyX[] = {8,9,10,25,26,27,33,33,34,34};
		//the Y coordinates for the coins
		static final int int_moneyY[] = {5,5,5,-1,-1,-1,-2,-1,-2,-1};
		//the integer which tells which event has happened
		static int int_event_order = 0;
		//the array of platform objects
		static Platform block[] = new Platform[int_platformsX.length]; 
		//the player object
		static player hero = new player(350,100);
		//get the background image
		static Image img_background = Main.img_background4; //get the background image
		//the message at the bottom of the screen
		static String String_message = "Welcome to Platform Hero!";
		//the event queue
		static int int_event = 0;
		//the target X for the event
		static int int_event4_targetX[] = {1,2,3,4,5,6};
		
		//constructor which takes no arguments
		public lvl_tutorial()
		{
		}
		
		public static void init()
		{
			
			//set the platforms array length
			Main.playSurface.block = new Platform[int_platformsX.length];
			
			//set the enemy array length
			Main.playSurface.diceMan = new enemy_dice[int_enemyX.length];
			
			//set the coins array
			Main.playSurface.money = new coin[int_moneyX.length];
			
			//the level changer - one per level
			Main.playSurface.portal = new lvl_changer(49,0,1);
			
			//give the gameSurface the information for lvl 1's platforms
			Main.playSurface.int_platformsDX = int_platformsDX;
			Main.playSurface.int_platformsDY = int_platformsDY;
			Main.playSurface.int_platformsX = int_platformsX;
			Main.playSurface.int_platformsY = int_platformsY;
			
			//give the surface the information for the enemies
			Main.playSurface.int_enemyX = int_enemyX;
			Main.playSurface.int_enemyY = int_enemyY;
			Main.playSurface.int_enemyDX = int_enemyDX;
			
			//give the surface the information regarding the coins
			Main.playSurface.int_moneyX = int_moneyX;
			Main.playSurface.int_moneyY = int_moneyY;
			
			//create a player
			Main.playSurface.hero = new player(350,250);
			
			//give the background an image
			Main.playSurface.img_background = img_background;
			
			//tell the gameSurface its running
			Main.playSurface.Enabled = true;
			
			//tell the gameSurface what type of block to draw
			Main.playSurface.grass = false;
			//what color?
			Main.playSurface.String_grass_color = "none";
			Main.playSurface.dirt = false;
			Main.playSurface.steel = true;
			
			//tell the game surface to re-initialise
			Main.playSurface.init();
			
			//start the timer
			Main.playSurface.bool_timer = true;
			
			//set the timer
			Main.playSurface.int_minutes = 3;
			Main.playSurface.int_seconds = 0;
			
			//set the event to 0
			int_event = 0;
			
			//tell which level is active
			Main.playSurface.int_lvl = 0;
		}
		
		//make the blocks move in event four
		public static void event4()
		{	
			//the integer which checks event 4's progress
			int int_checker = 0;

			//iterate through the platforms affected by event 4
			for(int i  = 0; i < int_event4_targetX.length; i++)
			{
				//if they are not at their target, move them
				if(Main.playSurface.block[20 + i].int_x < Main.playSurface.block[19].int_x + (int_event4_targetX[i] * 50))
				{
					Main.playSurface.block[20 + i].int_x += 1;
				}
				else
				{
					int_checker += 1;
				}
			}
			
			//if they are at their target, stop them and change event
			if(int_checker >= int_event4_targetX.length)
			{
				int_event = 5;
			}
		}
		
		//game loop. events are coded here
		public static void gameloop()
		{
			//the events is block. this is a massive else-if that determines the order of events
			if(int_event == 0)
			{
				//reset the message
				String_message = "Welcome to Platform Hero!";
				
				//start the timer
				Event1Timer.start();
			}
			//second event - instruct the player to go left
			else if(int_event == 1)
			{
				//stop the previous event's timer
				Event1Timer.stop();
				
				//reset the message
				String_message = "Start by pressing the 'd' key to walk right.";
				
				//check if the palyer has moved into position. if so, change events
				if(Main.playSurface.block[0].int_x < 0)
				{
					int_event = 2;
				}
			}
			//event three - instruct the palyer to move left
			else if(int_event == 2)
			{
				//reset the message
				String_message = "Good! Now Press the 'a' key to walk left.";
				
				//if the player has moved into position, change event
				if(Main.playSurface.block[0].int_x > 2)
				{
					int_event = 3;
				}
			}
			//event four - instruct the player to jump
			else if(int_event == 3)
			{
				//reset the message
				String_message = "Wonderful! Now Press the 'w' key to jump.";
				
				//if the player has moved into position, change event
				if(Main.playSurface.hero.int_y < 250)
				{
					int_event = 4;
				}
			}
			//event 5 - move the wall
			else if(int_event == 4)
			{
				//reset the message
				String_message = "Your now ready to face your first enemy. Climb the stairs to the next room.";
				
				//move the wall
				event4();
			}
			//event 6 - instruct the player about moving platforms
			else if(int_event == 5)
			{
				//reset the message
				String_message = "This is a moving platform. Jump on it!";
				
				//make the platforms move
				Main.playSurface.block[26].int_dx = -1;
				Main.playSurface.block[27].int_dx = 1;
				
				//change event
				int_event = 6;
			}
			//event seven - move the wall
			else if(int_event == 6)
			{
				//if the player has moved into position
				if(Main.playSurface.int_stand == 28 || Main.playSurface.int_stand == 29)
				{
					//reset the message
					String_message = "in the room after the next, there is an enemy.";
					//change event
					int_event = 7;
				}
			}
			//event 8 - continue event seven
			else if(int_event == 7)
			{
				//start event - 7 timer
				Event7Timer.start();
			}
			//event nine - move the wall out of the way
			else if(int_event == 8)
			{
				//stop the timer
				Event7Timer.stop();
				
				//move the block
				if(Main.playSurface.block[31].int_y > Main.playSurface.block[32].int_y)
				{
					Main.playSurface.block[31].int_y -= 1;
				}
				//if the blocks are done moving, change event
				else if(Main.playSurface.block[31].int_y == Main.playSurface.block[32].int_y)
				{
					int_event = 9;
				}
			}
			//event 10 - close the player in
			else if(int_event == 9)
			{
				//if the player has moved into position
				if(Main.playSurface.int_stand == 37)
				{
					//move the block
					if(Main.playSurface.block[31].int_y < Main.playSurface.block[32].int_y + Main.playSurface.block[32].int_width)
					{
						Main.playSurface.block[31].int_y += 1;
					}
					//when the block is finished moving
					else if(Main.playSurface.block[31].int_y == Main.playSurface.block[32].int_y + Main.playSurface.block[32].int_width)
					{
						//reset the string
						String_message = "To kill an enemy, jump on top of it.";
						//change event
						int_event = 10;
					}
				}
			}
			//event 10 - give the player access to the enemy
			else if(int_event == 10)
			{
				//if the block needs to move, move it
				if(Main.playSurface.block[39].int_y > Main.playSurface.block[40].int_y)
				{
					Main.playSurface.block[39].int_y -= 1;
				}
				//otherwise, change the event
				else if(Main.playSurface.block[39].int_y == Main.playSurface.block[40].int_y)
				{
					int_event = 11;
				}
			}
			//final event
			else
			{
				//if the player has moved into position
				if(Main.playSurface.int_stand == 45)
				{
					//reset the message
					String_message = "You are now ready! GO off into your own Adventure as a Platform Hero!";
				}
			}
		}
		
		//the level's paint method: this provides level specific paint functions
		public static void paint(Graphics g)
		{
			//set color to black
			g.setColor(Color.black);
			//draw the message box
			g.fillRect(10,435,975,25);
			//set color to white
			g.setColor(Color.white);
			//draw the message box outline
			g.drawRect(10, 435, 975, 25);
			//draw the message
			g.drawString(String_message,16,451);
		}
		
		//the first event timer
		static Timer Event1Timer = new Timer(3000,new ActionListener(){public void actionPerformed(ActionEvent e){
			//change events
			int_event = 1; 
		}});
		
		//the seventh event timer
		static Timer Event7Timer = new Timer(2000,new ActionListener(){public void actionPerformed(ActionEvent e){
			//reset the message
			String_message = "It will damage you if you touch it, not on the top. It will also try to shoot you. Avoid the bullets.";
			//change event
			int_event = 8;
		}});
}
