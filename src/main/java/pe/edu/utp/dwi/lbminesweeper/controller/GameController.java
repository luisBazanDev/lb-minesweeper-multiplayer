package pe.edu.utp.dwi.lbminesweeper.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.utp.dwi.lbminesweeper.domain.GameSettings;
import pe.edu.utp.dwi.lbminesweeper.model.Game;

import java.io.IOException;
import java.util.UUID;

@WebServlet(value = "/")
public class GameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Game game = (Game) session.getAttribute("game");

        // JSP
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        Game game = (Game) session.getAttribute("game");

        UUID uuidGame = UUID.randomUUID();

        if(game == null) {
            game = new Game(new GameSettings());
        }

        req.setAttribute("game", game);

        resp.sendRedirect(req.getContextPath() + "/play?uuidGame=" + uuidGame);
    }
}
