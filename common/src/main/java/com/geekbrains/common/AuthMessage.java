package com.geekbrains.common;

import lombok.Data;

@Data
public class AuthMessage extends AbstractMessage{

    private String userName;
    private String password;
}
