package br.com.etyllica.htbm.beat;

import java.awt.Color;
import java.util.Random;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.layer.ImageLayer;

public class Heart implements Drawable {

	private Performance performance = Performance.FAR;
	private HeartArea area;
	private HeartArea secondArea = null;

	private int combination = 0;

	protected Color color = Color.WHITE;

	private ImageLayer marcaNeutro = new ImageLayer("marca neutro.png");
	private ImageLayer marcaAcerto = new ImageLayer("marca acerto.png");
	private ImageLayer marcaAcertoSequencia = new ImageLayer("marca sequencia certa.png");
	private ImageLayer marcaErroSequencia = new ImageLayer("marca sequencia errada.png");

	private ImageLayer heartq = new ImageLayer("coracao q.png");
	private ImageLayer hearta = new ImageLayer("coracao a.png");
	private ImageLayer heartp = new ImageLayer("coracao p.png");
	private ImageLayer heartl = new ImageLayer("coracao l.png");

	private ImageLayer heartqa = new ImageLayer("coracao q a.png");
	private ImageLayer heartqp = new ImageLayer("coracao q p.png");
	private ImageLayer heartql = new ImageLayer("coracao q l.png");

	private ImageLayer heartpa = new ImageLayer("coracao p a.png");
	private ImageLayer heartpl = new ImageLayer("coracao p l.png");

	private ImageLayer heartal = new ImageLayer("coracao a l.png");

	private ImageLayer layer;
	private ImageLayer mark;

	private Random random = new Random();

	public Heart(int x){
		super();

		layer = new ImageLayer(x,70);
		mark = new ImageLayer(x-13,54,marcaNeutro.getPath());

		int numbArea = randomArea();
		this.area = convertArea(numbArea);
		this.secondArea = randomSecondArea(numbArea);

		if(HeartArea.UPPER_LEFT==area){
			combination = 1;
		}
		else if(HeartArea.UPPER_RIGHT==area){
			combination = 2;
		}
		else if(HeartArea.LOWER_LEFT==area){
			combination = 4;
		}
		else if(HeartArea.LOWER_RIGHT==area){
			combination = 8;
		}

		if(HeartArea.UPPER_LEFT==secondArea){
			combination += 1;
		}
		else if(HeartArea.UPPER_RIGHT==secondArea){
			combination += 2;
		}
		else if(HeartArea.LOWER_LEFT==secondArea){
			combination += 4;
		}
		else if(HeartArea.LOWER_RIGHT==secondArea){
			combination += 8;
		}

		switch (combination) {
		case 1:
			layer.cloneLayer(heartq);
			break;
		case 2:
			layer.cloneLayer(heartp);
			break;
		case 4:
			layer.cloneLayer(hearta);
			break;
		case 8:
			layer.cloneLayer(heartl);
			break;
		case 3: //1+2
			layer.cloneLayer(heartqp);
			break;
		case 5: //1+4
			layer.cloneLayer(heartqa);
			break;
		case 9: //1+8
			layer.cloneLayer(heartql);
			break;

		case 6: //2+4
			layer.cloneLayer(heartpa);
			break;
		case 10: //2+8
			layer.cloneLayer(heartpl);
			break;

		case 12: //4+8
			layer.cloneLayer(heartal);
			break;

		default:
			break;
		}

	}

	private HeartArea convertArea(int numbArea){

		switch(numbArea){
		default:
		case 0:
			return HeartArea.UPPER_LEFT;
		case 1:
			return HeartArea.UPPER_RIGHT;
		case 2:
			return HeartArea.LOWER_LEFT;
		case 3:
			return HeartArea.LOWER_RIGHT;
		}
	}

	private int randomArea(){

		return random.nextInt(4);

	}

	private HeartArea randomSecondArea(int area){

		int secondArea = random.nextInt(5);

		if(secondArea==area){
			return HeartArea.NONE;
		}

		switch(secondArea){
		case 0:
			return HeartArea.UPPER_LEFT;
		case 1:
			return HeartArea.UPPER_RIGHT;
		case 2:
			return HeartArea.LOWER_LEFT;
		case 3:
			return HeartArea.LOWER_RIGHT;
		default:
			return HeartArea.NONE;
		}

	}

	@Override
	public void draw(Graphic g) {

		mark.draw(g);
		layer.draw(g);		

	}

	public Performance getPerformance() {
		return performance;
	}

	public void setPerformance(Performance performance) {
		this.performance = performance;

		switch(performance){

		case FAR:
		case MISS:
			mark.setPath(marcaNeutro.getPath());
			break;
		case GOOD:
		case GREAT:
		case PERFECT:
			mark.setPath(marcaAcerto.getPath());
			break;
		case WRONG_SEQUENCE:
			mark.setPath(marcaErroSequencia.getPath());
			break;
		case RIGHT_SEQUENCE:
			mark.setPath(marcaAcertoSequencia.getPath());
			break;
		}
	}

	public HeartArea getArea(){
		return area;
	}

	public HeartArea getSecondArea(){
		return secondArea;
	}

	public void move(int speed){
		layer.setOffsetX(-speed);
		mark.setOffsetX(-speed);
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public ImageLayer getLayer(){
		return layer;
	}

}
