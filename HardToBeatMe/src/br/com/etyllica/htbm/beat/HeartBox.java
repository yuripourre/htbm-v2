package br.com.etyllica.htbm.beat;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;

public class HeartBox extends Layer implements Drawable {

	private int points = 0;

	private boolean activateUpperLeft = false;
	private boolean keyPressUpperLeft = false;

	private boolean activateLowerLeft = false;
	private boolean keyPressLowerLeft = false;

	private boolean activateUpperRight = false;
	private boolean keyPressUpperRight = false;

	private boolean activateLowerRight = false;
	private boolean keyPressLowerRight = false;

	public HeartBox(int x, int y){
		super(x,y,110,110);
	}

	@Override
	public void draw(Graphic g) {

		/*g.setColor(Color.WHITE);
		g.drawRect(x, y, w, h);

		g.drawRect(x, y, w/2, h/2);
		g.drawRect(x, y+w/2, w/2, h/2);
		g.drawRect(x+w/2, y, w/2, h/2);
		g.drawRect(x+w/2, y+w/2, w/2, h/2);

		//Left Side
		if(keyPressUpperLeft){
			g.fillRect(x, y, w/2, h/2);
		}
		if(keyPressLowerLeft){
			g.fillRect(x, y+w/2, w/2, h/2);
		}
		//Right Side
		if(keyPressUpperRight){
			g.fillRect(x+w/2, y, w/2, h/2);
		}
		if(keyPressLowerRight){
			g.fillRect(x+w/2, y+w/2, w/2, h/2);
		}*/

	}

	public void pressUpperLeft(){

		keyPressUpperLeft = true;
		activateUpperLeft = true;

	}

	public void unpressUpperLeft(){

		keyPressUpperLeft = false;
		activateUpperLeft = false;
	}

	public void pressUpperRight(){

		keyPressUpperRight = true;
		activateUpperRight = true;

	}

	public void unpressUpperRight(){

		keyPressUpperRight = false;
		activateUpperRight = false;

	}

	public void pressLowerRight(){

		keyPressLowerRight = true;
		activateLowerRight = true;

	}

	public void unpressLowerRight(){

		keyPressLowerRight = false;
		activateLowerRight = false;

	}

	public void pressLowerLeft(){

		keyPressLowerLeft = true;
		activateLowerLeft = true;

	}

	public void unpressLowerLeft(){

		keyPressLowerLeft = false;
		activateLowerLeft = false;

	}

	//TODO Rename to unpress
	public void deactivateButtons(){
		activateUpperLeft = false;
		activateUpperRight = false;
		activateLowerLeft = false;
		activateLowerRight = false;
	}

	public Performance colideHeart(Heart heart){

		int hx = heart.getLayer().getX();
		int hw = 110;

		int dif = 0;

		int missOffset = 30;
		
		if(hx+hw-missOffset<x){

			heart.setPerformance(Performance.MISS);

			//deactivateButtons();

			return Performance.MISS;
			
		}else{

			if(colideArea(heart.getArea(),heart.getSecondArea())){

				if(hx>x){
					dif = hx-x;
				}else{
					dif = x-hx;
				}

				if(dif<=hw*0.15){

					heart.setPerformance(Performance.PERFECT);

					points+=100;

					deactivateButtons();

					return Performance.PERFECT;
				}
				else if(dif<=hw*0.30){

					heart.setPerformance(Performance.GREAT);

					points+=80;

					deactivateButtons();

					return Performance.GREAT;
				}
				else if(dif<=hw*0.60){

					heart.setPerformance(Performance.GOOD);

					points+=60;

					deactivateButtons();

					return Performance.GOOD;
				}

			}
		}

		return Performance.FAR;

	}

	private boolean colideArea(HeartArea area, HeartArea secondArea){

		boolean areaCorrect = false;

		boolean secondAreaCorrect = false;

		if(HeartArea.UPPER_LEFT==area){
			areaCorrect = keyPressUpperLeft&&activateUpperLeft;
		}else if(HeartArea.UPPER_RIGHT==area){
			areaCorrect = keyPressUpperRight&&activateUpperRight;
		}else if(HeartArea.LOWER_LEFT==area){
			areaCorrect = keyPressLowerLeft&&activateLowerLeft;
		}else if(HeartArea.LOWER_RIGHT==area){
			areaCorrect = keyPressLowerRight&&activateLowerRight;
		}

		if(HeartArea.UPPER_LEFT==secondArea){
			secondAreaCorrect = keyPressUpperLeft&&activateUpperLeft;
		}else if(HeartArea.LOWER_LEFT==secondArea){
			secondAreaCorrect = keyPressLowerLeft&&activateLowerLeft;
		}else if(HeartArea.UPPER_RIGHT==secondArea){
			secondAreaCorrect = keyPressUpperRight&&activateUpperRight;
		}else if(HeartArea.LOWER_RIGHT==secondArea){
			secondAreaCorrect = keyPressLowerRight&&activateLowerRight;
		}else{
			secondAreaCorrect = true;
		}

		return (areaCorrect&&secondAreaCorrect);
	}

	public void setPoints(int points){
		this.points = points;
	}
	
	public int getPoints(){
		return points;
	}

}
