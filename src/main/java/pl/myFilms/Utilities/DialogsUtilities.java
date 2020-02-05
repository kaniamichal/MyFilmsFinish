package pl.myFilms.Utilities;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

import javax.swing.*;
import java.util.Optional;
import java.util.ResourceBundle;

public class DialogsUtilities {

    static ResourceBundle bundle = FxmlUtilities.getResourceBundle();

    public static void dialogAboutApplications() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle(bundle.getString("about.title"));
        infoAlert.setHeaderText(bundle.getString("about.header"));
        infoAlert.setContentText(bundle.getString("about.content"));
        infoAlert.showAndWait();
    }

    public static Optional<ButtonType> confirmDialog() {
        Alert confirmDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmDialog.setTitle(bundle.getString("exit.title"));
        confirmDialog.setHeaderText(bundle.getString("exit.header"));
        Optional<ButtonType> result = confirmDialog.showAndWait();
        return result;
    }

    public static void errorDialog(String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));

        JTextArea textArea = new JTextArea(error);
        errorAlert.getDialogPane().setContentText(String.valueOf(textArea));
        errorAlert.showAndWait();
    }

    public static String editDialog(String value) {
        TextInputDialog dialog = new TextInputDialog(value);
        dialog.getEditor().clear();
        dialog.setTitle(bundle.getString("edit.title"));
        dialog.setHeaderText(bundle.getString("edit.header"));
        dialog.setContentText(bundle.getString("edit.content"));
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            return result.get();
        }
        return null;
    }

    public static void initDialog (String error) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setTitle(bundle.getString("error.title"));
        errorAlert.setHeaderText(bundle.getString("error.header"));
        errorAlert.getDialogPane().setContentText(bundle.getString("error.get.dao"));
        errorAlert.showAndWait();
    }


}
