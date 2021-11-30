package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CloudController implements Initializable {


    public TextField fieldClientFilePath;
    public ListView<String> textAreaClientFile;
    public Button sendButton;

    private File file;
    private IoNet net;


    public void showFilesOnClient(ActionEvent actionEvent){

        file = new File(fieldClientFilePath.getText());
        List<File> files = new ArrayList<>();
        for (File file:file.listFiles()) {
            if (file.isFile()){
                files.add(file);
            }
        }
        for (File file:files) {
            textAreaClientFile.getItems().add(file.getName());
        }
    }

    public void sendFile(){

        String item = textAreaClientFile.getSelectionModel().getSelectedItem();
        File file = new File(fieldClientFilePath.getText() + "\\" + item);
        try {
            net.writeFileName(file);
            net.writeFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Socket socket = new Socket("localhost", 8189);
            net = new IoNet(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
