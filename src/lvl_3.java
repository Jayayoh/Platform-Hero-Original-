//import the necessary libraries and codes
import java.awt.Graphics;
import java.awt.Image;


public class lvl_3 
{
	// platform x coordinates -- seperated by rows,colums
	static final int int_platformsX[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,18,19,20,21,22,23,24,25,26,27,15,15,15,15,15,
		15,15,15,15,15,15,15,15,15,15,18,18,18,18,18,18,18,18,18,18,18,18,18,15,16,17,18,19,20,21,
		26,27,32,33,33,34,39,44,49,34,34,35,36,37,38,39,40,40,43,46,49,50,51,51,52,53,54,55,56,57,
		58,58}; 
	//platform y coordinates -- every 30 platforms is a new line
	static final int int_platformsY[] = {8,8,8,8,8,8,8,8,8,8,9,10,11,12,13,13,12,11,10,9,8,8,8,8,8,14,15,16,17,18,
		19,20,21,22,23,24,25,26,27,28,14,15,16,17,18,19,20,21,22,23,24,25,26,28,28,28,28,28,28,28,
		28,28,28,27,29,28,28,28,28,21,22,22,22,22,22,22,22,21,18,15,12,12,5,6,6,6,6,6,6,6,
		6,5};
	//the horizontal speed of the platforms
	static final int int_platformsDX[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		-1,1,0,0,0,0,-1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0};
	//the vertical speed of the platforms
	static final int int_platformsDY[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,-1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,-1,0,0,0,0,0,0,0,0,
		0,0};
	//the enemies x coordinates
	static final int int_enemyX[] = {35,36,37,55,56,55};
	//the enemy y coordinate
	static final int int_enemyY[] = {20,20,20,4,4,4};
	//the enemy speed
	static final int int_enemyDX[] = {1,2,3,1,2,3};
	//boolean for enabled
	static boolean Enabled = false;
	//get the background image
	static Image img_background = Main.img_background3;
	//the event queue
	static int int_event = 0;
	//the money array
	static int int_moneyX[] = {5,6,7,8,9,24,25,26,27,28,24,25,26,27,38,39,40,41,42,43,44,45,33,33,33,33,33};
	//the money y array
	static int int_moneyY[] = {5,5,5,5,5,5,5,5,5,5,27,27,27,27,27,27,27,27,27,27,27,27,28,28,28,28,28};
	
	//Constructor which takes no arguments
	public lvl_3()
	{
	}
	
	//Initialisation
	public static void init()
	{
		
		//set the platforms array length
		Main.playSurface.block = new Platform[int_platformsX.length];
		
		//set the enemy array length
		Main.playSurface.diceMan = new enemy_dice[int_enemyX.length];
		
		//set the coins array
		Main.playSurface.money = new coin[int_moneyX.length];
		
		//the level changer - one per level
		Main.playSurface.portal = new lvl_changer(105,25,2);
		
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
		Main.playSurface.grass = true;
		
		//what color of grass?
		Main.playSurface.String_grass_color ="black";
		Main.playSurface.dirt = true;
		Main.playSurface.steel = false;
		
		//start the timer
		Main.playSurface.bool_timer = true;
		
		//reset the time
		Main.playSurface.int_seconds = 0;
		Main.playSurface.int_minutes = 3;
		
		//tell which level is active
		Main.playSurface.int_lvl = 3;
		
		//tell the game surface to re-initialise
		Main.playSurface.init();
	}
	
	//the game loop: events happen here
	public static void gameloop()
	{
		//no level specific code yet
	}
	
	//level specific painting
	public static void paint(Graphics g)
	{
		//no level specific painting yet
	}
}
