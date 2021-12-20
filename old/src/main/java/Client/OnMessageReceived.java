package Client;

import common.AbstractMessage;

public interface OnMessageReceived {

    void onReceive(AbstractMessage msg);
}
