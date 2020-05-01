public class BallWorld extends World {
	private Score score;
	
	public BallWorld() {
		score = new Score();
    	score.setX(getWidth() + 10);
    	score.setY(20);
    	getChildren().add(score);
	}
	
    @Override
    public void act(long now) {
  
    }
    
    public Score getScore() {
    	return score;
    }
}
