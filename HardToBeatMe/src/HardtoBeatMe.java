import sound.MultimediaLoader;
import sound.paulscode.libraries.LibraryJOAL;
import br.com.etyllica.EtyllicaFrame;
import br.com.etyllica.context.Application;
import br.com.etyllica.core.Configuration;
import br.com.etyllica.htbm.HeartMenu;

public class HardtoBeatMe extends EtyllicaFrame {
	
	private static final long serialVersionUID = 3003259067958762021L;

	public HardtoBeatMe(){
		super(800,600);
	}

	public static void main(String[] args){
		HardtoBeatMe game = new HardtoBeatMe();
		game.setTitle("Hard To Beat Me");
		game.init();
		
		game.addLoader(MultimediaLoader.getInstance());
		
		MultimediaLoader.getInstance().setSoundLibrary(LibraryJOAL.class);		
	}
	
	@Override
	public Application startApplication() {
		
		String path = HardtoBeatMe.class.getResource("").toString();
		path += "../";
		setPath(path);
		
		Configuration.getInstance().setTimerClick(true);
		
		return new HeartMenu(w, h);
	}

}


