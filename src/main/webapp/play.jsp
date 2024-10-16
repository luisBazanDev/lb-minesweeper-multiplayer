<%--
  Created by IntelliJ IDEA.
  User: luisb
  Date: 10/14/24
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LB Minesweeper Multiplayer</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        lb_blue: '#132237',
                        lb_green: '#1CBAAC',
                        lb_yellow: '#EBB723',
                        lb_darkblue: '#0E1A29',
                        lb_lightblue: '#0B4C6C'
                    }
                }
            }
        }
    </script>
    <style>
        .copyTool {
            animation: popUp 300ms cubic-bezier(.42,.97,.52,1.49);
        }

        @keyframes popUp {
            0% {
                transform: translateY(50%);
                opacity: .5;
            }
            100% {
                transform: translateY(0);
                opacity: 1;
            }
        }
    </style>
</head>
<body>
    <div class="w-screen h-screen bg-lb_blue flex flex-col justify-between items-center">
        <div class="flex justify-between items-center pt-4 px-4 w-full">
            <a class="text-lb_green font-bold text-4xl ml-4 cursor-pointer" href="./">LB Minesweeper Multiplayer</a>
            <div class="bg-lb_darkblue flex flex-col gap-2 p-4 rounded-lg mr-6 mt-4">
                <div class="text-lb_lightblue text-nowrap">Invite your friends to join the game:</div>
                <div class="flex gap-2 relative">
                    <p id="copy-link" class="p-2 bg-lb_green/30 text-lb_yellow truncate w-96">Loading...</p>
                    <button onclick="copied()" class="text-lb_blue bg-lb_green p-2 rounded-r-md">
                        <svg
                                width="24"
                                height="24"
                                viewBox="0 0 24 24"
                                fill="none"
                                xmlns="http://www.w3.org/2000/svg"
                        >
                            <path d="M13 7H7V5H13V7Z" fill="currentColor" />
                            <path d="M13 11H7V9H13V11Z" fill="currentColor" />
                            <path d="M7 15H13V13H7V15Z" fill="currentColor" />
                            <path
                                    fill-rule="evenodd"
                                    clip-rule="evenodd"
                                    d="M3 19V1H17V5H21V23H7V19H3ZM15 17V3H5V17H15ZM17 7V19H9V21H19V7H17Z"
                                    fill="currentColor"
                            />
                        </svg>
                    </button>
                    <div id="copied-tool" class="copyTool absolute right-0 bg-lb_darkblue text-lb_green p-1 -top-[100%] hidden">
                        Copied!
                    </div>
                </div>
            </div>
        </div>

        <div class="px-6 py-4 bg-lb_darkblue w-2/3 flex justify-center">
            <canvas id="game" width="600" height="600" class="aspect-square cursor-pointer"></canvas>
        </div>
        <div class="text-lb_lightblue mb-4">Powered by <a href="https://mtocommunity.com" target="_blank" class="text-lb_green hover:underline">MTO Crew</a> and Luis Ventura</div>
    </div>

    <div id="gameModal" class="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-60 z-50 select-none hidden">
        <div class="bg-lb_darkblue p-6 rounded-lg shadow-lg text-center">
            <h2 id="modalMessage" class="text-2xl font-bold mb-4"></h2>
            <button onclick="newGame()" class="px-4 py-2 bg-lb_yellow text-white font-bold rounded hover:bg-lb_green/90 transition duration-300">
                New Game
            </button>
        </div>
    </div>

    <script src="js/modal.js"></script>
    <script src="js/core.js"></script>
    <script src="js/game.js"></script>

    <script>
        function copied () {
            navigator.clipboard.writeText(window.location.toString());
            const elem = document.getElementById("copied-tool");
            elem.classList.remove("hidden")
            setTimeout(() => elem.classList.add("hidden"), 1000);
        }

        document.getElementById("copy-link").innerText = window.location;
    </script>
</body>
</html>
