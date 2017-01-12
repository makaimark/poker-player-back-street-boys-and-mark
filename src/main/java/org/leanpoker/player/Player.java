package org.leanpoker.player;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        JsonObject jObject = request.getAsJsonObject();
        jObject = jObject.getAsJsonObject("gameState");
        System.err.println(jObject);
        return 1000;
    }

    public static void showdown(JsonElement game) {
    }
}
