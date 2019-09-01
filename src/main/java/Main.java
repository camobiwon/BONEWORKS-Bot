import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    public static long ownerID = 271788139381653514L;

    public static void main(String[] args) {
        try {
            //Get token from token.txt file
            String token;
            token = new String(Files.readAllBytes(Paths.get("token.txt")));

            //Go online
            DiscordApi api = new DiscordApiBuilder().setToken(token).login().join();
            System.out.println("Startup successful! Bot should now be online");
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