import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
	private BallWorld ballWorld;
	private Ball ball;
	private Paddle paddle;
	private Scene scene1;
	private Scene scene2;
	private Scene instruction_scene1;
	private Scene gameover_scene1;
	
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {

        RotateTransition rt = new RotateTransition();
        rt.setDuration(Duration.seconds(2));
        rt.setByAngle(360);

        // Play it (after showing the Stage)



        stage.setTitle("BallWorld");
        stage.setResizable(false);
        stage.setWidth(665);
        stage.setHeight(480);

        DropShadow dropShadow = new DropShadow(10, 10, 10, Color.GRAY);


        //title screen stuff
        BorderPane titleScreen = new BorderPane();
        Text text = new Text("Breakout!");
        text.setEffect(dropShadow);
        text.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));

        Button button = new Button("Play");
        button.setEffect(dropShadow);
        button.setPrefSize(400, 100);
        button.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(scene2);
            	ballWorld.start();
            }
        });
        
        Button button2 = new Button("Instructions");
        button2.setPrefSize(400, 100);
        button2.setEffect(dropShadow);
        button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(instruction_scene1);
            }
        });

        button.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");
        button2.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");

        VBox center = new VBox();
        titleScreen.setCenter(center);
        Separator separator1 = new Separator();
        separator1.setOrientation(Orientation.VERTICAL);
        separator1.setMaxWidth(100);
        center.getChildren().add(button);
        center.getChildren().add(separator1);
        center.getChildren().add(button2);

        center.setAlignment(Pos.CENTER);

        HBox centerTop = new HBox();
        centerTop.getChildren().add(text);

        centerTop.setAlignment(Pos.CENTER);
        titleScreen.setTop(centerTop);

        scene1 = new Scene(titleScreen);
        stage.setScene(scene1);

        rt.setNode(text);









        //instruction screen stuff
        BorderPane instruction = new BorderPane();
        Text instruction_text = new Text("Instructions!");
        instruction_text.setEffect(dropShadow);

        instruction_text.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));

        Button instruction_button2 = new Button("Return to Main Menu");
        instruction_button2.setPrefSize(200, 50);
        instruction_button2.setEffect(dropShadow);
        instruction_button2.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(scene1);
            }
        });

        instruction_button2.setStyle("-fx-border-color: #0000ff; -fx-border-width: 5px; -fx-font-size: 1em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");

        Text instruction_message = new Text();
        instruction_message.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 10));
        instruction_message.setText("Use the cursor or arrow keys to move the paddle left and right to bounce the ball.\n" +
                "When the ball hits a brick, the brick is removed and you gain 100 points.\n" +
                "If the ball hits the bottom of the screen, you lose 1000 points and 1 life. You have 3 lives in total");
        instruction_message.setTextAlignment(TextAlignment.CENTER);
        VBox instruction_center = new VBox();
        instruction.setCenter(instruction_center);
        Separator instruction_separator1 = new Separator();
        instruction_separator1.setOrientation(Orientation.VERTICAL);
        instruction_separator1.setMinHeight(100);
        instruction_center.getChildren().add(instruction_message);
        instruction_center.getChildren().add(instruction_separator1);
        instruction_center.getChildren().add(instruction_button2);

        instruction_center.setAlignment(Pos.TOP_CENTER);

        HBox instruction_centerTop = new HBox();
        instruction_centerTop.getChildren().add(instruction_text);

        instruction_centerTop.setAlignment(Pos.CENTER);
        instruction.setTop(instruction_centerTop);

        instruction_scene1 = new Scene(instruction);
        //stage.setScene(instruction_scene1);

        rt.setNode(instruction_text);






        //ball world stuff

        BorderPane border = new BorderPane();

        ballWorld = new BallWorld();

        MyWorldMouseListener m = new MyWorldMouseListener();
        ballWorld.setOnMouseMoved(m);
        MyKeyPressedListener p = new MyKeyPressedListener();
        ballWorld.setOnKeyPressed(p);
        MyKeyReleasedListener r = new MyKeyReleasedListener();
        ballWorld.setOnKeyReleased(r);
        border.setCenter(ballWorld);
        


        ball = new Ball(getClass().getClassLoader().getResource("resources/ball.png").toString(), 2, 2);
        ball.setX(320);
        ball.setY(240);

        paddle = new Paddle(getClass().getClassLoader().getResource("resources/paddle.png").toString());
        paddle.setX(50);
        paddle.setY(380);

        for (int y = 80; y<160; y+=30) {
            for (int x = 0; x < 11; x++) {
                Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
                b.setX(x * 60);
                b.setY(y);
                ballWorld.add(b);

                Brick b2 = new Brick(getClass().getClassLoader().getResource("resources/brick2.png").toString());
                b2.setX(x * 60 + 30);
                b2.setY(y);
                ballWorld.add(b2);
            }
            for (int x = 0; x < 11; x++) {

                Brick b2 = new Brick(getClass().getClassLoader().getResource("resources/brick.png").toString());
                b2.setX(x * 60 + 30);
                b2.setY(y+15);
                ballWorld.add(b2);

                Brick b = new Brick(getClass().getClassLoader().getResource("resources/brick2.png").toString());
                b.setX(x * 60);
                b.setY(y+15);
                ballWorld.add(b);
            }
        }


        ballWorld.add(ball);
        ballWorld.add(paddle);
        //ballWorld.start();
        ballWorld.requestFocus();

        scene2 = new Scene(border);
        //stage.setScene(scene2);






        //game over scene
        BorderPane gameover_titleScreen = new BorderPane();
        Text gameover_text = new Text("Game Over!");
        gameover_text.setEffect(dropShadow);
        gameover_text.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));



        Text score_text = new Text("Your Score Was " + ballWorld.getScore().getScore()+ "!");
        score_text.setEffect(dropShadow);
        score_text.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 30));



        Button gameover_button = new Button("Replay");
        gameover_button.setEffect(dropShadow);
        gameover_button.setPrefSize(400, 100);
        Button gameover_button2 = new Button("Main Menu");
        gameover_button2.setPrefSize(400, 100);
        gameover_button2.setEffect(dropShadow);

        gameover_button.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");
        gameover_button2.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");

        VBox gameover_center = new VBox();
        gameover_titleScreen.setCenter(gameover_center);
        Separator gameover_separator1 = new Separator();
        gameover_separator1.setOrientation(Orientation.VERTICAL);
        gameover_separator1.setMaxWidth(100);
        gameover_center.getChildren().add(score_text);
        gameover_center.getChildren().add(gameover_button);
        gameover_center.getChildren().add(gameover_separator1);
        gameover_center.getChildren().add(gameover_button2);

        gameover_center.setAlignment(Pos.CENTER);

        HBox gameover_centerTop = new HBox();
        gameover_centerTop.getChildren().add(gameover_text);


        gameover_centerTop.setAlignment(Pos.CENTER);
        gameover_titleScreen.setTop(gameover_centerTop);

        gameover_scene1 = new Scene(gameover_titleScreen);
        //stage.setScene(gameover_scene1);



        rt.setNode(gameover_text);


        stage.show();
        rt.play();
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


