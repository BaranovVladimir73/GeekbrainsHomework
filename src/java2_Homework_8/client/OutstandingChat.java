package java2_Homework_8.client;

import java2_Homework_8.client.communication.ClientCommunicator;
import java2_Homework_8.client.gui.ChatFrame;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


public class OutstandingChat {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;
    private final StorySaver storySaver = new StorySaver();
    private final List<String> savedMessages;

    public OutstandingChat() {
        communicator = new ClientCommunicator();
        savedMessages = storySaver.sendStory();
        Consumer<String> outboundMessageConsumer = communicator::sendMessage;

        frame = new ChatFrame(outboundMessageConsumer);

        for (String message:savedMessages) {
            frame.getInboundMessageConsumer().accept(message);
        }

        new Thread(()-> {
            while(true){
                String inboundMessage = communicator.receiveMessage();

                frame.getInboundMessageConsumer().accept(inboundMessage);
                storySaver.saveStory(inboundMessage);
            }
        })
                .start();
    }


}
