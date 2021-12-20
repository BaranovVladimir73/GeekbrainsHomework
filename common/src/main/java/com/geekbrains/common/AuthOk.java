package com.geekbrains.common;

import lombok.Data;

@Data
public class AuthOk extends AbstractMessage{

    private String authorizedUserName;
}
