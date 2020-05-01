import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;

public class Brick extends Actor{
	
	public Brick(String img) {
		try {
            setImage(new Image(new FileInputStream(img)));
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
	}

	@Override
	public void act(long now) {
		
	}

}
