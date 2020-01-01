package org.camobiwon.boneworksbot;

import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.permission.Role;

import java.util.stream.Collectors;

public class DiscordActivity {

    private static String gameName = "boneworks";
    private static Role role = null;

    static void setBotStatus() {
        Main.getApi().updateActivity(ActivityType.PLAYING, "BONEWORKS");
    }
}