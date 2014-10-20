package br.com.etyllica.htbm;


import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.button.RoundCornerButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.htbm.load.HTBMLoading;
import br.com.etyllica.layer.ImageLayer;

public class HeartMenu extends HeartApplication{
	
	public HeartMenu(int w, int h) {
		super(w, h);
		
		loadApplication = new HTBMLoading(w, h);		
	}
	
	private ImageLayer background;
	
	private RoundCornerButton playButton;
	private RoundCornerButton instructionsButton;
	private RoundCornerButton creditsButton;
	
	@Override
	public void load() {
		
		background = new ImageLayer("splash.png");
		
		loadingInfo = "Loading Background...";
		loading = 20;
		
		
		for(int i=0;i<100;i++) {
			try {
				loading = i;
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		int offsetY = 300;
		
		playButton = new RoundCornerButton(200,offsetY,400,60);
		playButton.setLabel(new TextLabel("Play Game"));
		
		playButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "play"));
		add(playButton);
		
		
		instructionsButton = new RoundCornerButton(200,offsetY+80,400,60);
		instructionsButton.setLabel(new TextLabel("Instructions"));
		instructionsButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "instructions"));
		
		add(instructionsButton);
		
		creditsButton = new RoundCornerButton(200,offsetY+160,400,60);
		creditsButton.setLabel(new TextLabel("Credits"));
		creditsButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "credits"));
		add(creditsButton);
		
		loading = 100;
	}
	
	public void play() {
		nextApplication = new HeartGame(w, h);
		changeScene = true;
	}
	
	public void instructions() {
		nextApplication = new Instructions(w, h);
		changeScene = true;
	}
	
	public void credits() {
		nextApplication = new Credits(w, h);
		changeScene = true;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		if(changeScene){
			
			remove(playButton);
			remove(instructionsButton);
			remove(creditsButton);
			
			return GUIEvent.APPLICATION_CHANGED;
			
		}	
				
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		
		background.draw(g);		
		
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {

		return GUIEvent.NONE;
	}
	
}
