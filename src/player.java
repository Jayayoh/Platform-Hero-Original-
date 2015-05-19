//import the necessary libraries, codes and files
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.swing.SwingWorker;
import javax.swing.Timer;

import Collision.collision;

//player class
class player 
{
	
	protected static final Void Void = null;
	//VARIABLES
	int int_x; //horizontal position
	int int_y; //vertical position
	int int_width = 34; //width
	int int_height = 42; //height
	int int_dx = 5; //horizontal speed
	int int_dy = 4; //vertical speed
	Image img_sprite_shell;
	//image array for walking right
	Image img_sprite_right[] = {
			Main.img_sprite_right[1],
			Main.img_sprite_right[2],
			Main.img_sprite_right[3],
			Main.img_sprite_right[4],
			Main.img_sprite_right[5]
		};
	//image array for walking Left
	Image img_sprite_left[] = {
			Main.img_sprite_left[1],
			Main.img_sprite_left[2],
			Main.img_sprite_left[3],
			Main.img_sprite_left[4],
			Main.img_sprite_left[5]
		};
	//image array for jumping right **change
	Image img_sprite_Jright[] = {
			Main.img_sprite_right[0],
			Main.img_sprite_right[1],
			Main.img_sprite_right[2],
			Main.img_sprite_right[3],
			Main.img_sprite_right[4],
			Main.img_sprite_right[5]
		};
	//image array for jumping Left **change
	Image img_sprite_Jleft[] = {
			Main.img_sprite_left[0],
			Main.img_sprite_left[1],
			Main.img_sprite_left[2],
			Main.img_sprite_left[3],
			Main.img_sprite_left[4],
			Main.img_sprite_left[5]
		};
	//the falling images
	Image img_sprite_falling_right = Main.img_sprite_falling_right;
	Image img_sprite_falling_left = Main.img_sprite_falling_left;
	int int_gravity_counter = 0; //the generic gravity counter
	int int_jump_counter = 0; //similar to the gravity counter, but for the reverse function (going up!)
	int int_animate_counter = 0; //the counter to set the animation
	int int_health = 100; //the player's health
	boolean bool_falling = true; //is it falling or not?
	boolean bool_right = false; //is he going right?
	boolean bool_left = false; //is he going left?
	boolean bool_rightable = true; //is he able to go right?
	boolean bool_leftable = true; //is he able to go left?
	boolean bool_face_right = true; //is he facing right? **change
	boolean bool_face_left = false; //is he facing left? **change
	boolean bool_jumping = false; //is he jumping?
	boolean bool_jumpable = false; //can he jump?
	boolean bool_damagable = true; //can he be damaged?
	Color color_health = Color.green; //the health color
	File sound_file_jump = new File("sounds/Jump.wav"); //the sound file for jumping
	Clip clip_jump; //the clip object for the sound file

	//player constructor - pass initialising information here
	public player(int x, int y)
	{
		//set the coordinates
		int_x = x;
		int_y = y;
		
		//set the jump sounds
		try
		{
			//set up the sound in the clip
			AudioInputStream audioin = AudioSystem.getAudioInputStream(sound_file_jump);
			clip_jump = AudioSystem.getClip();
			clip_jump.open(audioin);
			
			//decrease the volume of the clip
			FloatControl cool = (FloatControl)clip_jump.getControl(FloatControl.Type.MASTER_GAIN);
			cool.setValue(-8.0f);
		}
		catch(Exception e) //if there is an error, tell what the error is
		{
			System.out.println(e);
		}
	}
	
	//player boundaries
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
					if(int_y < 350) //if the player is above a certain height, then set his height
					{
						int_y = Main.playSurface.block[i].int_y - int_height + 2;
					}
					else //otherwise, set the paltform's height
					{
						int_y = 351;
					}

					bool_falling = false; //stop the player
					int_gravity_counter = 0; //reset the gravity increaser
					bool_jumpable = true; //allow the player to jump
					
					//MOVE if the platform is moving
					
					//move the platforms according to the platform's speed
					for(int j = 0; j < Main.playSurface.int_platformsX.length; j++)
					{
						Main.playSurface.block[j].int_x -= Main.playSurface.block[i].int_dx;
						Main.playSurface.block[j].int_y -= Main.playSurface.block[i].int_dy;
					}
					
					//move the coins according to the platform's speed
					for(int j = 0; j < Main.playSurface.money.length; j++)
					{
						//if the coin exists
						if(Main.playSurface.money[j] != null)
						{
							Main.playSurface.money[j].int_x -= Main.playSurface.block[i].int_dx;
							Main.playSurface.money[j].int_y -= Main.playSurface.block[i].int_dy;
						}
					}
					
					//move the enemies according to the platform's speed
					for(int j = 0; j < Main.playSurface.diceMan.length; j++)
					{
						//if the enemy exists
						if(Main.playSurface.diceMan[j] != null)
						{
							Main.playSurface.diceMan[j].int_x -= Main.playSurface.block[i].int_dx;
							Main.playSurface.diceMan[j].int_y -= Main.playSurface.block[i].int_dy;
						}
					}
					
					//move the portal according to the paltform's speed
					Main.playSurface.portal.int_x -= Main.playSurface.block[i].int_dx;
					Main.playSurface.portal.int_y -= Main.playSurface.block[i].int_dy;
					
					//test purposes only
					Main.playSurface.int_stand = i;
					
					//what platform is the player standing on? and its X/Y coordinates
					System.out.println("platform : " + i + " x = " + (((Main.playSurface.block[i].int_x -(Main.playSurface.block[0].int_x)) / 50) + Main.playSurface.int_platformsX_ref[0]) + " Y = " + -((Main.playSurface.block[0].int_y - Main.playSurface.block[i].int_y) / 50 - Main.playSurface.int_platformsY_ref[0]));
					
					//break out of the loop
					break;
					
				}
				else
				{
					//if no collision, then the player falls
					bool_falling = true;
				}
			}
		}
		
		//check collisions with the player, but for the left and right
		for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
		{
			//check to see if a collision has happened
			if(collision.checkCollide(int_x - 1, int_y, int_width + 2, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
			{
				//if the player has not collided with the bottom af a platform
				if(collision.checkLocationBottom(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
				{
					//if the player has collided witht eh right of a platform
					if(collision.checkLocationRight(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
					{
						//if the player is not on top of a platform
						if(collision.checkLocationTop(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
						{
							//set the player's location so that no physics related glitches occur
							int_x = Main.playSurface.block[i].int_x + Main.playSurface.block[i].int_width;
							
							//tell it not to go left
							bool_leftable = false;
							break;
						}
					}
					
					//if the player has collided with the left of the platform
					if(collision.checkLocationLeft(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
					{
						if(collision.checkLocationTop(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) != true)
						{
							
							//set the player's location so that no physics related glitches occur
							int_x = Main.playSurface.block[i].int_x - int_width + 1;
							
							//tell it not to go right
							bool_rightable = false;
							break;
						}
					}
				}
			}
			else//if the player has not collided with left or right
			{
				bool_rightable = true;//he can go left
				bool_leftable = true;//he can go right
			}
		}
		
		//collision with coins
		for(int i = 0; i < Main.playSurface.money.length; i++)
		{
			//if the coin exists
			if(Main.playSurface.money[i] != null)
			{
				//if the player has collided with a coin
				if(collision.checkCollide(int_x, int_y, int_width, int_height, Main.playSurface.money[i].int_x, Main.playSurface.money[i].int_y, Main.playSurface.money[i].int_width, Main.playSurface.money[i].int_height) == true)
				{
					Main.playSurface.int_score += 15;//increase score
					Main.playSurface.money[i].clip.start(); //play the sound
					Main.playSurface.money[i] = null; //delete the coin
					break;//break the loop
				}
			}
		}
	}
	
	//gravity physics
	public void gravity()
	{
		//increase the gravity counter
		int_gravity_counter += 1;
		
		//make it so that the player cannot double jump
		bool_jumpable = false;
		
		//if the gravity counter is low, it has been recently reset, so reset the players vertical speed
		if(int_gravity_counter < 10)
		{
			int_dy = 3;
		}
		
		//make the player move
		if(int_y < 350)//if he is below a point on the scrren
		{
			int_y += int_dy;//the player moves
		}
		else if(int_y >= 350) //if he is above a certain point on the screen
		{
			//the platforms move around the player
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				Main.playSurface.block[i].move("up", int_dy);
			}
			
			//enemies move around the player
			for(int i = 0; i < Main.playSurface.int_enemyX.length; i++)
			{
				//if an enemy exists
				if(Main.playSurface.diceMan[i] != null)
				{
					Main.playSurface.diceMan[i].int_y -= int_dy;
				}
			}
			
			//move the coins around the player
			for(int i = 0; i < Main.playSurface.money.length; i++)
			{
				//if a coin exists
				if(Main.playSurface.money[i] != null)
				{
					Main.playSurface.money[i].int_y -= int_dy;
				}
			}
			
			//move the portal around the player
			Main.playSurface.portal.int_y -= int_dy;
		}
		
		//increase gravity
		switch (int_gravity_counter)
		{
			case 5: int_dy += 2; break; //this will gradually make gravity increase, like in real life
			case 7: int_dy += 2; break;
			case 10: int_dy += 3; break;
			case 15: int_dy += 4; break;
		}	
	}
	
	//the jumping method
	public void jump()
	{
		//if the player can jump
		if(bool_jumpable == true)
		{
			//play the jump sound
			clip_jump.setFramePosition(0);
			clip_jump.stop();
			clip_jump.start();
		}
		//make it so that they cannot jump mid-air
		bool_jumpable = false;
		
		//increase the jump counter
		int_jump_counter += 1;
		
		//temporarily stop gravity
		bool_falling = false;
		
		//play the sound
		
		
		//set the vertical direction high to simulate the beggining of a jump
		if(int_jump_counter <= 1)
		{
			int_dy = 10;
		}
		
		//make the player move up
		if(int_y > 100) //if the player is below a certain height
		{
			int_y -= int_dy;
		}
		else if(int_y <= 100)//otherwise
		{
			//iterate through all of the platforms
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				Main.playSurface.block[i].move("down", int_dy); //make the platforms move instead
			}
			
			//make the enemies move
			for(int i = 0; i < Main.playSurface.diceMan.length; i++)
			{
				//if an enemy exists
				if(Main.playSurface.diceMan[i] != null)
				{
					Main.playSurface.diceMan[i].int_y += int_dy;
				}
			}
			
			//make the money move
			for(int i = 0; i < Main.playSurface.money.length; i++)
			{
				//if money exists
				if(Main.playSurface.money[i] != null)
				{
					Main.playSurface.money[i].int_y += int_dy;
				}
			}
			
			//make the portal move
			Main.playSurface.portal.int_y += int_dy;
		}
		
		//make the players jump decrease exponentially
		switch (int_jump_counter)
		{
			case 15: int_dy -= 2; break;
			case 20: int_dy -= 2; break;
			case 25: int_dy -= 3; break;
			case 30: int_dy -= 3; break;
		}
		
		//if the vertical movement is 0, then make the player fall
		if(int_dy <= 0)
		{
			bool_jumping = false;
			bool_falling = true;
		}
	}
	
	//animate the character when walking right
	public void animateRight()
	{
		
		//increase the counter
		int_animate_counter += 1;
		
		//set the shell image to the right image according to the counter
		switch (int_animate_counter)
		{
			case 4: img_sprite_shell = img_sprite_right[0]; break;
			case 8: img_sprite_shell = img_sprite_right[1]; break;
			case 12: img_sprite_shell = img_sprite_right[2]; break;
			case 16: img_sprite_shell = img_sprite_right[3]; break;
			case 20: img_sprite_shell = img_sprite_right[4]; int_animate_counter = 0; break;
		}
		
		//fail safe **change
		if(int_animate_counter > 18)
		{
			int_animate_counter = 0;
		}
	}
	
	//animate still **change
	public void animateStill()
	{
		//if he is facing right
		if(bool_face_right == true)
		{
			img_sprite_shell = img_sprite_right[3];
		}
		//otherwise
		else
		{
			img_sprite_shell = img_sprite_left[3];
		}
		
		//reset the counter
		int_animate_counter = 0;
	}
	
	//animate the character
	public void animateLeft()
	{
		//increase the counter
		int_animate_counter += 1;
		
		//set the shell image to the right image according to the counter
		switch (int_animate_counter)
		{
			case 4: img_sprite_shell = img_sprite_left[0]; break;
			case 8: img_sprite_shell = img_sprite_left[1]; break;
			case 12: img_sprite_shell = img_sprite_left[2]; break;
			case 16: img_sprite_shell = img_sprite_left[3]; break;
			case 20: img_sprite_shell = img_sprite_left[4]; int_animate_counter = 0; break;
		}
		
		//fail safe 
		if(int_animate_counter > 18)
		{
			int_animate_counter = 0;
		}
	}
	
	//animate while jumping **change
	public void animateRightJump()
	{
		//increase the counter
		int_animate_counter += 1;
			
		//set the shell image to the right image according to the counter
		switch (int_animate_counter)
		{
			case 3: img_sprite_shell = img_sprite_Jright[0]; break;
			case 6: img_sprite_shell = img_sprite_Jright[1]; break;
			case 9: img_sprite_shell = img_sprite_Jright[2]; break;
			case 12: img_sprite_shell = img_sprite_Jright[3]; break;
			case 15: img_sprite_shell = img_sprite_Jright[4]; break;
			case 18: img_sprite_shell = img_sprite_Jright[5];int_animate_counter = 0; break;
		}
		
		//fail safe **change
		if(int_animate_counter > 18)
		{
			int_animate_counter = 0;
		}
	}
	
	//animate while jumping 
	public void animateLeftJump()
	{
		//increase the counter
		int_animate_counter += 1;
					
		//set the shell image to the right image according to the counter
		switch (int_animate_counter)
		{
			case 3: img_sprite_shell = img_sprite_Jleft[0]; break;
			case 6: img_sprite_shell = img_sprite_Jleft[1]; break;
			case 9: img_sprite_shell = img_sprite_Jleft[2]; break;
			case 12: img_sprite_shell = img_sprite_Jleft[3]; break;
			case 15: img_sprite_shell = img_sprite_Jleft[4]; break;
			case 18: img_sprite_shell = img_sprite_Jleft[5]; int_animate_counter = 0; break;
		}
		
		//fail safe **change
		if(int_animate_counter > 18)
		{
			int_animate_counter = 0;
		}
	}
	
	//animate while falling ** change
	public void animateFalling()
	{
		//if he is facing right
		if(bool_face_right == true)
		{
			img_sprite_shell = img_sprite_falling_right;
		}
		else
		{
			img_sprite_shell = img_sprite_falling_left;
		}
				
		//reset the counter
		int_animate_counter = 0;
	}
	
	//the move method - all of the moving happens here!
	public void move()
	{
		//check the player's boundaries
		bounds();
		
		//if he is falling
		if(bool_falling == true)
		{
			bool_jumping = false;//he cannot jump
			gravity();//make him fall
		}
		else if(bool_jumping == true)//if he is jumping
		{
			bool_falling = false;//he is not falling
			jump();//make him jump
		}
		
		//if he can go right and is being told to go right
		if(bool_rightable == true && bool_right == true)
		{
			//move all platforms
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				Main.playSurface.block[i].move("right", int_dx);
			}
			
			//move all enemies
			for(int i = 0; i < Main.playSurface.int_enemyX.length; i++)
			{
				//if an enemy exists
				if(Main.playSurface.diceMan[i] != null)
				{
					Main.playSurface.diceMan[i].int_x -= int_dx;
				
					//move all enemy bullets, if they exist
					for(int j = 0; j < Main.playSurface.diceMan[i].Bullet.length; j ++)
					{
						if(Main.playSurface.diceMan[i].Bullet[j] != null)
						{
							Main.playSurface.diceMan[i].Bullet[j].int_x -= int_dx;
						}
					}
				}
			}
			
			//move all coins
			for(int i = 0; i < Main.playSurface.money.length; i++)
			{
				//if coins exist
				if(Main.playSurface.money[i] != null)
				{
					Main.playSurface.money[i].int_x -= int_dx;
				}
			}
			
			//move the portal
			Main.playSurface.portal.int_x -= int_dx;

			//call the animations
			bool_face_right = true; //make the player face right **change
			bool_face_left = false; //make the player face left **change
		}
		//if you can go left and are being told to go left
		else if(bool_leftable == true && bool_left == true)
		{
			//move all platforms
			for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
			{
				Main.playSurface.block[i].move("left", int_dx);
			}
			
			//move all enemies
			for(int i = 0; i < Main.playSurface.int_enemyX.length; i++)
			{
				//if enemies exist
				if(Main.playSurface.diceMan[i] != null)
				{
					Main.playSurface.diceMan[i].int_x += int_dx;
				
					//move all enemy bullets, if the exist
					for(int j = 0; j < Main.playSurface.diceMan[i].Bullet.length; j ++)
					{
						if(Main.playSurface.diceMan[i].Bullet[j] != null)
						{
							Main.playSurface.diceMan[i].Bullet[j].int_x += int_dx;
						}
					}
				}
			}
			
			//move all coins
			for(int i = 0; i < Main.playSurface.money.length; i++)
			{
				//if coins exist
				if(Main.playSurface.money[i] != null)
				{
					Main.playSurface.money[i].int_x += int_dx;
				}
			}
			
			//move the portal
			Main.playSurface.portal.int_x += int_dx;
			
			
			//call the animations
			bool_face_right = false; //make the player face right **change
			bool_face_left = true; //make the player face left **change
		}
		
		//deal with the animations
		
		//if stationary
		if(bool_right != true && bool_left != true && bool_jumping != true && bool_falling != true)
		{
			animateStill();
		}
		//if he is falling
		else if(bool_falling == true)
		{
			animateFalling();
		}
		//if he is walking left
		else if(bool_left == true && bool_falling != true && bool_jumping != true)
		{
			animateLeft();
		}
		//if he is walking right
		else if(bool_right == true && bool_falling != true && bool_jumping != true)
		{
			animateRight();
		}
		//if he is jumping right
		else if(bool_jumping == true && bool_right == true)
		{
			animateRightJump();
		}
		//if he is jumping left
		else if(bool_left == true && bool_jumping == true)
		{
			animateLeftJump();
		}
		
		//if he is falling
		if(bool_jumping != true)
		{
			bool_falling = true; //make him fall
		}	
		
		//kill the player if he's fallen too far
		if(int_gravity_counter >= 400)
		{
			die(Main.playSurface.int_lvl);
		}
	}
	
	//the method to deal with the player when he dies
	public void die(int lvl)
	{
		//take the level data, temporarily save it and set it so that the lvl reset isnt complete

		//take which level is active, and reset the level
		if(Main.playSurface.int_lives >= 0)
		{
			if(lvl == 0)
			{
				lvl_tutorial.init(); //tutorial level reset
			}
			else if(lvl == 1)
			{
				lvl_1.init(); //first level reset
			}
			else if(lvl == 2)
			{
				lvl_2.init(); //second level reset
			}
			else if(lvl == 3)
			{
				lvl_3.init(); //third level reset
			}
			
			//delete one of the heart objects
			Main.playSurface.heart[Main.playSurface.int_lives] = null;
			
			//decrease lives by one
			Main.playSurface.int_lives -= 1;
			
		} else {
			//stop any sound if its playing
			switch(Main.playSurface.int_lvl)
			{
				case 1: Main.playSurface.music[0].stop(); break;
				case 2: Main.playSurface.music[1].stop(); break;
				case 3: Main.playSurface.music[2].stop(); break;
			}
			//set the gameSurface so that it doesnt keep playing
			Main.playSurface.Enabled = false; 
			//reset the player's score
			Main.playSurface.int_score = 0;
			//reset the player's lives
			Main.playSurface.int_lives = 2;
			//declare the cardlayout
			CardLayout cl = (CardLayout)(Main.screens.getLayout());  
			//tell the cardlayout to shuffle to the next card
			cl.show(Main.screens, "GameOver"); 
		}
	}
	
	//take damage when called
	public void takeDamage(int damage)
	{
		//take the damage specified if he can be damaged
		if(bool_damagable == true)
		{
			int_health -= damage;
		}
		
		//change the health bar color if the health is below a certain level
		if(int_health <= 15)
		{
			color_health = Color.red; //if health is below 15, turn red
		}
		else if(int_health <= 25)
		{
			color_health = Color.orange; //if health is below 25, turn orange
		}
		else if(int_health <= 50)
		{
			color_health = Color.yellow; //if health is below 50, turn yellow
		}
		
		//reset the player if he dies
		if(int_health <= 0)
		{
			die(Main.playSurface.int_lvl);
		}
	}
	
	//the timer, if the player has been damaged, then he cannot be damaged again for two seconds
	Timer timer_damage = new Timer(2000,new ActionListener(){public void actionPerformed(ActionEvent e){
		bool_damagable = true;
		timer_damage.stop();//stop the timer when done
	}});
}
