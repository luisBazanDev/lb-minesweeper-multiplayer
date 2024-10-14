<%--
  Created by IntelliJ IDEA.
  User: AARON
  Date: 10/13/2024
  Time: 6:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head>
    <title>Minesweeper Game</title>
    <script type="text/javascript">
        var socket = new WebSocket("ws://localhost:8080/LB-minesweeper/ws");

        socket.onopen = function() {
            console.log("Connected to server");
        };

        socket.onmessage = function(event) {
            var gameData = JSON.parse(event.data);
            console.log("Game Data Received: ", gameData);
        };

        socket.onclose = function() {
            console.log("Connection closed");
        };

        function sendMove(x, y, isFlag) {
            var move = JSON.stringify({x: x, y: y, isFlag: isFlag});
            socket.send(move);
        }
    </script>
</head>
<body>
<h1>Minesweeper Game</h1>
<!-- Button to sample move -->
<button onclick="sendMove(3, 4, false)">Make Move (3, 4)</button>
</body>
</html>


