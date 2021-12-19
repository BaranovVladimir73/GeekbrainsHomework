package common;

import lombok.Data;

@Data
public class ReqToDeleteFileOnServer extends AbstractMessage{

    private String fileToDelete;
    private String userName;

}
