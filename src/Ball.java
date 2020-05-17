import javafx.scene.image.Image;

import javax.management.DynamicMBean;
import javax.swing.plaf.nimbus.State;
import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ball extends Actor {
    private double dx;
    private double dy;
    private boolean hitBall;

    public Ball(String img, double dx, double dy) throws FileNotFoundException {
        setImage(new Image(img));
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void act(long now) {
        if (getX() < 0) {
            dx *= -1;
        }
        if (getY() < 0) {
            dy *= -1;
        }

        if (getX()+getWidth() > 665) {
            dx *= -1;
        }
        if (getY()+getHeight() > 455) {
            dy *= -1;
            Score cur = ((BallWorld)getWorld()).getScore();
        	cur.setScore(cur.getScore() - 1000);

            Lives life = ((BallWorld)getWorld()).getLives();
            life.setLives(life.getLives() - 1);

        }
        move(dx, dy);
        if(getIntersectingObjects(Paddle.class).size() == 0) {
            hitBall = false;
        }
        if(getIntersectingObjects(Paddle.class).size() > 0 && !hitBall) {
            hitBall = true;

            Paddle paddle = getIntersectingObjects(Paddle.class).get(0);

            if (paddle.getState() == Paddle.State.STILL && paddle.getX() < this.getX() + this.getWidth() / 2 && paddle.getX() + paddle.getWidth() > this.getX() + this.getWidth() / 2) {
                dy *= -1;
            } else if (paddle.getX() + paddle.getWidth() / 3 < this.getX() + this.getWidth() / 2 && paddle.getX() + paddle.getWidth() * 2 / 3 > this.getX() + this.getWidth() / 2) {
                dy *= -1;
            } else if (paddle.getState() == Paddle.State.LEFT && paddle.getX() < this.getX() + this.getWidth() / 2 && paddle.getX() + paddle.getWidth() / 3 > this.getX() + this.getWidth() / 2) {
                dx = -2;
                dy*=-1;
            } else if (paddle.getState() == Paddle.State.RIGHT && paddle.getX() + paddle.getWidth() > this.getX() + this.getWidth() / 2 && paddle.getX() + paddle.getWidth() * 2 / 3 < this.getX() + this.getWidth() / 2) {
                dx = 2;
                dy*=-1;
            } else if (paddle.getX()>this.getX()+this.getWidth()/2) {
                dx = -3;
                dy*=-1;
            } else if (paddle.getX()+paddle.getWidth()<this.getX()+this.getWidth()/2) {
                dx = 3;
                dy*=-1;
            }
        }
        
        if(getIntersectingObjects(Brick.class).size() > 0) {
        	ArrayList<Brick> list = (ArrayList<Brick>) getIntersectingObjects(Brick.class);
        	double xBrick = list.get(0).getX();
        	double yBrick = list.get(0).getY();
        	Score cur = ((BallWorld)getWorld()).getScore();
        	cur.setScore(cur.getScore() + 100);
        	if(getX() > xBrick && getX() < xBrick + list.get(0).getWidth()) {
        		dy *= -1;
        	}else if(getY() > yBrick && getY() < yBrick + list.get(0).getHeight()) {
        		dx *= -1;
        	}else {
        		dy *= -1;
        		dx *= -1;
        	}
        	getWorld().remove(list.get(0));

            Bricks bricks = ((BallWorld)getWorld()).getBricks();
            bricks.setNumBricks(bricks.getNumBricks() - 1);
        }
    }
}
