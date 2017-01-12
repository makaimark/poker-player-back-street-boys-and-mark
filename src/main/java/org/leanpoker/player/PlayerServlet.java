package org.leanpoker.player;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class PlayerServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().print("Java player is running");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("action").equals("bet_request")) {
            String gameState = req.getParameter("game_state");
//            String inAction = (String)req.getAttribute("in_action");
//            String players = (String)req.getAttribute("players");
//            String communityCards = (String)req.getAttribute("community_cards");
//            System.err.println("\n_______________________________________________________________________________");
//
//            System.err.println("New logging:" + players + gameState + inAction + communityCards);
//
//            System.err.println("_______________________________________________________________________________\n");
            resp.getWriter().print(Player.betRequest(new JsonParser().parse(gameState)));
        }
        if (req.getParameter("action").equals("showdown")) {
            String gameState = req.getParameter("game_state");

            Player.showdown(new JsonParser().parse(gameState));
        }
        if (req.getParameter("action").equals("version")) {
            resp.getWriter().print(Player.VERSION);
        }
    }
}
