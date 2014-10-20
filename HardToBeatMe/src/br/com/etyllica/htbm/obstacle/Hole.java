package br.com.etyllica.htbm.obstacle;

import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.htbm.beat.Heart;
import br.com.etyllica.htbm.player.PlayerAction;
import br.com.etyllica.layer.ImageLayer;

public class Hole extends Obstacle{
	
	private ImageLayer layer;
	
	public Hole() {
		super(1400,370);
		this.w = 100;
		this.h = 20;
		
		layer = new ImageLayer(x,y,"buraco.png");
		
		this.actionSucess = PlayerAction.JUMP;
		this.actionFail = PlayerAction.FALL;
		
		this.difficult = 200;
		
		int offsetX = x-100;
		
		/*this.heartSequence.add(new Heart(offsetX-400, HeartArea.LOWER_LEFT));
		this.heartSequence.add(new Heart(offsetX-320, HeartArea.UPPER_LEFT));
		
		this.heartSequence.add(new Heart(offsetX-160, HeartArea.UPPER_RIGHT));
		this.heartSequence.add(new Heart(offsetX-10, HeartArea.LOWER_RIGHT));*/
		
		this.heartSequence.add(new Heart(offsetX-400));
		this.heartSequence.add(new Heart(offsetX-320));
		
		this.heartSequence.add(new Heart(offsetX-160));
		this.heartSequence.add(new Heart(offsetX-30));
		
	}

	@Override
	public void move(int speed){
		this.x -= speed;
		this.layer.setOffsetX(-speed);
		
		for(Heart heart: heartSequence){
			heart.move(speed);
		}
	}
	
	@Override
	public void draw(Graphic g) {
		
		layer.draw(g);
		
	}
	
	
}

