package org.camobiwon.boneworksbot;

import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.user.UserChangeActivityEvent;
import org.javacord.api.listener.user.UserChangeActivityListener;

import java.util.stream.Collectors;

public class DiscordActivity implements UserChangeActivityListener {

    private static String gameName = "boneworks";
    private static Role role = null;

    static void setBotStatus() {
        Main.getApi().updateActivity(ActivityType.PLAYING, "BONEWORKS");
    }

    @Override
    public void onUserChangeActivity(UserChangeActivityEvent event) {
        //If bot, return
        if (event.getUser().isBot()) return;

        //If user activity equals game, add role
        if(event.getNewActivity().isPresent() && event.getNewActivity().get().getType().equals(ActivityType.PLAYING) && event.getNewActivity().get().getName().equalsIgnoreCase(gameName)) {
            event.getUser().addRole(getRole());
        }

        //If user activity now not equals game, remove role
        if(event.getOldActivity().isPresent() && event.getOldActivity().get().getType().equals(ActivityType.PLAYING) && event.getOldActivity().get().getName().equalsIgnoreCase(gameName)) {
            event.getUser().removeRole(getRole());
        }
    }

    //Check users who don't have role; Add if playing
    static int checkPlayingNoRole() {
        int usersModified = 0;

        for (User user : Main.getServer().getMembers().stream().filter(user -> {
            return user.getActivity().isPresent() && user.getActivity().get().getName().equalsIgnoreCase(gameName);
        }).collect(Collectors.toList())) {
            if (user.isBot()) continue;
            user.addRole(getRole());
            usersModified++;
        }
        return usersModified;
    }

    //Check users who have role; Remove if not playing
    static int checkRoleNotPlaying() {
        int usersModified = 0;

        for (User user : getRole().getUsers()) {
            if (user.isBot()) continue;
            if(!user.getActivity().isPresent() || (user.getActivity().isPresent() && !user.getActivity().get().getName().equalsIgnoreCase(gameName))) {
                user.removeRole(getRole());
                usersModified++;
            }
        }
        return usersModified;
    }

    private static Role getRole() {
        if(role == null) {
            long roleID = 631729226281779221L;
            Main.getServer().getRoleById(roleID).ifPresent(role -> {DiscordActivity.role = role;});
        }
        return role;
    }
}