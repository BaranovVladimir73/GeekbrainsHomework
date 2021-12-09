package Client;

import common.AbstractMessage;
import common.ServerFileList;
import common.UploadFile;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class ClientController implements Initializable {

    public TextField fieldClientFilePath;
    public ListView<String> textAreaClientFile;
    public Button sendButton;
    private File file;
    private NettyNet net;



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

    public void showFilesOnServer(AbstractMessage abstractMessage){

        if (abstractMessage instanceof ServerFileList) {

        }

    }

    public void sendFile(){

        String item = textAreaClientFile.getSelectionModel().getSelectedItem();
        File file = new File(fieldClientFilePath.getText() + "\\" + item);
        try {
            UploadFile uploadFile = new UploadFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(uploadFile.getStartPosition());
            byte[] bytes = new byte[1024*10];
            int byteRead = randomAccessFile.read(bytes);
            log.debug(file.getName());
            uploadFile.setFileName(file.getName());
            uploadFile.setFile(file);
            uploadFile.setBytes(bytes);
            uploadFile.setEndPosition(byteRead);
            log.debug(uploadFile.toString());
            net.sendFile(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {

            net = new NettyNet(this::showFilesOnServer);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
