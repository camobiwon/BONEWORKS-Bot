import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PM implements MessageCreateListener {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if(event.isPrivateMessage()){
            if (event.getMessageAuthor().isBotUser()) return;
            //Initialize date
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
            LocalDateTime now = LocalDateTime.now();
            //Log to console
            System.out.println("[" + dtf.format(now) + "]" +
                    "(Direct Message)" +
                    event.getMessageAuthor().getName() + "#" +
                    event.getMessageAuthor().getDiscriminator().get() + ": " +
                    event.getMessage().getContent()
            );
            //Send message
            System.out.println("[" + dtf.format(now) + "]" + "(LOG) DM sent");
        }
    }
}
