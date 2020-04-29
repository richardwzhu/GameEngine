import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor{
	
	public Paddle(String img) {
		try {
            setImage(new Image(new FileInputStream(img)));
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
	}

	@Override
	public void act(long now) {
		if(getWorld().isKeyDown(KeyCode.LEFT)) {
			move(-5, 0);
		}
		if(getWorld().isKeyDown(KeyCode.RIGHT)) {
			move(5, 0);		
		}
	}

}