package pl.myFilms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pl.myFilms.Utilities.FxmlUtilities;

import static pl.myFilms.database.dbutils.DbManager.initDatabase;

/**
 * Created by kaniamichal January 2020
 */

public class Main extends Application {

    public static final String BORDER_PANE_FXML = "/fxml/BorderPane.fxml";

    public static void main(String[] args) { launch(args);}

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void setMainStage(Stage mainStage) {
        Main.mainStage = mainStage;
    }

    private static Stage mainStage;
    private BorderPane borderPane;


    public void start(Stage primaryStage) throws Exception {
        //Locale.setDefault(new Locale("pl"));
        Pane borderPane = FxmlUtilities.fxmlLoader(BORDER_PANE_FXML);
        Scene scene = new Scene(borderPane);
        mainStage = primaryStage;
        mainStage.setScene(scene);
        mainStage.setTitle(FxmlUtilities.getResourceBundle().getString("title.aplication"));
        mainStage.show();

        initDatabase();
      // FillDatabase.fillDatabase();
    }
/*działający start
    public void start(Stage primaryStage) throws Exception {
        //Locale.setDefault(new Locale("pl"));
        Pane borderPane = FxmlUtilities.fxmlLoader(BORDER_PANE_FXML);
        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle(FxmlUtilities.getResourceBundle().getString("title.aplication"));
        primaryStage.show();
    }*/

    public BorderPane getBorderPane() {
        return (BorderPane) borderPane.getParent();
    }

    public void setBorderPane(BorderPane borderPane) {
        this.borderPane = borderPane;
    }

}
