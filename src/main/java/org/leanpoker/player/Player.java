package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        JsonObject jObject = request.getAsJsonObject();
       // JsonObject inAction =  jObject.getAsJsonObject("in_action");
        JsonArray players = jObject.getAsJsonArray("players");
        System.err.println("\n_____________________________________________________________________");
        System.err.println("LOG: " + players);
        //System.err.println("in action: " + inAction);
        System.err.println("_____________________________________________________________________\n");

//        JsonObject json = request.getAsJsonObject();
//        JsonElement gameState = json.get("game_state");
//        System.err.println("\n_____________________________________________________________________");
//        System.err.println("Log:" + gameState);
//        System.err.println("_____________________________________________________________________\n");
        return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
