package pl.myFilms.Controllers;

import javafx.scene.layout.BorderPane;
import pl.myFilms.Main;

public class AllFilmsController {

    private MainController mainController;
    private BorderPane borderPane;
    private Main primaryStage;

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;

    }


}
