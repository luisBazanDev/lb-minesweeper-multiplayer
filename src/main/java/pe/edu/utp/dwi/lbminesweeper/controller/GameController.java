package pe.edu.utp.dwi.lbminesweeper.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pe.edu.utp.dwi.lbminesweeper.domain.GameSettings;
import pe.edu.utp.dwi.lbminesweeper.model.Game;
import pe.edu.utp.dwi.lbminesweeper.service.GameProvider;

import java.io.IOException;
import java.util.UUID;

@WebServlet(value = "/")
public class GameController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // JSP
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuidGame = UUID.randomUUID();
        Game game = new Game(uuidGame.toString(), new GameSettings());

        GameProvider.addGame(game);

        resp.sendRedirect(req.getContextPath() + "/play?uuid=" + uuidGame);
    }
}
