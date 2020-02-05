package pl.myFilms.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myFilms.Main;
import pl.myFilms.Utilities.DialogsUtilities;
import pl.myFilms.Utilities.FxmlUtilities;

import java.util.Optional;

/**
 * Created by kaniamichal on January 2020
 */

public class MainController {
    @FXML
    private HBox menuButtons;
    @FXML
    private MenuBar menuBar;
    @FXML //for initialize methods
    private static BorderPane BorderPane;

    @FXML //Add MenuButtons to main BorderPane
    private MenuButtonsController menuButtonsController;

    @FXML
    private CheckMenuItem setAlwaysOnTop;
    @FXML
    private RadioMenuItem ModenaCSS;
    @FXML
    private ToggleGroup radioMenuItemGroup;
    @FXML
    private RadioMenuItem CaspianCSS;


    //przekazanie do MenuButtonsController referencji do MainController, by móc sterować BorderPane,
    private void initialize() {
        menuButtonsController.setMainController(this);
    }


    public BorderPane getBorderPane() {
        return BorderPane = getBorderPane();

    }

    public void setCenter(String fxmlPath){
        BorderPane.setCenter(FxmlUtilities.fxmlLoader(fxmlPath));
    }

    //Methods to show new forms in new stage
    public static void setCenterFxml(String fxmlPath) {

        Pane loader = FxmlUtilities.fxmlLoader(fxmlPath);

        try {
            assert loader != null;
            Scene sceneNew = new Scene(loader);
            Stage mainStage = Main.getMainStage();
            mainStage.setScene(sceneNew);
            mainStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            DialogsUtilities.errorDialog(e.getMessage());
        }
    }



    public void closeApp() {
        Optional<ButtonType> result = DialogsUtilities.confirmDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

    public void CaspianCSS(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
    }

    public void ModenaCSS(ActionEvent actionEvent) {
        Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
    }

    public void setAlwaysOnTop(ActionEvent actionEvent) {
        Stage stage = (Stage) BorderPane.getScene().getWindow();
        boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
        stage.setAlwaysOnTop(value);

    }

    public void about() {
        DialogsUtilities.dialogAboutApplications();
    }


}




