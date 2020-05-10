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
		updateDisplay();
	}
	
	public void updateDisplay() {
		String txt = "";
		for (int i = 0; i < lives; i++) {
			txt+="\uD83D\uDC93";
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
