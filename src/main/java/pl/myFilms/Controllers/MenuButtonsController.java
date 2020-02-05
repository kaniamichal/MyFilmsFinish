package pl.myFilms.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

import static pl.myFilms.Controllers.MainController.setCenterFxml;
/**
 * Created by kaniamichal
 */
public class MenuButtonsController {

    public static final String ALL_FILMS_FXML = "/fxml/AllFilms.fxml";
    public static final String ADD_FILMS_FXML = "/fxml/AddFilms.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_DIRECTOR_FXML = "/fxml/AddDirector.fxml";

    private MainController mainController;

    @FXML
    public ToggleGroup ToggleButtonsGroup;
    @FXML
    public void allFilms() { setCenterFxml(ALL_FILMS_FXML);}
    @FXML
    public void addFilms() { setCenterFxml(ADD_FILMS_FXML); }
    @FXML
    public void addAuthor() { setCenterFxml(ADD_DIRECTOR_FXML); }
    @FXML
    public void addCategory() { setCenterFxml(ADD_CATEGORY_FXML); }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;    }

}
