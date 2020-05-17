import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;


public class Score extends Text{
	private int score;
	
	public Score() {
		score = 0;
		setFont(new Font(20));
		setFill(Color.CORNFLOWERBLUE);
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
