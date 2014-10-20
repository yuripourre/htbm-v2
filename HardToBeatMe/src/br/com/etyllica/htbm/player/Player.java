package br.com.etyllica.htbm.player;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.AnimatedLayer;

public class Player implements Drawable {

	private PlayerAction action = PlayerAction.RUN;
	
	private AnimatedLayer layer;
	
	public Player(int x, int y){
		super();
		
		layer = new AnimatedLayer(x,y,114,144,"player.png");
		layer.setAnimateHorizontally(true);
		layer.setFrames(12);
		layer.animate();
		
	}
	
	@Override
	public void draw(Graphic g) {
		
		Color color = Color.BLACK;
		
		switch(action){
		case RUN:
			color = Color.RED;
			break;
		case JUMP:
			color = Color.YELLOW;
			break;
		case FALL:
			color = Color.GREEN;
			break;
		case WALK:
			color = Color.BLUE;
			break;
		}
		
		g.setColor(color);
		
		layer.draw(g);
		//camada.draw(g);
		//g.drawRect(camada.getX(),camada.getY(),camada.getW(),camada.getH());
		
	}
	
	public AnimatedLayer getCamada(){
		return layer;
	}

	public PlayerAction getAction() {
		return action;
	}

	public void setAction(PlayerAction action) {
		this.action = action;
	}	
	
}
