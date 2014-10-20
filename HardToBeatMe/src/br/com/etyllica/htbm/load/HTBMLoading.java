package br.com.etyllica.htbm.load;

import br.com.etyllica.context.load.DefaultLoadApplication;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class HTBMLoading extends DefaultLoadApplication {

	private ImageLayer splash;
	private ImageLayer pbLayer;
	
	private static final int OFFSET_Y = 312;
	private int height;
	
	public HTBMLoading(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		splash = new ImageLayer("splash.png");
		pbLayer = new ImageLayer(299, OFFSET_Y, "pb.png");
		height = pbLayer.getH();
	}
	
	@Override
	public void draw(Graphic g) {

		splash.draw(g);
		
		pbLayer.draw(g);
		
		g.drawStringShadowX(260, phrase);
		g.drawStringShadowX(200, loading+"%");
	}

	@Override
	public void onChangeText(String phrase) {
		this.phrase = phrase;
	}

	@Override
	public void onChangeLoad(float load) {
		this.loading = load;
				
		float factor = (load/100);
		
		int offset = (int)(height*factor);
		
		pbLayer.setY(OFFSET_Y+offset);
		pbLayer.setH(height-offset);
		pbLayer.setYImage(offset);		
	}
	
}
