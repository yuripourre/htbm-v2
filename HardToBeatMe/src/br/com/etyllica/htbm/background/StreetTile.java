package br.com.etyllica.htbm.background;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;

public class StreetTile extends Layer implements Drawable {

	public StreetTile(int x, int y) {
		super(x, y, 160, 320);
	}
	
	@Override
	public void draw(Graphic g) {
		
		int streetH = 160;
		g.setAlpha(60);
		
		g.setColor(Color.BLACK);
		g.fillRect(x,y,w,streetH);
		
		g.setColor(Color.BLUE);
		g.drawRect(x,y,w,streetH);
		
		g.setColor(Color.GRAY);
		g.fillRect(x,y+streetH,w,h-streetH);
		
		g.setColor(Color.BLUE);
		g.drawRect(x,y+streetH,w,h-streetH);
		
		g.setAlpha(100);		
	}

	
	
}
