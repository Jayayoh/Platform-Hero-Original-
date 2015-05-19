//import the required codes and libraries
import java.awt.Graphics;
import java.awt.Image;

//the JPanel for the first level
class lvl_1
{
	
	// platform x coordinates -- every 30 platforms is a new line
	static final int int_platformsX[] = {1,1,1,1,1,1,2,2,3,3,4,5,6,7,8,11,14,17,19,17,17,21,21,21,22,22,23,24,24,25,
		25,26,26,27,28,29,30,31,31,31,31,31,31,31,31,31,31,31,31,32,33,34,34,34,34,34,34,34,34,34,
		34,35,36,36,37,38,46,38,39,39,40,40,41,41,41,41,41,41,41,42,44,36,34,32,41,40,39,38,37,42,
		43,44,45,46,47,36,37,47,48,44,45,46,47,48,49,50,51,49,51,51,51,51,51,51,51,51,51,8,11,8,
		11,8,11,9,10,35,37,39,42,44,46,48,49,50,51,52,49,49,49,49,49,49,49,49,49,49,50,51,52,53,
		54,55,56,57,58,59,60,61,62,63,64,65,66,66,66,66,52,52,52,52,52,52,52,52,52,52,52,52,53,53,
		53,54,55,56,57,58,59,60,61,62,63,55,56,56,56,56,56,56,56,55,56,56,57,58,59,60,63,63,63,63,
		63,63,63,63,64,64,64,63,62,61,60,59,59,60,61,62,67,68,64,61,68,69,69,58,58,58,58,58,58,58,
		70,70,53,54,58,59,67,68,58,58,58,58,54,55,53,54,59,60,61,62,63,64,65,66,67,68,69,72,75,78,
		81,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,99,68,69,70,71,72,73,74,75,76,
		77,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95,95,98,101,104,107,103,110,111,112,105,
		113,114,115,116,117,118,119,120}; 
	//platform y coordinates -- every 30 platforms is a new line
	static final int int_platformsY[] = {2,3,4,5,6,7,7,8,8,9,9,9,9,9,9,9,9,9,7,3,-1,1,5,-3,-3,-4,-4,-4,-3,-3,
		-2,-2,-1,-1,-1,-1,-1,-1,0,1,2,3,4,5,6,7,8,9,10,10,10,10,7,6,5,4,3,2,1,0,
		-1,-1,-1,-2,-2,-2,-2,-3,-3,-4,-4,-5,-5,-6,-7,-8,-9,-10,-11,-6,-4,-7,-9,-11,-12,-12,-12,-12,-12,-12,
		-12,-12,-12,-12,-12,-12,-13,-13,-13,-9,-9,-9,-9,-9,-9,-10,-10,-10,-11,-12,-13,-14,-15,-16,-17,-18,-19,26,26,27,
		27,28,28,28,28,12,14,16,16,14,16,0,0,0,0,0,16,17,18,19,20,21,22,23,24,25,25,25,25,25,
		25,25,25,25,25,25,25,25,25,25,25,25,25,24,26,16,11,12,13,14,15,16,17,18,19,20,21,22,22,17,
		13,13,13,13,13,13,13,13,13,13,13,24,24,23,22,21,20,19,18,20,17,16,16,16,16,16,14,15,16,17,
		18,19,20,21,14,18,22,22,22,22,22,22,19,19,19,19,-13,-13,-13,-13,-14,-14,-15,-12,-11,-10,-9,-8,-7,-6,
		-15,-16,-5,-5,-18,-18,-18,-18,-13,-14,-15,-16,-11,-11,-15,-15,-8,-8,-6,-6,-6,-6,-6,-5,-4,-3,-2,-1,-1,-1,
		-1,-2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,22,20,18,16,14,12,10,8,6,
		3,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,3,3,3,3,3,0,3,3,3,4,
		3,3,3,3,3,3,3,3};
	//the horizontal speed of the platforms
	static final int int_platformsDX[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,-1,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0};
	//the vertical speed of the platforms
	static final int int_platformsDY[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,1,1,0,0,0,0,0,0,0,0,-1,-1,1,1,-1,-1,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0};
	//the enemies x coordinates
	static final int int_enemyX[] = {38,38,57,57,86,87,88,89,90,91,92,93,94,95,80,81,82,83,84,85,86,87,88,89};
	//the enemy y coordinate
	static final int int_enemyY[] = {-14,-14,24,24,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,3,3,3,3,3,3,3,3,3,3};
	//the enemy speed
	static final int int_enemyDX[] = {1,2,1,2,1,2,3,2,1,2,3,2,1,2,1,2,3,2,3,2,1,2,1,2};
	//boolean for enabled
	static boolean Enabled = false; //is the JPanel enabled?
	static Image img_background = Main.img_background1; //get the background image
	//the event queue
	static int int_event = 0;
	//the money array
	static int int_moneyX[] = {10,11,12,13,14,21,17,21,17,31,32,33,34,82,83,84,85,86,87,88,89,90,91,92,93,59,60,61,62};
	//the money y array
	static int int_moneyY[] = {5,5,5,5,5,4,2,0,-2,-2,-2,-2,-2,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,-3,21,21,21,21};
	
	//constructor which takes no arguments
	public lvl_1()
	{
	}
	
	//initialization
	public static void init()
	{
		//set the platforms array length
		Main.playSurface.block = new Platform[int_platformsX.length];
		
		//set the enemy array length
		Main.playSurface.diceMan = new enemy_dice[int_enemyX.length];
		
		//set the coins array
		Main.playSurface.money = new coin[int_moneyX.length];
		
		//the level changer - one per level
		Main.playSurface.portal = new lvl_changer(118,1,2);
		
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
		Main.playSurface.String_grass_color = "green";
		Main.playSurface.dirt = true;
		Main.playSurface.steel = false;
		
		//start the timer
		Main.playSurface.bool_timer = true;
		
		//reset the time
		Main.playSurface.int_seconds = 0;
		Main.playSurface.int_minutes = 3;
		
		//tell which level is active
		Main.playSurface.int_lvl = 1;
		
		//tell the game surface to re-initialise
		Main.playSurface.init();
	}
	
	//the game loop: events happen here
	public static void gameloop()
	{
		//no level specific coding
	}
	
	//level specific painting
	public static void paint(Graphics g)
	{
		//no level specific painting
	}
}


