import java.awt.Image;

import Collision.collision;

//platform object
class Platform
{
	
	int int_x; //horizontal location
	int int_y; //vertical location
	int int_width = 50; //width
	int int_height = 50; //height
	int int_dx; //the x speed
	int int_dy; //the y speed
	Image img_block = Main.img_block;
	Image img_green_grass = Main.img_green_grass; //get the grass
	Image img_yellow_grass = Main.img_yellow_grass; // the yellow grass
	Image img_black_grass = Main.img_black_grass; //the black grass
	Image img_steel = Main.img_steel; //the steel platform image
	boolean collision_right; //the right collision boolean
	boolean collision_left; //the left collision boolean
	boolean collision_top; //the top collision boolean
	int int_trackX = 0; //the distance the platform has moved x
	int int_trackY = 0; //the distance the platform has moved y
	String String_type; //the string which tells the platform image
	
	//constructor - innitializing information passed to here
	public Platform(int x, int y,int dx, int dy)
	{
		//set the coordinates
		int_x = x * 50; //x coordinate
		int_y = y * 50; //y coordinate
		int_dx = dx; //the x speed
		int_dy = dy; //the y speed
	}
	
	//move the platforms - particularly useful when the play is "moving"
	public void move(String dir,int speed)
	{
		if(dir.equals("right")) //if the argument is saying to move right
		{
			int_x -= speed; //move right
		}
		else if(dir.equals("left")) //if the argument is saying to move left
		{
			int_x += speed; //move left
		}
		else if(dir.equals("up")) //if the argument tells the platform to move up
		{
			int_y -= speed; //move up
		}
		else if(dir.equals("down")) //if the argument is telling the platform to move down
		{
			int_y += speed; //move down
		}
		else
		{
			//otherwise, say that there was an error
			 System.out.println("Improper Argument");
		}
	}
	
	public void bounds()
	{
		//loop through the platforms to see if they have collided with each other
		for(int i = 0; i < Main.playSurface.int_platformsX.length; i++)
		{
			if(int_dx < 0 || int_dx > 0 || int_dy < 0 || int_dy > 0)
			{
				//if the block is checking to collide with itself
				if(Main.playSurface.block[i].int_x == int_x && Main.playSurface.block[i].int_y == int_y)
				{
					//skip this block
					continue;
				}
				//if a block has collided with another
				if(collision.checkCollide(int_x, int_y, int_width, int_height, Main.playSurface.block[i].int_x, Main.playSurface.block[i].int_y, Main.playSurface.block[i].int_width, Main.playSurface.block[i].int_height) == true)
				{
					//reverse direction
					int_dx = -int_dx;
					int_dy = -int_dy;
				}
			}
		}
	}
	
	//the platforms smart move function
	public void moveAI()
	{
		//check the player boundaries
		bounds();
		
		//move sideways if the platform has a speed
		int_x += int_dx;
		//move up or down if the platform has a speed
		int_y += int_dy;

		//increase the distance that the platform has moved
		int_trackX += int_dx;
		int_trackY += int_dy;
		
		//if the platform has moved to the limit of its track
		if(int_trackX / 50 >= 5)
		{
			int_trackX = 0; //reverse the platform's speed
			int_dx = -int_dx;
		}
		else if(int_trackX / 50 <= -5) //if the platform has moved to the end of its track
		{
			int_trackX = 0; //reverse its speed
			int_dx = -int_dx;
		}
		
		//if the platform has moved to the end of its track (y)
		if(int_trackY / 50 >= 5)
		{
			int_trackY = 0; //reverse the direction
			int_dy = -int_dy;
		}
		else if(int_trackY / 50 <= -5) //if the platform has moved to the end of the track (y)
		{
			int_trackY = 0; //reverse the direction
			int_dy = -int_dy;
		}
	}
}

