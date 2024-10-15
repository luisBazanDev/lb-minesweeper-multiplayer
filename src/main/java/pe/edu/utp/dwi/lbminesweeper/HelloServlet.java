package pe.edu.utp.dwi.lbminesweeper;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import pe.edu.utp.dwi.lbminesweeper.domain.ObfuscatedCell;
import pe.edu.utp.dwi.lbminesweeper.model.Game;
import pe.edu.utp.dwi.lbminesweeper.domain.GameSettings;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World 2!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        Game game = new Game(new GameSettings());

        ObfuscatedCell[][] obfuscatedCells = game.getObfuscatedCells();

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h1>" + message + "</h1>");
        out.println(game.toString().replaceAll("\n", "<br/>"));
        out.println("</body></html>");
    }

    public void destroy() {
    }
}