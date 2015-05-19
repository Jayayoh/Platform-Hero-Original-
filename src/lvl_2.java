import java.awt.Graphics;
import java.awt.Image;

//the JPanel for the first level
class lvl_2
{
	
	// platform x coordinates -- seperated by rows,colums
	static final int int_platformsX[] = {3,4,5,6,7,8,9,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		5,10,15,20,25,30,35,40,45,50,55,60,65,70,76,80,85,90,95,100,
		1,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30, //this one has 31
		31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,
		61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,
		91,92,93,94,95,96,97,98,99,100,100}; 
	//platform y coordinates -- every 30 platforms is a new line
	static final int int_platformsY[] = {6,6,6,6,6,6,6,
		10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,10,
		15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,15,
		20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,20,
		25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,
		30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,
		35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,35,
		40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,40,
		45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,45,
		50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,
		55,54,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55, //this one has 31
		55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,
		55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,55,
		55,55,55,55,55,55,55,55,55,55,54};
	//the horizontal speed of the platforms
	static final int int_platformsDX[] = {0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0};
	//the vertical speed of the platforms
	static final int int_platformsDY[] = {0,0,0,0,0,0,1,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
		0,0,0,0,0,0,0,0,0,0,0,0};
	//the enemies x coordinates
	static final int int_enemyX[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	//the enemy y coordinate
	static final int int_enemyY[] = {53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53,53};
	//the enemy speed
	static final int int_enemyDX[] = {1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2};
	//boolean for enabled
	static boolean Enabled = false;
	//get the background image
	static Image img_background = Main.img_background2;
	//the event queue
	static int int_event = 0;
	//the money array
	static int int_moneyX[] = {};
	//the money y array
	static int int_moneyY[] = {};
	
	//constructor which takes no arguments
	public lvl_2()
	{
	}
	
	//initialization
	public static void init()
	{
		
		//make the platforms have random speeds
		for(int i = 0; i < int_platformsX.length - 109; i++)
		{
			//if the platform number is even
			if(i % 2 < 1)
			{
				continue; //skip that platform
			}
			
			//decide whther to be positive or negative
			int dec = (int)Math.ceil(Math.random() * 2);
			
			if(dec == 1)//if positive
			{
				int dec2 = (int)Math.ceil(Math.random() * 2); //decide whther you will be affecting the x or the y
				
				if(dec2 == 1)
				{
					int_platformsDX[i + 7] = -1;
				}
				else if(dec2 == 2)
				{
					int_platformsDX[i + 7] = 1;
				}
			}
			else //if negative
			{
				int dec2 = (int)Math.ceil(Math.random() * 2); //decide whether to affect the x or the y
				
				if(dec2 == 1)
				{
					int_platformsDY[i + 7] = -1;
				}
				else if(dec2 == 2)
				{
					int_platformsDY[i + 7] = 1;
				}
			}
			
		}
		
		//set the platforms array length
		Main.playSurface.block = new Platform[int_platformsX.length];
		
		//set the enemy array length
		Main.playSurface.diceMan = new enemy_dice[int_enemyX.length];
		
		//set the coins array
		Main.playSurface.money = new coin[int_moneyX.length];
		
		//the level changer - one per level
		Main.playSurface.portal = new lvl_changer(102,20,3);
		
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
		Main.playSurface.String_grass_color ="yellow";
		Main.playSurface.dirt = true;
		Main.playSurface.steel = false;
		
		//start the timer
		Main.playSurface.bool_timer = true;
		
		//reset the time
		Main.playSurface.int_seconds = 0;
		Main.playSurface.int_minutes = 3;
		
		//tell which level is active
		Main.playSurface.int_lvl = 2;
		
		//tell the game surface to re-initialise
		Main.playSurface.init();
	}
	
	//the game loop: events happen here
	public static void gameloop()
	{
		//no level specific coding yet
	}
	
	//level specific painting
	public static void paint(Graphics g)
	{
		//no level specific painting yet
	}
}

