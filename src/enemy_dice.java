/*
 * The Enemy Class. THis class will be "spawned" around the map, and will 
 * try to shoot the player when he gets too close
 */

//import all of the necessary libraries and code
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Collision.collision;

class enemy_dice 
{
	//set all the variables
	
	//the x position
	int int_x;
	//the y position
	int int_y;
	//the width **already preset
	final int int_width = 34;
	//the Height ** already preset
	final int int_height = 42;
	//the Xspeed
	int int_dx;
	//the Y speed
	int int_dy = 3;
	//the shell image
	Image img_sprite_shell = Main.tool.getImage("Images/diceStandRight.png");
	//image for the enemies (animation) right
	Image img_sprite_right[] = {
			Main.tool.getImage("Images/diceStandRight.png"),
			Main.tool.getImage("Images/diceWalkRight1.png"),
			Main.tool.getImage("Images/diceWalkRight2.png"),
			Main.tool.getImage("Images/diceWalkRight3.png"),
			Main.tool.getImage("Images/diceWalkRight4.png"),
			Main.tool.getImage("Images/diceWalkRight5.png")
	};
	//images for the enemies (animation) left
	Image img_sprite_left[] = {
			Main.tool.getImage("Images/diceStandLeft.png"),
			Main.tool.getImage("Images/diceWalkLeft1.png"),
			Main.tool.getImage("Images/diceWalkLeft2.png"),
			Main.tool.getImage("Images/diceWalkLeft3.png"),
			Main.tool.getImage("Images/diceWalkLeft4.png"),
			Main.tool.getImage("Images/diceWalkLeft5.png")
	};
	//the bullet array
	bullet Bullet[] = new bullet[30];
	//the gravity coutner
	int int_gravity_counter = 0;
	//the animate coutner
	int int_animate_counter = 0;
	//the falling boolean
	boolean bool_falling = true;
	//the left boolean
	boolean bool_left = false;
	//the right boolean
	boolean bool_right = true;
	//is he facing right?
	boolean bool_face_right = true;
	//is he facing left
	boolean bool_face_left = false;
	//can he shoot?
	boolean bool_shootable = true;
	
	
	//the constructor : this creates the enemy using the proivided arguments
	public enemy_dice(int x, int y, int dx)
	{
		int_x = x * 50; //set the x coodinates
		int_y = y * 50; //set the y coordinates
		int_dx = dx; //set the speed of the enemies
	}
	
	public void gravity()
	{
		//increase the gravity counter
		int_gravity_counter += 1;
		
		//if the gravity counter is low, it has been recently reset, so reset the players vertical speed
		if(int_gravity_counter < 10)
		{
			int_dy = 3;
		}
		
		int_y += int_dy; //make the enemy fall
		
		//increase gravity
		switch (int_gravity_counter)
		{
			case 5: int_dy += 2; break; //this will gradually make gravity increase, like in real life
			case 7: int_dy += 2; break;
			case 10: int_dy += 3; break;
			case 15: int_dy += 4; break;
		}	
	}
	
	//animate the enemy right
	public void animateRight()
	{
		
		//increase the counter
		int_animate_counter += 1;
		
		//set the shell image to the right image according to the counter
		switch (int_animate_counter)
		{
		//this will animate the enemy according to the counter
			case 5: img_sprite_shell = img_sprite_right[0]; break;
			case 10: img_sprite_shell = img_sprite_right[1]; break;
			case 15: img_sprite_shell = img_sprite_right[2]; break;
			case 20: img_sprite_shell = img_sprite_right[3]; break;
			case 25: img_sprite_shell = img_sprite_right[4]; int_animate_counter = 0; break;
		}
		
		//fail safe **change
		if(int_animate_counter > 18)
		{
			int_animate_counter = 0;
		}
	}
	
	//animate the character
		public void animateLeft()
		{
			//increase the counter
			int_animate_counter += 1;
			
			//set the shell image to the right image according to the counter
			switch (int_animate_counter)
			{
				//set the image according to the counter
				case 5: img_sprite_shell = img_sprite_left[0]; break;
				case 10: img_sprite_shell = img_sprite_left[1]; break;
				case 15: img_sprite_shell = img_sprite_left[2]; break;
				case 20: img_sprite_shell = img_sprite_left[3]; break;
				case 25: img_sprite_shell = img_sprite_left[4]; int_animate_counter = 0; break;
			}
			
			//fail safe **change
			if(int_animate_counter > 18)
			{
				int_animate_counter = 0;
			}
		}
		
		//enemy boundaries
		public void bounds()
		{
			//check for collisions
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				//check to see if a collision has happened
				if(collision.checkCollide(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
				{
					//if so, check to see if it was on the top
					if(collision.checkLocationTop(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
					{
						//if so...
						int_y = Main.playSurface.block[i].int_y - int_height + 2;


						bool_falling = false; //stop the player
						int_gravity_counter = 0; //reset the gravity increaser
						
						//MOVE if the platform is moving
						
						for(int j = 0; j < Main.playSurface.int_platformsX.length; j++)
						{
							Main.playSurface.block[j].int_x -= Main.playSurface.block[i].int_dx;
							Main.playSurface.block[j].int_y -= Main.playSurface.block[i].int_dy;
						}

						break;
						
					}
					else
					{
						//make the enemy fall
						bool_falling = true;
					}
				}
			}
			
			//iterate through all the platforms
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				//check to see if a collision has happened
				if(collision.checkCollide(int_x - 1, int_y, int_width + 2, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
				{
					//if the enemy has not hit the bottom of a platform
					if(collision.checkLocationBottom(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
					{	
						//if the enemy has hit the right
						if(collision.checkLocationRight(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
						{
							//if the enemy is not on the top
							if(collision.checkLocationTop(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
							{
								//set the enemy's location so that no physics related glitches occur
								int_x = Main.playSurface.block[i].int_x + Main.playSurface.block[i].int_width;
								
								//tell it not to go left
								bool_left = false;
								bool_right = true;
								break;
							}
						}
						
						//if the enemy has hit the left
						if(collision.checkLocationLeft(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
						{
							//if the enemy is not on the top
							if(collision.checkLocationTop(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
							{
								
								//set the player's location so that no physics related glitches occur
								int_x = Main.playSurface.block[i].int_x - int_width + 1;
								
								//tell it not to go right
								bool_right = false;
								bool_left = true;
								break;
							}
						}
					}
				}
			}
		}
		
		//the move method - all of the moving happens here!
		public void move()
		{
			//check the enemy bounds
			bounds();
			
			//computer vs player AI (this checks if the player is within 3 blocks of the enemy)
			if((int_x - Main.playSurface.hero.int_x) / 50 <= 3 && (int_x - Main.playSurface.hero.int_x) / 50 >= -3)
			{
				if((int_y - Main.playSurface.hero.int_y) / 50 <= 3 && (int_y - Main.playSurface.hero.int_y) / 50 >= -3)
				{
					//if the player is close enouigh, move towards the player and shoot
					if((int_x - Main.playSurface.hero.int_x) / 50 > 0)
					{
						bool_right = false;
						bool_left = true;
						shoot((float)Main.playSurface.hero.int_x,(float)Main.playSurface.hero.int_y, (float)Main.playSurface.hero.int_width, (float)Main.playSurface.hero.int_height);
					}
					else if((int_x - Main.playSurface.hero.int_x) / 50 < 0)
					{
						bool_left = false;
						bool_right = true;
						shoot((float)Main.playSurface.hero.int_x,(float)Main.playSurface.hero.int_y, (float)Main.playSurface.hero.int_width, (float)Main.playSurface.hero.int_height);
					}
				}
			}
			
			//if the game is telling the enemy to fall, then fall
			if(bool_falling == true)
			{
				gravity();
			}
			
			//if the game is telling the enemy to move right, then move right
			if(bool_right == true)
			{
				
				int_x += int_dx;
				
				//call the animations
				bool_face_right = true; //make the player face right **change
				bool_face_left = false; //make the player face left **change
			}
			//or if the game is telling the enemy to move left
			else if(bool_left == true)
			{
				
				int_x -= int_dx;
				
				//call the animations
				bool_face_right = false; //make the player face right **change
				bool_face_left = true; //make the player face left **change
			}
			
			//deal with the animations

			if(bool_left == true) //if moving left, animate left
			{
				animateLeft();
			}
			else if(bool_right == true) //if moving right, animate right
			{
				animateRight();
			}

			bool_falling = true;
		}
		
		//the shoot method
		public void shoot(float x, float y, float width, float height)
		{
			//divide the x and y
			x += width / 2;
			y += width / 2;
			
			//this equation gets the angle in which to shoot that, in the form of x speed and y speed
			float divisor = (float)Math.sqrt(Math.pow(x - int_x, 2) + Math.pow(y - int_y,2));
			float slopeX = (x - (float)int_x) / divisor;
			float slopeY = (y - (float)int_y) / divisor;
			
			//if the enemy can shoot
			if(bool_shootable == true)
			{
				//iterate through all the potential bullet
				for(int i = 0; i < Bullet.length; i++)
				{
					//if a bullet slot is open
					if(Bullet[i] == null)
					{
						//create a bullet
						Bullet[i] = new bullet(int_x + (int_width / 2),int_y + (int_height / 2),slopeX * 5,slopeY * 5);
						//prevent the enemy from immediately shooting again
						bool_shootable = false;
						//start the timer which tells when the enemy can shoot again
						timer_shoot.start();
						//break the loop
						break;
					}
				}
			}
		}
		
		//the shoot timer
		Timer timer_shoot = new Timer(200,new ActionListener(){public void actionPerformed(ActionEvent e){
			bool_shootable = true; //the enemy can now shoot
			timer_shoot.stop(); //stop the timer
		}});
}
