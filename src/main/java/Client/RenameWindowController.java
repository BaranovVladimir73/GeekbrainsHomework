package Client;

import common.CommandToServer;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RenameWindowController implements Initializable {

    public Button buttonOk;
    public Button buttonCancel;
    public TextField newFileNameField;

    private String newFileName;

    public void renameFile(){
        if (!newFileNameField.getText().isEmpty()){
            newFileName = newFileNameField.getText();
            closeWindow();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Укажите новое имя файла", ButtonType.OK);
            alert.showAndWait();
        }

    }

    public String getNewFileName() {
        return newFileName;
    }

    public void closeWindow() {
        Stage stage = (Stage) newFileNameField.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
