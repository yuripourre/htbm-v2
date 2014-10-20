package br.com.etyllica.htbm.background;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Background implements Drawable {

	private ImageLayer sun;
	
	private Parallax clouds;
	private Parallax predios;
	private Parallax grama;
	private Parallax street;
	
	
	public Background(){
		super();
		
		sun = new ImageLayer("sun.png");
		
		clouds = new Parallax("clouds.png");
		clouds.setSpeed(-1);
		
		predios = new Parallax("buildings.png");
		predios.setSpeed(-2);
		
		grama = new Parallax(230,"grass.png");
		grama.setSpeed(-3);
		
		street = new Parallax(190,"street.png");
		street.setSpeed(-8);
		
	}

	public void move(){
		clouds.move();
		predios.move();
		grama.move();
		street.move();
	}
	
	@Override
	public void draw(Graphic g) {

		sun.draw(g);
	
		clouds.draw(g);
		predios.draw(g);
		grama.draw(g);
		street.draw(g);
		
	}
	
}
