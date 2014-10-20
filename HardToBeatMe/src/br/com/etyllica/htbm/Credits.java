package br.com.etyllica.htbm;

import java.awt.Color;
import java.awt.Font;

import br.com.etyllica.core.event.Action;
import br.com.etyllica.core.event.GUIEvent;
import br.com.etyllica.core.event.KeyEvent;
import br.com.etyllica.core.event.PointerEvent;
import br.com.etyllica.core.graphics.Graphic;
import br.com.etyllica.gui.button.RoundButton;
import br.com.etyllica.gui.label.TextLabel;
import br.com.etyllica.layer.ImageLayer;

public class Credits extends HeartApplication {

	private RoundButton backButton;
	
	private ImageLayer background;
	
	public Credits(int w, int h) {
		super(w, h);
	}

	@Override
	public void load() {
		
		loadingInfo = "Loading Background...";
		background = new ImageLayer("splash.png");
		
		backButton = new RoundButton(40,500,80,80);
		backButton.setLabel(new TextLabel("Back"));
		
		backButton.addAction(GUIEvent.MOUSE_LEFT_BUTTON_DOWN, new Action(this, "back"));
		add(backButton);
		
		loading = 100;
	}

	public void back() {
		nextApplication = new HeartMenu(w, h);
		changeScene = true;
	}
	
	@Override
	public GUIEvent updateMouse(PointerEvent event) {
		
		if(changeScene){
			return GUIEvent.APPLICATION_CHANGED;
		}
		
		return GUIEvent.NONE;
	}

	@Override
	public void draw(Graphic g) {
		
		background.draw(g);
		
		g.setColor(Color.BLACK);
		g.setAlpha(40);
		g.fillRect(20, 0, w-40, h);
		g.setAlpha(100);
		
		g.setFont(g.getFont().deriveFont(32f).deriveFont(Font.BOLD));
		
		g.setColor(Color.WHITE);
		
		g.drawStringShadowX(50, "Credits");
		
		g.drawStringShadowX(250, "Pedro Viriato - Art");
		g.drawStringShadowX(300, "Rafael Bastos - Sounds");
		g.drawStringShadowX(350, "Yuri Pourre - Programming");
		
			
	}

	@Override
	public GUIEvent updateKeyboard(KeyEvent event) {
		// TODO Auto-generated method stub
		return GUIEvent.NONE;
	}
	
}
