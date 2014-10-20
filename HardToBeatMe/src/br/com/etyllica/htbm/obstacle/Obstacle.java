package br.com.etyllica.htbm.obstacle;

import java.util.ArrayList;
import java.util.List;

import br.com.etyllica.core.Drawable;
import br.com.etyllica.htbm.beat.Heart;
import br.com.etyllica.htbm.player.PlayerAction;
import br.com.etyllica.layer.Layer;

public abstract class Obstacle extends Layer implements Drawable{

	protected int difficult = 0;
	
	protected List<Heart> heartSequence = new ArrayList<Heart>();
	
	protected PlayerAction actionSucess = PlayerAction.RUN;
	
	protected PlayerAction actionFail = PlayerAction.RUN;
	
	protected boolean passed = false;
	protected boolean sequenceEnds = false;
	
	public Obstacle(int x, int y){
		super(x, y);
	}	
	
	public void move(int speed){
		this.x -= speed;
		
		for(Heart heart: heartSequence){
			heart.move(speed);
		}
	}
	
	public List<Heart> getHeartSequence(){
		return heartSequence;
	}
	
	public int getDifficult(){
		return difficult;
	}

	public PlayerAction getActionSucess() {
		return actionSucess;
	}
	
	public PlayerAction getActionFail() {
		return actionFail;
	}

	public boolean isPassed() {
		return passed;
	}

	public void setPassed(boolean passed) {
		this.passed = passed;
	}

	public boolean isSequenceEnds() {
		return sequenceEnds;
	}

	public void setSequenceEnds(boolean sequenceEnds) {
		this.sequenceEnds = sequenceEnds;
	}
	
	
}
