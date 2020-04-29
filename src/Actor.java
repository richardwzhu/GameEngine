import javafx.scene.Node;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public abstract class Actor extends ImageView {
    public Actor() {}

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public World getWorld() {
        return (World) getParent();
    }

    public double getWidth() {
        return getBoundsInParent().getWidth();
    }

    public double getHeight() {
        return getBoundsInParent().getHeight();
    }

    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
        ArrayList<A> list = new ArrayList<A>();
        for (Node actor : getWorld().getChildren()) {
            if (this.intersects(actor.getBoundsInLocal())) {
                if (cls.isInstance(actor)) {
                    if (actor != this) {
                        list.add(cls.cast(actor));
                    }
                }
            }
        }
        return list;
    }

    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls) {
        ArrayList<A> list = new ArrayList<A>();
        for (Node actor : getWorld().getChildren()) {
            if (this.intersects(actor.getBoundsInLocal())) {
                if (cls.isInstance(actor)) {
                    if (actor != this) {
                        return cls.cast(actor);
                    }
                }
            }
        }
        return null;
    }

    public abstract void act(long now);
}
