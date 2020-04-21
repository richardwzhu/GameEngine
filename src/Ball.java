import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Ball extends Actor {
    private double dx;
    private double dy;

    public Ball(String img, double dx, double dy) {
        try {
            setImage(new Image(new FileInputStream(img)));
        }
        catch (FileNotFoundException e) {
            System.out.println(e);
        }
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
        }
        move(dx, dy);
    }
}
