import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Brick extends Actor{
	
	public Brick(String img) throws FileNotFoundException {
		setImage(new Image(img));
	}

	@Override
	public void act(long now) {
		
	}

}
