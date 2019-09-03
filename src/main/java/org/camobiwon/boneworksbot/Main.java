package org.camobiwon.boneworksbot;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //Variables
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm:ss a");
    private static Logger logger = LogManager.getLogger(Main.class);
    public static List<Long> adminIDs = new ArrayList<>();
    private static Long serverID = 563139253542846474L;
    private static DiscordApi api;
    private static ResourceLoader resourceLoader;

    //Time formatting
    public static String time() {
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    //Get admins from admin.txt file
    private static void getAdmins() {
        String admins;
        try {
            admins = resourceLoader.getFileContent("admins.txt");
            String[] admin = admins.split(",");
            for (String parseLong: admin) {
                adminIDs.add(Long.parseLong(parseLong));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("admins.txt file not found / unreadable! (Make sure it's in src\\main\\resources)");
        }
    }

    //Get server ID
    public static Server getServer() {
        return api.getServerById(serverID).get();
    }

    //Main call
    public static void main(String[] args) {
        resourceLoader = new ResourceLoader();

        try {
            //Get token from token.txt file
            String token = resourceLoader.getFileContent("token.txt");

            //Grab admin list
            getAdmins();

            //Go online
            api = new DiscordApiBuilder().setToken(token).login().join();
            System.out.println("Startup successful! Bot should now be online\n");
            System.out.println("=====[ CONSOLE OUTPUT ]=====");

            //Initialize
            ChatLog.chatInit(api);
            ChatLog.logMessage("Bot Online");
            FallbackLoggerConfiguration.setDebug(true);

            //Listeners
            api.addMessageCreateListener(new Commands());

            //Log output if bot joins / leaves server
            api.addServerJoinListener(event -> logger.info("Joined server " + event.getServer().getName()));
            api.addServerLeaveListener(event -> logger.info("Left server " + event.getServer().getName()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("token.txt file not found / unreadable! (Make sure it's in src\\main\\resources)");
        }
    }
}