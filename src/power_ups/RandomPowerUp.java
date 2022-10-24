package power_ups;

import game.EventHandler;
import game.GameWindow;

public class RandomPowerUp {
	
	public static int randomPowerUp() {
		
		int power_up = (int)(Math.random()*4);
		
		return power_up;
		
	}
	
	public static void minusTime() {
		
		if (GameWindow.secunds < PowerUpsEvents.minusTime.getTimeInSec()) {
			
			if (GameWindow.minutes < 1) {
				
				if (GameWindow.hours < 1) {
					
					GameWindow.hours = 0;
					
				}
				else {
					
					GameWindow.hours--;
					
				}
				
				GameWindow.minutes = 0;
				
			}
			else {
				
				GameWindow.minutes--;
				
			}
			
			GameWindow.secunds = 0;
			
		}
		else {
			
			GameWindow.secunds -= PowerUpsEvents.minusTime.getTimeInSec();
			
			if (GameWindow.timeInSec < PowerUpsEvents.minusTime.getTimeInSec()) {
				
				GameWindow.timeInSec = 0;
				
			}
			else {
				
				GameWindow.timeInSec -= PowerUpsEvents.minusTime.getTimeInSec();
				
			}
			
		}
		
	}

	public static void placeNuke() {
		
		//prepare local variables
		double stNukes = Math.round((EventHandler.stMines * 10) / 100);
		int stLeftNukes = (int)stNukes;
		
		int xCoord = 0;
		int yCoord = 0;
		
		//repeat all nukes are placed
		while (stLeftNukes != 0) {
			
			do {
				
				//generate random coords
				xCoord = (int)(Math.random() * EventHandler.rows + 0);
				yCoord = (int)(Math.random() * EventHandler.columns + 0);
				
			} while (EventHandler.mines[xCoord][yCoord] != 9 || EventHandler.nukes[xCoord][yCoord]);
			
			EventHandler.nukes[xCoord][yCoord] = true;
			stLeftNukes--;
			
		}
		
	}
	
	public static void setNoXray() {
		
		double stNoXray = Math.round((EventHandler.rows*EventHandler.columns) / 100 * 5);
		int stLeftNoXray = (int)stNoXray;
		
		int xCoord = 0;
		int yCoord = 0;
		
		while (stLeftNoXray != 0) {
			
			do {
				
				xCoord = (int)(Math.random() * EventHandler.rows + 0);
				yCoord = (int)(Math.random() * EventHandler.columns + 0);
				
			} while (EventHandler.noXray[xCoord][yCoord]);
			
			EventHandler.noXray[xCoord][yCoord] = true;
			stLeftNoXray--;
			
		}
		
	}
	
}
