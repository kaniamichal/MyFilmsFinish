package pl.myFilms.Utilities;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import java.util.ResourceBundle;

public class FxmlUtilities {

    public static Pane fxmlLoader(String fxmlPath) {
        //Locale.setDefault(new Locale("pl"));
        FXMLLoader loader = new FXMLLoader(FxmlUtilities.class.getResource(fxmlPath));
        loader.setResources(getResourceBundle());
        try {
            return loader.load();
        } catch (Exception e) {
           DialogsUtilities.errorDialog(e.getMessage());
        }
        return null;
    }

    public static ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("bundles.messages");
    }
}
