import java.awt.Image; //import the image functionality

import Collision.collision;//import the collision codes

class lvl_changer 
{
	//the x location
	int int_x;
	//the y location
	int int_y;
	//the width
	int int_width = 100;
	//the height
	int int_height = 100;
	//the level arguments
	int int_lvl;
	//the Image
	Image img_portal = Main.img_portal;
	
	//constructor for creating a portal instance
	public lvl_changer(int x, int y, int lvl)
	{
		int_x = x * 50; //set the x locations aligned with the platforms
		int_y = y * 50; //set the y locations aligned with the platforms
		int_lvl = lvl; //give the level integer a number, which tells the portal which level to go to
	}
	
	//portal boundaries
	public void bounds()
	{
		//check if the portal collides with the player
		if(collision.checkCollide(int_x, int_y, int_width, int_height, Main.playSurface.hero.int_x, Main.playSurface.hero.int_y, Main.playSurface.hero.int_width, Main.playSurface.hero.int_height) == true)
		{
			//if a collision has happened
			switch(int_lvl)
			{
				//if the level is one, go to level one
				case 1: lvl_1.init(); break;
				//if the level is two, go to level two
				case 2: lvl_2.init(); break;
				//if the level is three, go to level three
				case 3: lvl_3.init(); break;
			}
		}
	}
	
}
