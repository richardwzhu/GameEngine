import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public abstract class World extends Pane {
    private AnimationTimer timer;

    World() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                for (Node actor: getChildren()) {
                    ((Actor)actor).act(now);
                }
            }
        };
    }
    public void start() {
        timer.start();
    }
    public void stop() {
        timer.stop();
    }
    public void add(Actor actor) {
        getChildren().add(actor);
    }
    public void remove(Actor actor){
        getChildren().remove(actor);
    }

    public <A extends Actor> List<A> getObjects(Class<A> cls){
        ArrayList<A> list =  new ArrayList<A>();
        for (Node actor: getChildren()) {
            if (cls.isInstance(actor)) {
                list.add(cls.cast(actor));
            }
        }
        return list;
    }
    public abstract void act(long now);
}
