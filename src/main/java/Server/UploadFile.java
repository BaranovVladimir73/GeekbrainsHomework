package Server;

import lombok.Data;
import java.io.File;
import java.io.Serializable;

@Data
public class UploadFile implements Serializable {

    private File file;
    private String FileName;
    private int startPosition;
    private int endPosition;
    private byte[] bytes;


}
