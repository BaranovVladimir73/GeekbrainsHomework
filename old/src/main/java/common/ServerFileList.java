package common;

import java.util.ArrayList;

public class ServerFileList extends AbstractMessage{

    private ArrayList<String> serverFileList;

    public ServerFileList() {
    }

    public ServerFileList(ArrayList<String> serverFileList) {
        this.serverFileList = serverFileList;
    }

    public ArrayList<String> getServerFileList() {
        return serverFileList;
    }
}
