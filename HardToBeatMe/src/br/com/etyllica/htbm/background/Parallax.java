package br.com.etyllica.htbm.background;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Parallax implements Drawable {

	private ImageLayer camada1;
	private ImageLayer camada2;
	
	private int speed = -1;
	
	private int backgroundW = 800;
	
	public Parallax(String path) {
		this(0, path);
	}
	
	public Parallax(int y, String path) {
		super();
		
		camada1 = new ImageLayer(0,y,path);
		camada2 = new ImageLayer(800,y,path);
	}
	
	public void move(){
				
		if(camada1.getX()+backgroundW<0){
			camada1.setX(camada2.getX()+backgroundW+speed);
		}
		else if(camada2.getX()+backgroundW<0){
			camada2.setX(camada1.getX()+backgroundW+speed);
		}
		
		camada1.setOffsetX(speed);
		camada2.setOffsetX(speed);
		
	}

	@Override
	public void draw(Graphic g) {
		camada1.draw(g);
		camada2.draw(g);
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
}
