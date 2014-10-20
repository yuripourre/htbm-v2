package br.com.etyllica.htbm.background;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Street implements Drawable {

	private int speed = 8;
	
	protected ImageLayer camada;
	
	private List<StreetTile> streetTiles = new ArrayList<StreetTile>();
	
	public Street(int x, int y){
		
		for(int i=0;i<7;i++){
			streetTiles.add(new StreetTile(160*i, y));
		}
		
	}
	
	@Override
	public void draw(Graphic g) {

		for(StreetTile tile: streetTiles){
			tile.draw(g);
		}
		
	}	
	
	public void move(){
		
		for(StreetTile tile: streetTiles){
			tile.setOffsetX(-speed);
			if(tile.getX()<-tile.getW()){
				tile.setX(790+tile.getW());
			}
		}
		
	}
	
	public int getSpeed(){
		return speed;
	}
	
}
