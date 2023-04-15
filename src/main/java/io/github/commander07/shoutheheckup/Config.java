package io.github.commander07.shoutheheckup;

import eu.midnightdust.lib.config.MidnightConfig;

import java.util.List;

public class Config extends MidnightConfig {
    @Entry(name = "Ignored Players") public static List<String> ignoredPlayers = List.of();
    @Entry(name = "Banned Words") public static List<String> bannedWords = List.of();
    @Entry(name = "Hide Level", min=0,max=3) public static int hideLevel = 1;
    @Entry(name = "Filter Chat") public static boolean filterChat = false;
    @Entry(name = "Enabled") public static boolean enabled = true;
}
