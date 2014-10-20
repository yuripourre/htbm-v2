package br.com.etyllica.htbm;

import java.awt.Color;
import java.util.List;
import java.util.Random;

import sound.model.Music;
import sound.model.Sound;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.effects.Effect;
import br.com.etyllica.htbm.background.Background;
import br.com.etyllica.htbm.beat.Heart;
import br.com.etyllica.htbm.beat.HeartBox;
import br.com.etyllica.htbm.beat.Performance;
import br.com.etyllica.htbm.obstacle.Car;
import br.com.etyllica.htbm.obstacle.Hole;
import br.com.etyllica.htbm.obstacle.Obstacle;
import br.com.etyllica.htbm.player.Player;
import br.com.etyllica.htbm.player.PlayerAction;
import br.com.etyllica.layer.ImageLayer;

public class HeartGame extends Application {
	
	private Sound beat1;
	private Sound beat2;
	private Sound wrong;
	private Music music = new Music("Rafael Farol - Heart Rush.ogg");

	private Effect performPerfect;
	private Effect performGreat;
	private Effect performGood;
	private Effect performMiss;
	private Effect stars;
	
	private ImageLayer markq;
	private ImageLayer marka;
	private ImageLayer markp;
	private ImageLayer markl;
	
	private Player player;

	private int px = 160;
	private int py = 290;
	
	private int jumpY = py-70;
	private int jumpSpeed = 5;
	
	private int superJumpY = py-140;
	private int superJumpSpeed = 13;

	//UpperBar
	private HeartBox box;

	private Background background;
	
	private final int gameSpeed = 8;
	
	private int nextHeart = 0;

	private Obstacle obstacle;


	private int allPoints = 0;


	private ImageLayer bar;

	private ImageLayer marker;
	

	public HeartGame(int w, int h){
		super(w,h);
	}

	@Override
	public void load() {

		loadingInfo = "Loading Player...";
		player = new Player(px, py);
		
		loading = 5;
		loadingInfo = "Loading Background...";
		background = new Background();
		

		loadingInfo = "Loading Bar...";
		bar = new ImageLayer(0,60,"bar.png");
		
		marker = new ImageLayer(130,53,"marcador.png");
		
		markq = new ImageLayer(130,53,"mark/mark q.png");
		markq.setVisible(false);
		
		marka = new ImageLayer(130,53,"mark/mark a.png");
		marka.setVisible(false);
		
		markp = new ImageLayer(130,53,"mark/mark p.png");
		markp.setVisible(false);
		
		markl = new ImageLayer(130,53,"mark/mark l.png");
		markl.setVisible(false);

		
		loadingInfo = "Loading Interface...";
		loading = 10;
		box = new HeartBox(130,50);

		
		loading = 20;
		loadingInfo = "Loading Effects...";
		
		int effectsX = 110;
		int effectsY = 90;
		
		performPerfect = new Effect(effectsX, effectsY, 240, 65);
		performPerfect.setPath("fx/perfect.png");
		performPerfect.setFrames(7);
		//performPerfect.anima();
		performPerfect.setVisible(false);
		
		performGreat = new Effect(effectsX, effectsY, 240, 65);
		performGreat.setPath("fx/great.png");
		performGreat.setFrames(7);
		//performGreat.anima();
		performGreat.setVisible(false);
		
		performGood = new Effect(effectsX, effectsY, 240, 65);
		performGood.setPath("fx/good.png");
		performGood.setFrames(7);
		//performGood.anima();
		performGood.setVisible(false);

		performMiss = new Effect(effectsX, effectsY, 240, 65);
		performMiss.setPath("fx/miss.png");
		performMiss.setFrames(7);
		//performMiss.anima();
		performMiss.setVisible(false);
		
		stars = new Effect(160, 250, 100, 60);
		stars.setPath("fx/stars.png");
		stars.setFrames(7);
		stars.setVisible(false);
		
		loadingInfo = "Loading Obstacles...";
		loading = 50;

		obstacle = generateNewObstacle();
		

		loading = 80;
		loadingInfo = "Loading Sounds...";

		//beat1 = new Sound("beat1.wav");
		//beat2 = new Sound("beat2.wav");
		wrong = new Sound("wrong.wav");

		loadingInfo = "Loading Music...";

		music.play();

		updateAtFixedRate(35);

		loading = 100;
	}

	@Override
	public void timeUpdate(long now){
		
		moveAll();
				
		performPerfect.nextFrame();
		performGreat.nextFrame();
		performGood.nextFrame();
		performMiss.nextFrame();
		
		stars.nextFrame();
		
		player.getCamada().nextFrame();
		
		List<Heart> hearts = obstacle.getHeartSequence();

		if(!obstacle.isSequenceEnds()){

			if(nextHeart<hearts.size()){

				Heart heart = hearts.get(nextHeart);

				Performance performance = box.colideHeart(heart);

				if(performance!=Performance.FAR){

					nextHeart++;
					
					/*performPerfect.setVisible(false);
					performGreat.setVisible(false);
					performGood.setVisible(false);
					performMiss.setVisible(false);*/
					
					if(Performance.PERFECT==performance){
						//performPerfect.setVisible(true);
						performPerfect.animaOnce();
					}
					else if(Performance.GREAT==performance){
						//performGreat.setVisible(true);
						performGreat.animaOnce();
					}
					else if(Performance.GOOD==performance){
						//performGood.setVisible(true);
						performGood.animaOnce();
					}
					else if(Performance.MISS==performance){
						//performMiss.setVisible(true);
						performMiss.animaOnce();
					}

				}

			}else{

				obstacle.setSequenceEnds(true);

				//	End of the sequence
				int actualPoints = box.getPoints();
				box.setPoints(0);
				nextHeart = 0;

				if(actualPoints>=obstacle.getDifficult()){

					allPoints += actualPoints;
					
					for(Heart heart: hearts){
						heart.setPerformance(Performance.RIGHT_SEQUENCE);
					}

					//TODO Act as animation	
					player.setAction(obstacle.getActionSucess());

				}else{

					stars.animaOnce();
					
					for(Heart heart: hearts){
						heart.setColor(Color.RED);
						heart.setPerformance(Performance.WRONG_SEQUENCE);
					}
					
					wrong.play();

					player.setAction(obstacle.getActionFail());
				}
			}

		}else{
			
			if(!obstacle.isPassed()){

				actPlayer();

			}else{
				
				//Wait until obstacle out of scene
				
				if(obstacle.getX()+obstacle.getW()<0){
					
					obstacle = generateNewObstacle();
				}
				
			}
			
		}


	}

	private void actPlayer(){

		//move player
		if(PlayerAction.JUMP==player.getAction()){

			if(player.getCamada().getY()>jumpY){
				player.getCamada().setOffsetY(-jumpSpeed);
			}else{
				player.setAction(PlayerAction.JUMP_DOWN);
			}

		}else if(PlayerAction.JUMP_DOWN==player.getAction()){

			if(player.getCamada().getY()<py){

				player.getCamada().setOffsetY(+jumpSpeed);

			}else{

				obstacle.setPassed(true);

				player.setAction(PlayerAction.RUN);
			}

		}
		
		if(PlayerAction.SUPER_JUMP==player.getAction()){

			if(player.getCamada().getY()>superJumpY){
				player.getCamada().setOffsetY(-superJumpSpeed);
			}else{
				player.setAction(PlayerAction.JUMP_DOWN);
			}

		}else if(PlayerAction.SUPER_JUMP_DOWN==player.getAction()){

			if(player.getCamada().getY()<py){

				player.getCamada().setOffsetY(+superJumpSpeed);

			}else{
				
				obstacle.setPassed(true);

				player.setAction(PlayerAction.RUN);
			}

		}
		
		
		if(PlayerAction.FALL==player.getAction()){
			
			obstacle.setPassed(true);
			player.setAction(PlayerAction.RUN);
		}
	}

	private Random random = new Random();
	
	private Obstacle generateNewObstacle(){
		
		switch (random.nextInt(2)) {
		case 1:
			return new Car();

		default:
			return new Hole();
		}
		
	}

	private void moveAll(){

		background.move();

		obstacle.move(gameSpeed);

	}


	@Override
	public GUIEvent updateKeyboard(KeyEvent event){

		if(event.isKeyDown(KeyEvent.TSK_Q)){
			box.pressUpperLeft();
			markq.setVisible(true);

			//beat1.play();

		}else if(event.isKeyUp(KeyEvent.TSK_Q)){
			
			box.unpressUpperLeft();
			markq.setVisible(false);
		}

		if(event.isKeyDown(KeyEvent.TSK_A)){
			box.pressLowerLeft();
			marka.setVisible(true);
			
			//beat2.play();
		}else if(event.isKeyUp(KeyEvent.TSK_A)){
			box.unpressLowerLeft();
			marka.setVisible(false);
		}

		if(event.isKeyDown(KeyEvent.TSK_P)){
			box.pressUpperRight();
			markp.setVisible(true);
			
			//beat1.play();

		}else if(event.isKeyUp(KeyEvent.TSK_P)){
			box.unpressUpperRight();
			markp.setVisible(false);
		}

		if(event.isKeyDown(KeyEvent.TSK_L)){
			box.pressLowerRight();
			markl.setVisible(true);
			
			//beat2.play();

		}else if(event.isKeyUp(KeyEvent.TSK_L)){
			box.unpressLowerRight();
			markl.setVisible(false);
		}


		return GUIEvent.NONE;

	}

	@Override
	public void draw(Graphic g) {

		background.draw(g);
		
		bar.draw(g);

		box.draw(g);

		obstacle.draw(g);

		for(Heart heart: obstacle.getHeartSequence()){
			heart.draw(g);
		}

		player.draw(g);

		marker.draw(g);
		markq.draw(g);
		marka.draw(g);
		markp.draw(g);
		markl.draw(g);
		
		
		performPerfect.draw(g);
		performGreat.draw(g);
		performGood.draw(g);
		performMiss.draw(g);
		stars.draw(g);
		
		g.setFont(g.getFont().deriveFont(22f));
		g.setColor(Color.WHITE);
		g.drawShadow(500,65,"POINTS:");
		g.drawShadow(600,65,getPoints());

	}
	
	private String getPoints(){
		
		StringBuffer buffer = new StringBuffer(7);
		
		int numb = 1000000;
		
		while(numb>1){
		
			if(allPoints<numb){
				buffer.append("0");	
			}
			
			numb/=10;
		}
		
		buffer.append(allPoints);
		
		return buffer.toString();
	}

	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		return GUIEvent.NONE;
	}

}

