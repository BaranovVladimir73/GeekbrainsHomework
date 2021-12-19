package com.geekbrains.common;

import lombok.Data;

@Data
public class ReqToRefreshFilesOnServer extends AbstractMessage{

    private String userName;
}
