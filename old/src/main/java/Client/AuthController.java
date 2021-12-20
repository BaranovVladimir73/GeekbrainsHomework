package Client;

import common.AbstractMessage;
import common.AuthMessage;
import common.AuthNotOk;
import common.AuthOk;
import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    public TextField userNameField;
    public TextField passwordField;
    public Button enterButton;

    private String userName;
    private String password;
    private NettyNet net;


    public void sendAuth(){
        userName = userNameField.getText();
        password = passwordField.getText();
        AuthMessage authMessage = new AuthMessage();
        authMessage.setUserName(userName);
        authMessage.setPassword(password);
        net.sendMessage(authMessage);
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void closeWindow() {
        Stage stage = (Stage) enterButton.getScene().getWindow();

        stage.close();
    }

    public void getAuthorizationMessage(AbstractMessage abstractMessage){
        Platform.runLater(()-> {
            if (abstractMessage instanceof AuthOk) {
                closeWindow();
            } else if (abstractMessage instanceof AuthNotOk){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Неверный логин или пароль", ButtonType.OK);
                alert.showAndWait();
            }
        });
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            net = new NettyNet(this::getAuthorizationMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
