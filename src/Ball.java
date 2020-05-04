import javafx.scene.image.Image;

import java.awt.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Ball extends Actor {
    private double dx;
    private double dy;

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

        if (getX()+getWidth() > getWorld().getWidth()) {
            dx *= -1;
        }
        if (getY()+getHeight() > getWorld().getHeight()) {
            dy *= -1;
            Score cur = ((BallWorld)getWorld()).getScore();
        	cur.setScore(cur.getScore() - 1000);
        }
        move(dx, dy);
        
        if(getIntersectingObjects(Paddle.class).size() > 0) {
        	dy *= -1;
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
        }
    }
}
