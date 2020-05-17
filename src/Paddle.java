import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public class Paddle extends Actor{

	public enum State{
		LEFT, RIGHT, STILL
	}
	State state;
	
	public Paddle(String img) {
		state = State.STILL;
            setImage(new Image(img));
	}

	@Override
	public void act(long now) {
		if(getWorld().isKeyDown(KeyCode.LEFT)) {
		    if (this.getX()>0) {
                move(-5, 0);
            }
			this.state = State.LEFT;

		}
		if(getWorld().isKeyDown(KeyCode.RIGHT)) {
            if (this.getX()+this.getWidth()<655) {
                move(5, 0);
            }
			this.state = State.RIGHT;

		}
		else {
			this.state = State.STILL;
		}
	}

	public State getState() {
		return state;
	}
	public void setState(State state) {
	    this.state = state;
    }

}
