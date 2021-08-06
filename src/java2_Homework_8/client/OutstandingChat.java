package java2_Homework_8.client;

import java2_Homework_8.client.communication.ClientCommunicator;
import java2_Homework_8.client.gui.ChatFrame;

import java.util.function.Consumer;


public class OutstandingChat {

    private final ChatFrame frame;
    private final ClientCommunicator communicator;

    public OutstandingChat() {
        communicator = new ClientCommunicator();

        Consumer<String> outboundMessageConsumer = communicator::sendMessage;

        frame = new ChatFrame(outboundMessageConsumer);

        new Thread(()-> {
            while(true){
                String inboundMessage = communicator.receiveMessage();
                frame.getInboundMessageConsumer().accept(inboundMessage);
            }
        })
                .start();



    }
}
