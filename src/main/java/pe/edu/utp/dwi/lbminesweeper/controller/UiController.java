package pe.edu.utp.dwi.lbminesweeper.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pe.edu.utp.dwi.lbminesweeper.model.Game;
import pe.edu.utp.dwi.lbminesweeper.service.GameProvider;

import java.io.IOException;

@WebServlet(value = "/play")
public class UiController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Game game = GameProvider.getGame(req.getParameter("uuid"));

        if(game == null) {
            resp.sendRedirect("./");
            return;
        }

        // JSP
        req.getRequestDispatcher("play.jsp").forward(req, resp);
    }
}
