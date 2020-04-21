import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("BallWorld");
        stage.setResizable(true);
        stage.setWidth(640);
        stage.setHeight(480);

        BorderPane border = new BorderPane();

        BallWorld ballWorld= new BallWorld();

        border.setCenter(ballWorld);

        Scene scene = new Scene(border);
        stage.setScene(scene);

        Ball ball = new Ball("ball.png", 2, 3);
        ball.setX(320);
        ball.setY(240);

        ballWorld.add(ball);
        ballWorld.start();
        stage.show();

    }

}
