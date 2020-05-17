import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class BallWorld extends World {
	private Score score;
	private Lives lives;
	private Bricks bricks;
	private Stage stage;
	Scene titleScene;
	Scene gameOverScene;
	
	public BallWorld(Stage stage, Scene titleScene, Scene gameOverScene) {
		this.titleScene = titleScene;
		this.gameOverScene = gameOverScene;
		this.stage = stage;
		score = new Score();
    	score.setX(getWidth() + 10);
    	score.setY(20);

		lives = new Lives();
		lives.setX(getWidth() + 10);
		lives.setY(50);

		bricks = new Bricks();
		bricks.setNumBricks(2);
		//bricks.setNumBricks(132);

    	getChildren().add(score);
		getChildren().add(lives);

	}
	
    @Override
    public void act(long now) {

    }
    
    public Score getScore() {
    	return score;
    }

	public Lives getLives() {
		return lives;
	}

	public Bricks getBricks() {
		return bricks;
	}

	public boolean isGameOver() {
		if (lives.getLives() <= 0) {
			return true;
		}
		return false;
	}

	public boolean isLevelCleared() {
		if (bricks.getNumBricks() <= 0) {
			return true;
		}
		return false;
	}

	public void reset() {
		score.setScore(0);
		bricks.setNumBricks(2);
		lives.setLives(3);
		addBricks();
	}
	public void addBricks() {
		try {
			Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
			b.setX(3 * 60);
			b.setY(80);
			this.add(b);

			Brick b2 = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
			b2.setX(4 * 60);
			b2.setY(80);
			this.add(b2);
			/*
			//y<160
			for (int y = 80; y < 160; y += 30) {

				for (int x = 0; x < 11; x++) {
					Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
					b.setX(x * 60);
					b.setY(y);
					this.add(b);

					Brick b2 = new Brick(getClass().getClassLoader().getResource("resources/brick2.png").toString());
					b2.setX(x * 60 + 30);
					b2.setY(y);
					this.add(b2);
				}
				//x<11
				for (int x = 0; x < 11; x++) {

					Brick b2 = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
					b2.setX(x * 60 + 30);
					b2.setY(y + 15);
					this.add(b2);

					Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick2.png").toString());
					b.setX(x * 60);
					b.setY(y + 15);
					this.add(b);
				}
			}
			*/
		} catch (FileNotFoundException e) {
			System.out.println(e);
		}
	}
}
