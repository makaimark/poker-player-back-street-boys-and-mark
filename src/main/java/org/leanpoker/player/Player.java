package org.leanpoker.player;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Player {

    static final String VERSION = "Default Java folding player";

    public static int betRequest(JsonElement request) {

        JsonObject jObject = request.getAsJsonObject();
        String inAction = jObject.get("in_action").getAsString();
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

        JsonArray community_cards = jObject.get("community_cards").getAsJsonArray();

        List<String> highcards = Arrays.asList("8", "9", "10", "J", "Q", "K", "A");

        System.err.println("Current buy" + jObject.get("current_buy_in").getAsInt());

        List<String> commCards = new ArrayList<>();
        for (int i = 0; i < community_cards.size(); i++) {
            commCards.add(String.valueOf(community_cards.get(i).getAsJsonObject().get("rank")));
        }

        HashMap<String, Integer> cardsSuits = new HashMap<>();
        if (suit0.equals(suit1)) {
            cardsSuits.put(suit0, 2);
        } else {
            cardsSuits.put(suit0, 1);
            cardsSuits.put(suit1, 1);
        }

        for (int i = 0; i < community_cards.size(); i++) {
            if (cardsSuits.containsKey(String.valueOf(community_cards.get(i).getAsJsonObject().get("suit")))) {
                cardsSuits.put(String.valueOf(community_cards.get(i).getAsJsonObject().get("suit")), cardsSuits.get(String.valueOf(community_cards.get(i).getAsJsonObject().get("suit")) + 1));
            } else {
                cardsSuits.put(String.valueOf(community_cards.get(i).getAsJsonObject().get("suit")), 1);
            }
        }

        System.err.println("CARD SUIT NUMBER: " + cardsSuits.get("clubs"));
        System.err.println("CARD SUIT NUMBER: " + cardsSuits.get("diamonds"));
        System.err.println("CARD SUIT NUMBER: " + cardsSuits.get("hearts"));
        System.err.println("CARD SUIT NUMBER: " + cardsSuits.get("spades"));

        if (community_cards.size() == 0) {
            //If we don't have flop
            if (rank0.equals(rank1)) {
                System.err.println("Without flop, hand pair");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt();
            } else if (cardsSuits.containsValue(2) && (highcards.contains(rank0) && highcards.contains(rank1))) {
                System.err.println("without river - same Suits");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt();
            } else if (highcards.contains(rank0) && highcards.contains(rank1)) {
                System.err.println("Without flop, high cards");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt();
            } else {
                return 0;
            }
        } else {
            if (commCards.contains(rank0) || commCards.contains(rank1)) {
                System.err.println("flop-turn-river - pair");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt()*3;
            } else if (cardsSuits.containsValue(4)) {
                System.err.println("4 same color");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt();
            } else if (cardsSuits.containsValue(5)) {
                System.err.println("5 same color");
                return jObject.get("current_buy_in").getAsInt() + jObject.get("minimum_raise").getAsInt()*3;
            } else
            {System.err.println("flop-turn-river without pair");
                return 0;
            }
        }
    }

    public static void showdown(JsonElement game) {
    }
}
