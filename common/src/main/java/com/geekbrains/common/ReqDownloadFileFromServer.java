package com.geekbrains.common;

import lombok.Data;

@Data
public class ReqDownloadFileFromServer extends AbstractMessage{

    private String fileName;
    private String userName;
}
