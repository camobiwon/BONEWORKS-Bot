import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main {

    //Initial variables
    public static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
    private static Logger logger = LogManager.getLogger(Main.class);
    public static long ownerID = 271788139381653514L;

    //Time formatting
    public static String time() {
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //Log messaging
    public static void logSend(String text) {
        if(text == null){
            System.out.println("[" + time() + "]" + "(LOG) Response Sent");
        } else {
            System.out.println("[" + time() + "]" + "(LOG) " + text);
        }
    }

    //Set log message to null by default
    public static void logSend() {
        logSend(null);
    }

    //Main call
    public static void main(String[] args) {
        try {
            //Get token from token.txt file
            String token;
            token = new String(Files.readAllBytes(Paths.get("token.txt")));

            //Go online
            DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
            System.out.println("\nStartup successful! Bot should now be online");
            System.out.println("=====[ CONSOLE OUTPUT ]=====");

            FallbackLoggerConfiguration.setDebug(true);

            //Listeners
            api.addMessageCreateListener(new Commands());
            api.addMessageCreateListener(new PM());

            //Console output if bot joins / leaves server
            api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
            api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("token.txt file not found / unreadable! (Make sure it's in same directory)");
        }
    }
}