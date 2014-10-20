package br.com.etyllica.htbm.background;

import java.awt.Color;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.Layer;

public class BackgroundTile extends Layer implements Drawable {

	public BackgroundTile() {
		super();
	}
	
	@Override
	public void draw(Graphic g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, 800, 200);
	}
	
}
