package com.geekbrains.common;

import lombok.Data;

@Data
public class CommandToServer extends AbstractMessage{

    private String command;
    private String fileName;
    private String newFileName;
    private String userName;
}
