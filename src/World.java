import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.List;

public abstract class World extends Pane {
    private AnimationTimer timer;
    private HashSet<KeyCode> codes;

    World() {
        codes = new HashSet<KeyCode>();
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                act(now);
                try {
                    for (Node actor: getChildren()) {
                        if(actor instanceof Actor) {
                            ((Actor)actor).act(now);
                        }
                    }
                } catch (ConcurrentModificationException e) {

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
    
    public void addKeyCode(KeyCode code) {
    	codes.add(code);
    }
    
    public void removeKeyCode(KeyCode code) {
    	codes.remove(code);
    }
    
    public boolean isKeyDown(KeyCode code) {
    	return codes.contains(code);
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
