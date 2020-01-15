package pl.myFilms.Controllers;

import javafx.fxml.FXML;

public class MenuButtonsController {

    public static final String ALL_FILMS_FXML = "/fxml/AllFilms.fxml";
    public static final String ADD_FILMS_FXML = "/fxml/AddFilms.fxml";
    public static final String ADD_CATEGORY_FXML = "/fxml/AddCategory.fxml";
    public static final String ADD_AUTHOR_FXML = "/fxml/AddAuthor.fxml";
    public static final String SEARCH_FXML = "/fxml/Search.fxml";
    public static final String STATISTICS_FXML = "/fxml/Statistic.fxml";

    private MainController mainController;
    @FXML
    private AddCategoryControler addCategoryControler;
    private AddFilmsController addFilmsController;

    public MenuButtonsController() {
    }

    //setter
    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    //Methods for buttons action in MenuButtons
    @FXML
    public void allFilms() {MainController.setCenterFxml(ALL_FILMS_FXML); }

    @FXML
    public void addFilms() {MainController.setCenterFxml(ADD_FILMS_FXML); }

    @FXML
    public void addAuthor() { MainController.setCenterFxml(ADD_AUTHOR_FXML);      }

    @FXML
    public void addCategory() { MainController.setCenterFxml(ADD_CATEGORY_FXML);}

    @FXML
    public void search() { MainController.setCenterFxml(SEARCH_FXML);}

    @FXML
    public void stats() { MainController.setCenterFxml(STATISTICS_FXML);}

}
