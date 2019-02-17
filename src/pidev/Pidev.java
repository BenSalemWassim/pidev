/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev;

import Services.JobOwnerService;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import java.io.FileInputStream;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author wassim
 */
public class Pidev extends Application {
//define your offsets here
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage stage) throws Exception {
        
      
      Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
     // UndecoratorScene.setClassicDecoration();

// Undecorator undecorator = new Undecorator(stage,(Region)root);
// 
// undecorator.getStylesheets().add("skin/undecorator.css");
 Scene scene = new Scene(root);

  stage.setScene(scene);
  // Transparent scene and stage
// scene.setFill(Color.TRANSPARENT);
// stage.initStyle(StageStyle.TRANSPARENT);
 
// Set minimum size

 stage.show();}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
