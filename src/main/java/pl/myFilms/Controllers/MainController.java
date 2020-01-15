package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myFilms.Main;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.FxmlUtilities;

public class MainController {


    @FXML //for initialize methods
    private BorderPane borderPane;

    @FXML //Add MenuButtons to main BorderPane
    private MenuButtonsController menuButtonsController;

    //przekazanie do MenuButtonsController referencji do MainController, by móc sterować BorderPane,
    private void initialize() {
        menuButtonsController.setMainController(this);
    }

    //  public void setCenter(String fxmlPath){borderPane.setCenter(fxmlPath);}


    public BorderPane getBorderPane() {
        return borderPane = getBorderPane();

    }

    //Methods to show new forms in new stage
    public static void setCenterFxml(String fxmlPath) {

        Pane loader = FxmlUtilities.fxmlLoader(fxmlPath);

        try {
            Scene sceneNew = new Scene(loader);
            Stage mainStage = Main.getMainStage();
            mainStage.setScene(sceneNew);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }
    /*public void exportToJson (ActionEvent e) throws IOException {

        SceneFactory exportSceneFactory = new ExportSceneFactory();
        Stage mainWindow = Main.getMainStage();
        mainWindow.setScene(exportSceneFactory.build());
    }
    public Scene build() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("view/export.fxml"));
        root.getStylesheets().add(
                Main.class.getResource("view/style/main.css").toExternalForm()
        );
        return new Scene(root, 1200, 800);
    }*/
}




