package common;

import lombok.Data;

@Data
public class ReqToRenameFile extends AbstractMessage{

    private String oldFileName;
    private String newFileName;
    private String userName;

}
