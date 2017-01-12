package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {
        JsonObject json = request.getAsJsonObject();
        JsonElement gameState = json.get("game_state");
        System.err.println("Log:" + gameState);
        return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
