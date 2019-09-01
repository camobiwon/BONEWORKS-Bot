import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.btobastian.sdcf4j.CommandExecutor;

public class Command implements CommandExecutor {
    //Initialize date
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
    LocalDateTime now = LocalDateTime.now();

    //Custom commands
    //@Command(aliases = "!ping", description = "Pong!")
    public String onPingCommand() {
        return "Pong!";
    }
}