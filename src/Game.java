import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
	private BallWorld ballWorld;
	private Ball ball;
	private Paddle paddle;
	private Scene titleScene;
	private Scene gameScene;
	private Scene instructionScene;
	private Scene gameOverScene;
	private RotateTransition rt;
	private String state = "MainMenu";
    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        //UI Stuff
        rt = new RotateTransition();
        rt.setDuration(Duration.seconds(2));
        rt.setByAngle(360);
        DropShadow dropShadow = new DropShadow(10, 10, 10, Color.GRAY);

        //Setting window up
        stage.setTitle("BallWorld");
        stage.setResizable(false);
        stage.setWidth(665);
        stage.setHeight(480);



        ballWorld = new BallWorld(stage, titleScene, gameOverScene);


        //Intro screen

        //Creating a group for all of the intro screen elements
        Group titleScreen = new Group();

        //creating title text
        Text breakoutText = new Text("Breakout!");
        breakoutText.setEffect(dropShadow);
        breakoutText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));

        //Creating title buttons
        Button playButton = new Button("Play");
        playButton.setEffect(dropShadow);
        playButton.setPrefSize(400, 100);
        playButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(gameScene);
            	ballWorld.start();
                ballWorld.requestFocus();
                stage.show();
            }
        });
        
        Button instructionButton = new Button("Instructions");
        instructionButton.setPrefSize(400, 100);
        instructionButton.setEffect(dropShadow);
        instructionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
            	stage.setScene(instructionScene);
            	rt.playFromStart();
            	stage.show();
            }
        });

        playButton.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");
        instructionButton.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");

        //Set positions
        breakoutText.setLayoutX(120);
        breakoutText.setLayoutY(100);
        playButton.setLayoutX(120);
        playButton.setLayoutY(160);
        instructionButton.setLayoutX(120);
        instructionButton.setLayoutY(300);

        titleScreen.getChildren().add(breakoutText);
        titleScreen.getChildren().add(playButton);
        titleScreen.getChildren().add(instructionButton);

        titleScene = new Scene(titleScreen);
        stage.setScene(titleScene);

        rt.setNode(breakoutText);

        rt.playFromStart();
        stage.show();







        //Instruction Screen stuff
        Group instructionScreen = new Group();

        //Create Instruction Title Text
        Text instructionText = new Text("Instructions!");
        instructionText.setEffect(dropShadow);
        instructionText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));

        //Create Buttons
        Button instructionReturnButton = new Button("Return to Main Menu");
        instructionReturnButton.setPrefSize(200, 50);
        instructionReturnButton.setEffect(dropShadow);
        instructionReturnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(titleScene);
                stage.show();
                rt.playFromStart();
            }
        });

        instructionReturnButton.setStyle("-fx-border-color: #0000ff; -fx-border-width: 5px; -fx-font-size: 1em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");

        //Create instruction message

        Text instructionMessage = new Text();
        instructionMessage.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.ITALIC, 10));
        instructionMessage.setText("             Use the cursor or arrow keys to move the paddle left and right to bounce the ball.\n" +
                "                    When the ball hits a brick, the brick is removed and you gain 100 points.\n" +
                "If the ball hits the bottom of the screen, you lose 1000 points and 1 life. You have 3 lives in total");

        //Position elements and add them
        instructionText.setLayoutX(60);
        instructionText.setLayoutY(100);

        instructionMessage.setLayoutX(60);
        instructionMessage.setLayoutY(160);

        instructionReturnButton.setLayoutX(230);
        instructionReturnButton.setLayoutY(200);

        instructionScreen.getChildren().add(instructionText);
        instructionScreen.getChildren().add(instructionMessage);
        instructionScreen.getChildren().add(instructionReturnButton);

        instructionScene = new Scene(instructionScreen);

        rt.setNode(instructionText);


        //ball world stuff
        BorderPane ballWorldScene = new BorderPane();

        MyWorldMouseListener m = new MyWorldMouseListener();
        ballWorld.setOnMouseMoved(m);
        MyKeyPressedListener p = new MyKeyPressedListener();
        ballWorld.setOnKeyPressed(p);
        MyKeyReleasedListener r = new MyKeyReleasedListener();
        ballWorld.setOnKeyReleased(r);
        ballWorldScene.setCenter(ballWorld);



        ball = new Ball(getClass().getClassLoader().getResource("resources/ball.png").toString(), 2, 2);
        ball.setX(320);
        ball.setY(240);

        paddle = new Paddle(getClass().getClassLoader().getResource("resources/paddle.png").toString());
        paddle.setX(50);
        paddle.setY(380);

        //Create bricks
        ballWorld.addBricks();


        ballWorld.add(ball);
        ballWorld.add(paddle);
        gameScene = new Scene(ballWorldScene);




        //game over scene
        //Set up scene
        Group gameOverScreen = new Group();

        //Create UI elements
        Text gameOverText = new Text("Game Over!");
        gameOverText.setEffect(dropShadow);
        gameOverText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 100));

        Text scoreText = new Text("Your Score Was " + ballWorld.getScore().getScore()+ "!");
        scoreText.setEffect(dropShadow);
        scoreText.setFont(Font.font("impact", FontWeight.BOLD, FontPosture.ITALIC, 30));



        Button replayButton = new Button("Replay");
        replayButton.setEffect(dropShadow);
        replayButton.setPrefSize(400, 100);
        replayButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(gameScene);
                ballWorld.reset();
                stage.show();

            }
        });
        Button mainMenuButton = new Button("Main Menu");
        mainMenuButton.setPrefSize(400, 100);
        mainMenuButton.setEffect(dropShadow);
        mainMenuButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                stage.setScene(titleScene);
                ballWorld.reset();
                stage.show();
                rt.playFromStart();
            }
        });

        replayButton.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");
        mainMenuButton.setStyle("-fx-border-color: #0000ff; -fx-border-width: 10px; -fx-font-size: 4em;-fx-background-color: #6495ed;-fx-text-fill: #0000ff");



        gameOverScreen.getChildren().add(scoreText);
        gameOverScreen.getChildren().add(replayButton);
        gameOverScreen.getChildren().add(mainMenuButton);
        gameOverScreen.getChildren().add(gameOverText);

        gameOverText.setLayoutX(100);
        gameOverText.setLayoutY(100);
        scoreText.setLayoutX(200);
        scoreText.setLayoutY(160);
        replayButton.setLayoutX(120);
        replayButton.setLayoutY(200);
        mainMenuButton.setLayoutX(120);
        mainMenuButton.setLayoutY(320);

        gameOverScene = new Scene(gameOverScreen);



        rt.setNode(gameOverText);

        AnimationTimer mainLoop = new AnimationTimer() {

            @Override
            public void handle(long now) {
                if (stage.getScene() == (gameScene)) {
                    if (ballWorld.isGameOver()) {
                        stage.setScene(gameOverScene);
                        stage.show();
                        scoreText.setText("Your Score Was " + ballWorld.getScore().getScore()+ "!");
                        rt.playFromStart();
                    }
                    if (ballWorld.isLevelCleared()) {
                        ballWorld.addBricks();
                    }
                }
            }

        };
        mainLoop.start();




    }
    
    class MyWorldMouseListener implements EventHandler<MouseEvent> {
        double previousPaddlePos = 0;
    	@Override
    	public void handle(MouseEvent event) {
    	    if (event.getX()<600) {
                paddle.setX(event.getX());
            }
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


