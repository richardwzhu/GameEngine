import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.paint.Color;


public class Score extends Text{
	private int score;
	
	public Score() {
		score = 0;
		setFont(Font.font(null, FontWeight.BOLD, 20));
		setFill(Color.CORNFLOWERBLUE);
		Light.Distant light = new Light.Distant();
		light.setAzimuth(-135.0);
		Lighting lighting = new Lighting();
		lighting.setLight(light);
		lighting.setSurfaceScale(5.0);
		setEffect(lighting);
		updateDisplay();
	}
	
	public void updateDisplay() {
		setText("Score: " + score);
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
		updateDisplay();
	}
}
