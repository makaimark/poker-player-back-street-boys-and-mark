package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        JsonObject jObject = request.getAsJsonObject();
        String inAction =  jObject.get("in_action").getAsString();
        JsonArray players = jObject.getAsJsonArray("players");
        System.err.println("\n_____________________________________________________________________");
        System.err.println("LOG: " + players.get(Integer.parseInt(inAction)));
        System.err.println("_____________________________________________________________________\n");

        JsonElement ourPlayer = players.get(Integer.parseInt(inAction));
        JsonObject details = ourPlayer.getAsJsonObject();
        JsonArray cardsArray = details.getAsJsonArray("hole_cards");

        JsonElement card1 = cardsArray.get(0);
        JsonObject card1Object = card1.getAsJsonObject();
        JsonElement card2 = cardsArray.get(1);
        JsonObject card2Object = card2.getAsJsonObject();
        String suit0 = String.valueOf(card1Object.get("suit"));
        String rank0 = String.valueOf(card1Object.get("rank"));

        String suit1 = String.valueOf(card2Object.get("suit"));
        String rank1 = String.valueOf(card2Object.get("rank"));

        System.err.println("Suits and ranks: " + suit0 + rank0 + suit1 + rank1);

        JsonArray community_cards = jObject.get( "community_cards").getAsJsonArray();

        System.err.println("Community_cards: " + community_cards);
        List<String> highcards = Arrays.asList("J", "Q", "K", "A");
        List<String> mediumcards = Arrays.asList("8", "9", "10");

        System.err.println("Current buy" + jObject.get("current_buy_in").getAsInt());

        if (highcards.contains(rank0) && highcards.contains(rank1)) {
            return jObject.get("current_buy_in").getAsInt()+jObject.get("minimum_raise").getAsInt();
        } else if ((highcards.contains(rank0) && mediumcards.contains(rank1)) || (highcards.contains(rank1) && mediumcards.contains(rank0))) {
            return jObject.get("current_buy_in").getAsInt()-details.get("bet").getAsInt();
        } else if (rank0.equals(rank1)) {
            return jObject.get("current_buy_in").getAsInt()+jObject.get("minimum_raise").getAsInt();
        }
        return 0;
    }

    public static void showdown(JsonElement game) {
    }
}
