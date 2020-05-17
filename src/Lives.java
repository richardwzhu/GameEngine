import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Lives extends Text{
	private int lives;

	public Lives() {
		lives = 3;
		setFont(new Font(20));
		setFill(Color.RED);
		Light.Distant light = new Light.Distant();
		light.setAzimuth(-135.0);
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		this.setEffect(lighting);
		updateDisplay();
	}
	
	public void updateDisplay() {
		String txt = "";
		for (int i = 0; i < lives; i++) {
			txt+="❤";

		}
		setText(txt);
	}
	
	public int getLives() {
		return lives;
	}
	
	public void setLives(int lives) {
		this.lives = lives;
		updateDisplay();
	}
}
