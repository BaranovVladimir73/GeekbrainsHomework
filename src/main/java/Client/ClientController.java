package Client;

import common.AbstractMessage;
import common.CommandToServer;
import common.ServerFileList;
import common.UploadFile;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Slf4j
public class ClientController implements Initializable {

    public TextField fieldClientFilePath;
    public ListView<String> textAreaClientFile;
    public ListView<String> textAreaServerFile;
    public Button sendButton;
    public Button deleteButton;
    public Button renameButton;
    public Button uploadButton;
    private File file;
    private NettyNet net;
    private volatile int start = 0;


    public void showFilesOnClient(){

        textAreaClientFile.getItems().clear();
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

        Platform.runLater(()-> {
            if (abstractMessage instanceof ServerFileList) {
                ServerFileList serverFileList = (ServerFileList) abstractMessage;
                ArrayList<String> fileOnServer = serverFileList.getServerFileList();
                textAreaServerFile.getItems().clear();
                textAreaServerFile.getItems().addAll(fileOnServer);
            } else if (abstractMessage instanceof UploadFile){
                UploadFile in = (UploadFile) abstractMessage;
                byte[] bytes = in.getBytes();
                String fileName = in.getFileName();
                int byteRead = in.getEndPosition();
                String pathFile = fieldClientFilePath.getText() + File.separator + fileName;
                File file = new File(pathFile);
                try {
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
                    randomAccessFile.seek(start);
                    randomAccessFile.write(bytes);
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showFilesOnClient();
            }
        });
    }

    public void sendFile(){

        String item = textAreaClientFile.getSelectionModel().getSelectedItem();
        File file = new File(fieldClientFilePath.getText() + "\\" + item);
        try {
            UploadFile uploadFile = new UploadFile();
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            randomAccessFile.seek(uploadFile.getStartPosition());
            byte[] bytes = Files.readAllBytes(Paths.get(fieldClientFilePath.getText() + "\\" + item));
            int byteRead = (int) randomAccessFile.length();
            log.debug(file.getName());
            uploadFile.setFileName(file.getName());
            uploadFile.setFile(file);
            uploadFile.setBytes(bytes);
            uploadFile.setEndPosition(byteRead);
            log.debug(uploadFile.toString());
            net.sendMessage(uploadFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void renameFileOnServer(){

        String oldFileName = textAreaServerFile.getSelectionModel().getSelectedItem();
        String newFileName;

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/RenameWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            RenameWindowController controller = fxmlLoader.<RenameWindowController>getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Confirmation");
            stage.setScene(new Scene(root1));
            stage.resizableProperty().set(false);
            stage.showAndWait();
            newFileName = controller.getNewFileName();
            CommandToServer commandToServer = new CommandToServer();
            commandToServer.setCommand("#rename_file");
            commandToServer.setFileName(oldFileName);
            commandToServer.setNewFileName(newFileName);
            log.debug(commandToServer.toString());
            net.sendMessage(commandToServer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFileOnServer(){

        String item = textAreaServerFile.getSelectionModel().getSelectedItem();
        CommandToServer deleteFile = new CommandToServer();
        deleteFile.setCommand("#delete_file");
        deleteFile.setFileName(item);
        log.debug(deleteFile.toString());
        net.sendMessage(deleteFile);

    }

    public void uploadFileFromServer(){

        String item = textAreaServerFile.getSelectionModel().getSelectedItem();
        CommandToServer uploadFile = new CommandToServer();
        uploadFile.setCommand("#upload_file");
        uploadFile.setFileName(item);
        net.sendMessage(uploadFile);
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
