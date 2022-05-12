package application.model;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.scene.control.Label;

/**
 *  This timer class is used to control users' operations the system
 * @author Uzoma Oseji - 1715756
 *
 */
public class ServerTimer
{
	
	private Timeline timer;
	private int minute = 5;
	private int second = 0;
	private String counter ="";
	
	/**
	 * Starts the timer event
	 * @param lbl
	 */
	public void start(Label lbl)
	{
		timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick(lbl)));
		timer.setCycleCount(Timeline.INDEFINITE);
		timer.play();
	}
	
	/**
	 * Start the thread for the timer tick
	 * @param lbl
	 */
	private void tick(Label lbl)
	{
		if (second > 0)
		{
			second--;
		}
		
		if (minute == 0 && second == 0) 
		{
			timer.stop();
			System.exit(0);
			
		}
		else if (second == 0)
		{
			minute--;
			second = 60;
		}
		
		if (second < 10)
		{
			counter = "0" + minute + ":0" + second;
		}
		else 
		{
			counter = "0" + minute + ":" + second;
		}
		
		lbl.setText(counter);
	}

}
