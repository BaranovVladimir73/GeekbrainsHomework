package common;

import lombok.Data;
import java.io.File;

@Data
public class UploadFile extends AbstractMessage{

    private File file;
    private String FileName;
    private int startPosition;
    private int endPosition;
    private byte[] bytes;
    private String userName;


}
