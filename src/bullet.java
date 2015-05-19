//import the required libraries and codes
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;

import Collision.collision;

//the class
class bullet
{
	//the x coordinate
	int int_x;
	//the y coordinate
	int int_y;
	//the width
	int int_width = 10;
	//the height
	int int_height = 10;
	//the x speed
	float int_dx;
	//the y speed
	float int_dy;
	
	//create the buller
	public bullet(int x, int y, float dx, float dy)
	{
		int_x = x; //get an argument for the x
		int_y = y; //get an argument for the y
		int_dx = dx; //get an argument for the x speed
		int_dy = dy; //get an argument for the y speed
	}
	
	//move the bullet
	public void move()
	{
		int_x += int_dx; //move it horizontally
		int_y += int_dy; //move it vertically
	}
}
