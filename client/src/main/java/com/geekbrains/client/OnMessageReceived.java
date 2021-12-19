package com.geekbrains.client;


import com.geekbrains.common.AbstractMessage;

public interface OnMessageReceived {

    void onReceive(AbstractMessage msg);
}
