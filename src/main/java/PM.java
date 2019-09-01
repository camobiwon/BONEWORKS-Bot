import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class PM implements MessageCreateListener {

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.isPrivateMessage()){
            if (event.getMessageAuthor().isBotUser()) return;
            //Log user sent messages
            System.out.println("[" + Main.time() + "]" +
                    "(Direct Message)" +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    event.getMessage().getContent()
            );
            Main.logSend();
        }
    }
}