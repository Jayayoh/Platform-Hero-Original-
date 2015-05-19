//import the necessary libraries and codes
import java.awt.Image;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

class coin 
{
	//variables
	//the x location
	int int_x;
	//the y variables
	int int_y;
	//the image of the Coin
	Image img_coin = Main.img_coin;
	//the width of the coin
	int int_width = 20;
	// the height of the coin
	int int_height = 20;
	//the sound file
	File sound_file = new File("sounds/Coin.wav");
	//the sound clip
	Clip clip;
	
	//create the coin
	public coin(int x, int y)
	{
		int_x = x * 50;//set the x coordinate 
		int_y = y * 50;//set the y coordinate
		
		//set the sounds
		try
		{
			//set up the coin sounds
			AudioInputStream audioin = AudioSystem.getAudioInputStream(sound_file);
			clip = AudioSystem.getClip();
			clip.open(audioin);
			
			//reduce volume of coins sounds
			FloatControl cool = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
			cool.setValue(-8.0f);
		}
		catch(Exception e)
		{
			//catch errors
		}
	}
}
