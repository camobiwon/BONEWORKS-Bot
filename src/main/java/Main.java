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
    private static Logger logger = LogManager.getLogger(Main.class);
    public static long ownerID = 271788139381653514L;

    //Print the output from the string that the user added
    public static void logSend(String text) {
        //Initialize date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();
        if(text == null){
            System.out.println("[" + dtf.format(now) + "]" + "(LOG) Response Sent");
        } else {
            System.out.println("[" + dtf.format(now) + "]" + "(LOG) " + text);
        }
    }

    public static void logSend() {
        logSend(null);
    }

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