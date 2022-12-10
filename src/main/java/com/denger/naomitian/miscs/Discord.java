package com.denger.naomitian.miscs;

import com.denger.naomitian.miscs.discordhelper.*;


public class Discord {
    public static String discordID = "1020334506823131307";
    public static DiscordRichPresence discordRichP = new DiscordRichPresence();

    public static DiscordRPC discordRPC = DiscordRPC.INSTANCE;


    public static void startRPC() {

        DiscordEventHandlers eventHandlers = new DiscordEventHandlers();
        eventHandlers.disconnected = ((var1, var2) -> System.out.println("Discord RPC disconnected, var1:" + var1 + ", var2: " + var2));


        discordRPC.Discord_Initialize(discordID, eventHandlers, true, null);

        discordRichP.startTimestamp = System.currentTimeMillis() / 1000L;
        discordRichP.details = "byDenger";
        discordRichP.largeImageKey = "1";
        discordRichP.largeImageText = "Micotian";
        discordRichP.state = null;
        discordRPC.Discord_UpdatePresence(discordRichP);
    }

    public static void stopRPC() {
        discordRPC.Discord_Shutdown();
        discordRPC.Discord_ClearPresence();
    }
}
