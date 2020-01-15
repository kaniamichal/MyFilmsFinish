package pl.myFilms.Controllers;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.event.ActionEvent;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckMenuItem;
import javafx.stage.Stage;
import pl.myFilms.Utilities.DialogsUtilities;

import java.util.Optional;

public class MenuBarController {

      public void closeApp() {
        Optional<ButtonType> result = DialogsUtilities.confirmDialog();
        if (result.get() == ButtonType.OK) {
            Platform.exit();
            System.exit(0);
        }
    }

        public void CaspianCSS (ActionEvent actionEvent){
            Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        }

        public void ModenaCSS (ActionEvent actionEvent){
            Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        }

        public void setAlwaysOnTop (ActionEvent actionEvent){
            JFXPanel borderPane = null;
            Stage stage = (Stage) borderPane.getScene().getWindow();
            boolean value = ((CheckMenuItem) actionEvent.getSource()).selectedProperty().get();
            stage.setAlwaysOnTop(value);

        }

        public void about () {
            DialogsUtilities.dialogAboutApplications();
        }
    }
