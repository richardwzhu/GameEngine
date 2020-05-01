import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Score extends Text{
	private int score;
	
	public Score() {
		score = 0;
		setFont(new Font(20));
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
