import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Game extends Application {
	private BallWorld ballWorld;
	private Ball ball;
	private Paddle paddle;
	
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

        ballWorld= new BallWorld();
        
        MyWorldMouseListener m = new MyWorldMouseListener();
        ballWorld.setOnMouseMoved(m);
        MyKeyPressedListener p = new MyKeyPressedListener();
        ballWorld.setOnKeyPressed(p);
        MyKeyReleasedListener r = new MyKeyReleasedListener();
        ballWorld.setOnKeyReleased(r);

        border.setCenter(ballWorld);

        Scene scene = new Scene(border);
        stage.setScene(scene);

        ball = new Ball(getClass().getClassLoader().getResource("resources/ball.png").toString(), 4, 4);
        ball.setX(320);
        ball.setY(240);
        
        paddle = new Paddle(getClass().getClassLoader().getResource("resources/paddle.png").toString());
        paddle.setX(50);
        paddle.setY(380);
        
        Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
        b.setX(100);
        b.setY(100);
       
        ballWorld.add(ball);
        ballWorld.add(paddle);
        ballWorld.add(b);
        ballWorld.start();
        stage.show();
        ballWorld.requestFocus();
    }
    
    class MyWorldMouseListener implements EventHandler<MouseEvent> {
        double previousPaddlePos = 0;
    	@Override
    	public void handle(MouseEvent event) {
    		paddle.setX(event.getX());
    		if (previousPaddlePos - paddle.getX() < 0) {
    		    paddle.setState(Paddle.State.RIGHT);
            }
            if (previousPaddlePos - paddle.getX() > 0) {
                paddle.setState(Paddle.State.LEFT);
            }
            else {
                paddle.setState(Paddle.State.STILL);

            }

            previousPaddlePos = paddle.getX();


        }
    	
    }
    
    class MyKeyPressedListener implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			ballWorld.addKeyCode(event.getCode());
		} 	
    }
    
    class MyKeyReleasedListener implements EventHandler<KeyEvent> {

		@Override
		public void handle(KeyEvent event) {
			ballWorld.removeKeyCode(event.getCode());
		} 	
    }
}


