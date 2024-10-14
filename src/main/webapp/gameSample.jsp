<%--
  Created by IntelliJ IDEA.
  User: AARON
  Date: 10/13/2024
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Minesweeper Multiplayer</title>
    <script type="text/javascript">
        let socket;
        let gameId = Math.random().toString(36).substring(7); // Random id for now

        function connect() {
            socket = new WebSocket("ws://localhost:8080/LB-minesweeper/ws/minesweeper");

            socket.onopen = function () {
                console.log("Connected to WebSocket server");
            };

            socket.onmessage = function (event) {
                const message = JSON.parse(event.data);
                updateBoard(message);
            };

            socket.onclose = function () {
                console.log("Disconnected from WebSocket server");
            };

            socket.onerror = function (error) {
                console.log("WebSocket error: " + error);
            };
        }

        function sendMove(x, y) {
            const move = {
                timestamp: Date.now(),
                position: { x: x, y: y },
                gameId: gameId
            };
            socket.send(JSON.stringify(move));
        }

        function updateBoard(data) {
            // TODO: Implement logic for this function, i want do nothing here <3
            console.log("Board Update Data sent ", data);
        }

        window.onload = connect;
    </script>
</head>
<body>
<h1>Minesweeper Multiplayer</h1>

<!-- Board Simulation until Wazan is able to implement it -->
<table>
    <tr>
        <td onclick="sendMove(0, 0)">[ ]</td>
        <td onclick="sendMove(0, 1)">[ ]</td>
        <td onclick="sendMove(0, 2)">[ ]</td>
    </tr>
    <tr>
        <td onclick="sendMove(1, 0)">[ ]</td>
        <td onclick="sendMove(1, 1)">[ ]</td>
        <td onclick="sendMove(1, 2)">[ ]</td>
    </tr>
    <tr>
        <td onclick="sendMove(2, 0)">[ ]</td>
        <td onclick="sendMove(2, 1)">[ ]</td>
        <td onclick="sendMove(2, 2)">[ ]</td>
    </tr>
</table>
</body>
</html>

