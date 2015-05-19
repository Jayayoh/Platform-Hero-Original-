//import all of the necessary libraries and codes
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import Collision.collision;

class gameSurface extends JPanel implements MouseListener, MouseMotionListener 
{
		
		// platform x coordinates -- every 30 platforms is a new line
		int int_platformsX[] = {}; 
		//platform y coordinates -- every 30 platforms is a new line
		int int_platformsY[] = {};
		//the horizontal speed of the platforms
		int int_platformsDX[] = {};
		//the vertical speed of the platforms
		int int_platformsDY[] = {};
		
		//the reference for the platform array
		int int_platformsX_ref[];
		int int_platformsY_ref[];
		int int_platformsDX_ref[];
		int int_platformsDY_ref[];
		//end reference
		
		//the enemy x coordinate
		int int_enemyX[] = {};
		//the enemy y coordinate
		int int_enemyY[] = {};
		//the enemy directional speed
		int int_enemyDX[] = {};
		//the seconds counter
		int int_seconds = 0;
		//the minutes counter
		int int_minutes = 10;
		//how much score do you have?
		int int_score = 0;
		//to tell what level is active
		int int_lvl = 0;
		//boolean for enabled
		boolean Enabled = false; //is the JPanel enabled?
		//the array of platform objects
		Platform block[]; 
		//the array of enemy objects
		enemy_dice diceMan[];
		//the player object
		player hero; 
		//the portal object
		lvl_changer portal;
		//get the background image
		Image img_background; 
		//if this is true, draw the grass
		boolean grass;
		//this tells what color the grass will be
		String String_grass_color;
		//if this is true, draw the blocks as dirt
		boolean dirt;
		//if this is true, draw the blocks as steel
		boolean steel;
		//check to see if the game timer is running
		boolean bool_timer = false;
		//the colour for the timer
		Color color_timer = Color.yellow;
		//the integer that tells what platform the player is standing on
		int int_stand;
		//the array of coins
		coin[] money;
		//the x of the coins
		int int_moneyX[];
		//the y of the coins
		int int_moneyY[];
		//the number of lives left
		int int_livesX[] = {35,70,105};
		//the number of lives Y
		int int_livesY[] = {50,50,50};
		//the lives
		lives heart[] = new lives[3];
		//the number of lives the player has
		int int_lives = 2;
		//the sounds
		File[] soundFile = {
				new File("sounds/Techno_Track.wav"),
				new File("sounds/Creepy_Track.wav"),
				new File("sounds/Egyptian_Track.wav")
		};
		//the clip object for the sound file
		Clip[] music = new Clip[soundFile.length];
		//the input stream for the sound file
		AudioInputStream[] audioin = new AudioInputStream[soundFile.length];
		
		//the seperate threades to move the different
		Thread Thread_enemyMove = new Thread(new Runnable(){
			public void run()
			{
				//if diceman exists, move him
				if(int_enemyX.length > 0)
				{
					for(int i = 0; i < diceMan.length; i++)
					{
						if(diceMan[i] != null)
						{
							diceMan[i].move();
						}
						
					}
				}
			}
		});
		
		Thread Thread_playerMove = new Thread(new Runnable(){
			public void run()
			{
				hero.move();
			}
		});
		
		//constructor which takes no arguments
		public gameSurface()
		{
			setVisible(true); //make the JPanel visible
			update.start(); //start the game timer
			gameTimer.start(); //start he game timer
			addMouseListener(this); //make the surface detect mouse clicks
			addMouseMotionListener(this); //make the surface detect mouse movements
			setDoubleBuffered(true); //allow buffering to happen
			
			//set up the audio
			try
			{
				for(int i = 0; i < soundFile.length; i++)
				{
					//set up the audio clips
					audioin[i] = AudioSystem.getAudioInputStream(soundFile[i]);
					music[i] = AudioSystem.getClip();
					music[i].open(audioin[i]);
					
					//increase music volume
					FloatControl cool = (FloatControl)music[i].getControl(FloatControl.Type.MASTER_GAIN);
					cool.setValue(+5.0f);
				}
			}
			catch(Exception e) //catch and print any errors
			{
				System.out.println(e);
			}	
		}
		
		//Initialise variables
		public void init()
		{	
			//set the reference so that you always know the original position of the platforms
			int_platformsX_ref = int_platformsX;
			int_platformsY_ref = int_platformsY;
			int_platformsDX_ref = int_platformsDX;
			int_platformsDY_ref = int_platformsDY;
			
			//set up the lives
			for(int i = 0; i < int_lives + 1; i++)
			{
				//create the live objects
				heart[i] = new lives(int_livesX[i],int_livesY[i]);
			}
			
			//if the first level is active
			if(int_lvl == 1)
			{
				//stop any running music
				if(music[0].isRunning())
				{
					music[0].stop();
					music[0].setFramePosition(0);
				}
				//start the first clip
				music[0].start();
			}
			//if the second level is running
			else if(int_lvl == 2)
			{
				//stop any running music
				if(music[1].isRunning())
				{
					music[1].stop();
					music[1].setFramePosition(0);
				}
				//start the second clip
				music[0].stop();
				music[1].start();
			}
			//if the thrid level is active
			else if(int_lvl == 3)
			{
				//stop any running music
				if(music[2].isRunning())
				{
					music[2].stop();
					music[2].setFramePosition(0);
				}
				//start the thrid clip
				music[1].stop();
				music[2].start();
			}
			
			//go through the coordinates (int_platform) and make a platform object for each one
			for(int i = 0; i < int_platformsX.length; i++)
			{
				block[i] = new Platform(int_platformsX[i],int_platformsY[i],int_platformsDX[i],int_platformsDY[i]);
			}
			
			//create new enemies
			for(int i = 0; i < int_enemyX.length; i++)
			{
				diceMan[i] = new enemy_dice(int_enemyX[i],int_enemyY[i],int_enemyDX[i]);
			}
			
			//create new coins
			for(int i = 0; i < int_moneyX.length; i++)
			{
				money[i] = new coin(int_moneyX[i],int_moneyY[i]);
			}
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
			}
			public void mousePressed( MouseEvent e ) //mouse pressed
			{
			}
			public void mouseReleased( MouseEvent e ) //mouse released - accompanies mouse clicked
			{  
			}
			public void mouseMoved(MouseEvent e) //if the mouse was moved
			{
			}
			public void mouseDragged(MouseEvent e) //if the mouse was clicked and moved (dragged)
			{
			}
		
		//main game code goes here
		public void gameloop()
		{
			
			Thread_enemyMove.run();
			Thread_playerMove.run();
			
			//if platforms have a speed, move them
			for(int i = 0; i < int_platformsX.length; i++)
			{
				if(block[i].int_dx != 0 || block[i].int_dy != 0)
				{
					block[i].moveAI();
				}
			}
			
			//make the player move
			//hero.move();
			
			/*
			//if diceman exists, move him
			if(int_enemyX.length > 0)
			{
				for(int i = 0; i < diceMan.length; i++)
				{
					if(diceMan[i] != null)
					{
						diceMan[i].move();
					}
					
				}
			}*/
			
			//check if any bullets exist
			for(int i  = 0; i < diceMan.length; i ++)
			{
				//if the enemy exists
				if(diceMan[i] != null)
				{
					//loop through the enemy's bullets
					for(int j = 0; j < diceMan[i].Bullet.length; j++)
					{
						//if they do exist
						if(diceMan[i].Bullet[j] != null)
						{
							//move the bullets
							diceMan[i].Bullet[j].move();
							
							//if they go off screen
							if(diceMan[i].Bullet[j].int_x < 0 || diceMan[i].Bullet[j].int_x + diceMan[i].Bullet[j].int_width > 1000)
							{
								//set the bullet to null
								diceMan[i].Bullet[j] = null;
								break;
							}
							
							//if the bullet goes off screen
							else if(diceMan[i].Bullet[j].int_y < 0 || diceMan[i].Bullet[j].int_y + diceMan[i].Bullet[j].int_width > 500)
							{
								//set the bullet to null
								diceMan[i].Bullet[j] = null;
								break;
							}
							
							//check if they collide with the player
							//boundaries
							if(collision.checkCollide(diceMan[i].Bullet[j].int_x, diceMan[i].Bullet[j].int_y, diceMan[i].Bullet[j].int_width, diceMan[i].Bullet[j].int_height, hero.int_x, hero.int_y, hero.int_width, hero.int_height) == true)
							{
								//remove damage from the player's health bar
								hero.takeDamage(2);
								
								//make the bullet null
								diceMan[i].Bullet[j] = null;
								//get out of the loop
								break;
							}
							
							//loop through the blocks in the block array
							for(int k = 0; k < block.length; k++)
							{
								//if  the enemy exists
								if(diceMan[i] != null)
								{
									//check to see if there was a collision
									if(collision.checkCollide(diceMan[i].Bullet[j].int_x, diceMan[i].Bullet[j].int_y, diceMan[i].Bullet[j].int_width, diceMan[i].Bullet[j].int_height, block[k].int_x, block[k].int_y, block[k].int_width, block[k].int_height) == true)
									{
										//make the bullet null
										diceMan[i].Bullet[j] = null;
										//get out of the loop
										break;
									}
								}
							}
						}
					}
				}
			}

			//check collision with the player (enemy)
			for(int i = 0; i < diceMan.length; i++)
			{
				//if the enemy exists
				if(diceMan[i] != null)
				{
					//check for a collision with the player
					if(collision.checkCollide(diceMan[i].int_x, diceMan[i].int_y, diceMan[i].int_width, diceMan[i].int_height,hero.int_x, hero.int_y, hero.int_width, hero.int_height) == true)
					{
						//check if the player has killed the enemy
						if(collision.checkLocationBottom(diceMan[i].int_x, diceMan[i].int_y, diceMan[i].int_width, diceMan[i].int_height, hero.int_x, hero.int_y, hero.int_width, hero.int_height) == true)
						{
							//add 100 to the score
							int_score += 100;
							//stop the player from falling
							hero.bool_falling = false;
							//send the player bouncing up
							hero.bool_jumpable = true;
							//send the player bouncing up
							hero.bool_jumping = true;
							//reset the jump
							hero.int_jump_counter = 0;
							//reset gravity
							hero.int_gravity_counter = 0;
							//set the enemy instance to null
							diceMan[i] = null;
							//get out of the loop
							break;
						}
						//the ememy hit the player
						else
						{
							//cause damage
							hero.takeDamage(10);
							//say the player is immune
							hero.bool_damagable = false;
							//start the immunity timer
							hero.timer_damage.start();
						}
					}
				}
			}
			//see if the player has collided with the portal
			portal.bounds();
			
			//call the events in the tutorial level
			if(int_lvl == 0)
			{
				lvl_tutorial.gameloop();
			}
		}
		
		//the paint method - all graphic rendering happens here
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g); //do something important
			
			//draw the background
			g.drawImage(img_background,0,0,this);
			
			//draw the player
			g.drawImage(hero.img_sprite_shell,hero.int_x,hero.int_y,this);
			
			//set color to green
			g.setColor(hero.color_health);
			
			//draw the player's health
			g.fillRect(hero.int_x, hero.int_y - 15, hero.int_health / 2, 5);
			
			//set color to black
			g.setColor(Color.black);
			
			//draw the outline for the player's health
			g.drawRect(hero.int_x,hero.int_y - 15, 50, 5);
			
			//draw any bullets
			for(int i = 0; i < diceMan.length; i ++)
			{
				//if an enemy exists
				if(diceMan[i] != null)
				{
					//iterate through all the bullets
					for(int j = 0; j < diceMan[i].Bullet.length; j++)
					{
						//if a bullet exists
						if(diceMan[i].Bullet[j] != null)
						{
							//set color to green
							g.setColor(Color.green);
							//draw the bullet
							g.fillOval(diceMan[i].Bullet[j].int_x, diceMan[i].Bullet[j].int_y, diceMan[i].Bullet[j].int_width, diceMan[i].Bullet[j].int_height);
							//set color to blue
							g.setColor(Color.blue);
							//draw the outline of the bullet
							g.drawOval(diceMan[i].Bullet[j].int_x, diceMan[i].Bullet[j].int_y, diceMan[i].Bullet[j].int_width, diceMan[i].Bullet[j].int_height);
						}
					}
				}
			}
			
			//draw the grass on the platforms
			if(grass == true)
			{
				//if the game calls for green grass
				if(String_grass_color.equals("green"))
				{
					//go through all the platforms
					for(int i = 0; i < block.length; i++)
					{
						//if the block is on screen
						if(block[i].int_x < 1000 || (block[i].int_x + block[i].int_width) > 0)
						{
							//draw the platform 's grass
							g.drawImage(block[i].img_green_grass,block[i].int_x,block[i].int_y - 12, this);
						}
					}
				}
				//if the game calls for yellow grass
				else if(String_grass_color.equals("yellow"))
				{
					//check the length of the blocks
					for(int i = 0; i < block.length; i++)
					{
						//if the block is on screen
						if(block[i].int_x < 1000 || (block[i].int_x + block[i].int_width) > 0)
						{
							//draw the grass
							g.drawImage(block[i].img_yellow_grass,block[i].int_x,block[i].int_y - 12, this);
						}
					}
				}
				//otherwise, draw black grass
				else
				{
					//iterate through the platforms
					for(int i = 0; i < block.length; i++)
					{
						//if a platform is on screen
						if(block[i].int_x < 1000 || (block[i].int_x + block[i].int_width) > 0)
						{
							//draw the platform
							g.drawImage(block[i].img_black_grass,block[i].int_x,block[i].int_y - 12, this);
						}
					}
				}
			}
			
			//draw the platforms
			for(int i = 0; i < block.length; i++)
			{
				//if dirt
				if(dirt == true)
				{		
					//if on screen
					if(block[i].int_x < 1000 && (block[i].int_x + block[i].int_width) > 0 && (block[i].int_y + block[i].int_height) > 0 && block[i].int_y < 500)
					{
						//darw the block
						g.drawImage(block[i].img_block,block[i].int_x,block[i].int_y,this);
					}
				}
				//if steel
				else if(steel == true)
				{
					//if a block exists
					if(block[i] != null)
					{
						//if the block is on screen
						if(block[i].int_x < 1000 && (block[i].int_x + block[i].int_width) > 0 && block[i].int_y > 0 && (block[i].int_y + block[i].int_width) < 500)
						{
							g.drawImage(block[i].img_steel,block[i].int_x,block[i].int_y,this);
						}
					}
				}	
			}
			
			//draw the enemies
			for(int i = 0; i < diceMan.length; i++)
			{
				//if an enemy exists
				if(diceMan[i] != null)
				{
					//if they are on screen
					if(diceMan[i].int_x > 0 && diceMan[i].int_x < 1000 && diceMan[i].int_y > 0 && diceMan[i].int_y < 500)
					{
						//draw the enemy
						g.drawImage(diceMan[i].img_sprite_shell, diceMan[i].int_x, diceMan[i].int_y,this);
					}
				}
			}
			
			//draw the coins
			for(int i = 0; i < money.length; i++)
			{
				//if coins exists
				if(money[i] != null)
				{
					//if the money is on screen
					if(money[i].int_x > 0 && money[i].int_x < 1000 && money[i].int_y > 0 && money[i].int_y < 500)
					{
						//draw the coin
						g.drawImage(money[i].img_coin,money[i].int_x,money[i].int_y,this);
					}
				}
			}
			
			//draw the level portal
			if(portal.int_x > 0 && portal.int_x < 1000 && portal.int_y > 0 && portal.int_y < 500)
			{
				g.drawImage(portal.img_portal,portal.int_x,portal.int_y,this);
			}
			
			//set the color to black
			g.setColor(Color.black);
			
			//draw the box around the score etc
			g.fillRect(10, 10, 150, 80);
			
			//set color to white
			g.setColor(Color.white);
			
			//draw the outline for the box
			g.drawRect(10, 10, 150, 80);
			
			//set the color to yellow
			g.setColor(color_timer);
			
			//if there are less than 10 seconds left
			if(int_seconds < 10)
			{
				//draw the time with a zero
				g.drawString("Time Remaining : " + int_minutes + ":0" + int_seconds, 15,25);
			}
			//otherwise
			else
			{
				//draw the time without a zero
				g.drawString("Time Remaining : " + int_minutes + ":" + int_seconds, 15,25);
			}

			//draw the player's score
			g.drawString("Score : " + int_score,15,40);
			
			//draw the lives
			for(int i = 0; i < int_livesX.length; i++)
			{
				if(heart[i] != null)
				{
					g.drawImage(Main.img_life, heart[i].int_x, heart[i].int_y,this);
				}
			}
			
			//call class specific painting
			if(int_lvl == 0)
			{
				lvl_tutorial.paint(g);
			}
			else if(int_lvl == 1)
			{
				lvl_1.paint(g);
			}	
		}
		
		//game timer
		Timer update = new Timer(15,new ActionListener(){public void actionPerformed(ActionEvent e){
			if(Enabled == true)
			{
				gameloop();
				repaint(); //repaint the screen
			}
		}});
		
		//game timer
		Timer gameTimer = new Timer(1000,new ActionListener(){public void actionPerformed(ActionEvent e){
			if(bool_timer == true)
			{
				
				//tell how many seconds have elapsed
				int_seconds -= 1;
				
				if(int_minutes <= 0 && int_seconds <= 0)
				{
					//set the timer to zero
					int_minutes = 0;
					int_seconds = 0;
				}
				//tell if a minute has elapsed
				else if(int_seconds <= 0 && int_minutes > 0)
				{
					//reset the seconds
					int_seconds = 59;
					//add a minute
					int_minutes -= 1;
				}
				
				//if time is above the red point
				if(int_minutes >= 1 && int_seconds >= 0)
				{
					//draw it yellow
					color_timer = Color.yellow;
				}
				//otherwise
				else
				{
					//draw it red
					color_timer = Color.red;
				}
				
				//if you run out of time
				if(int_minutes == 0 && int_seconds == 0)
				{
					//kill the player
					hero.die(int_lvl);
				}
			}
		}});
	}
