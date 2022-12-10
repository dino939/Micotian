package com.denger.naomitian.manager;


import com.denger.naomitian.notifications.Type;
import com.denger.naomitian.notifications.notifications;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import com.denger.naomitian.module.misc.MiddleClick;
import net.minecraft.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class FriendManager {
    public static List<String> FRIENDS = new ArrayList<String>();


    public static void toggleFriend(String nick){
        if (isFriend(nick)){
            FRIENDS.remove(nick);
            notifications.add(TextFormatting.RED + nick, "Remove from Friend list", Type.OK);

        }else{
            FRIENDS.add(nick);
            notifications.add(TextFormatting.GREEN + nick, "add in Friends list", Type.OK);

        }


    }

    public static boolean isFriend(String nick) {
        return FRIENDS.contains(nick);
    }
    public static Entity ifFreand2(String nick){
        EntityPlayer.getOfflineUUID(nick);

        return ifFreand2(nick);
    }
}
