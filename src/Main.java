import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import com.maingame.Controller.StartScreenController;

import java.io.IOException;

public class Main extends Application implements Runnable {
    @Override
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/startscreen.fxml"));
        VBox startScreenBox = loader.load();
        StartScreenController controller = loader.getController();
        controller.setMainStage(primaryStage);

        Scene scene = new Scene(startScreenBox, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("SimSaga");
        primaryStage.show();

        startGameLoop();
    }

    private void startGameLoop(){
        Thread gameThread = new Thread(this);
        gameThread.start();
    }

    /** This method runs the fps loop on a background thread allowing for all the main game components to take place on
     * the game thread. */
    @Override
    public void run() {

        int fps = 15;
        double timePerFrame = 1000000000.0 / fps;
        long lastFrame = System.nanoTime();
        long currentTime;

        int frames = 0;
        long lastCheck = System.currentTimeMillis();

        while(true) {

            currentTime = System.nanoTime();

            if ((currentTime - lastFrame) >= timePerFrame) {
                lastFrame = currentTime;
                frames++;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

    }
}