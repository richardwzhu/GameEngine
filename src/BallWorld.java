public class BallWorld extends World {
	private Score score;
	private Lives lives;
	private Bricks bricks;
	
	public BallWorld() {
		score = new Score();
    	score.setX(getWidth() + 10);
    	score.setY(20);

		lives = new Lives();
		lives.setX(getWidth() + 10);
		lives.setY(50);

		bricks = new Bricks();
		bricks.setNumBricks(132);

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
}
