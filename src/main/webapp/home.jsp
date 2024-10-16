<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
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
</head>

<body>
<div class="w-screen h-screen bg-lb_blue flex flex-col justify-between items-center">
    <div class="flex flex-col items-center space-y-12">
        <svg
                xmlns="http://www.w3.org/2000/svg"
                width="150"
                height="150"
                viewBox="0 0 640 512"
                fill="#EBB723"
                class="mt-10"
        >
            <path d="M344 24l0 144c0 13.3-10.7 24-24 24s-24-10.7-24-24l0-144c0-13.3 10.7-24 24-24s24 10.7 24 24zM192 320c0-17.7 14.3-32 32-32l192 0c17.7 0 32 14.3 32 32l0 32-256 0 0-32zm-77.3 90.5c8.1-16.3 24.8-26.5 42.9-26.5l324.7 0c18.2 0 34.8 10.3 42.9 26.5l27.6 55.2C563.5 487 548 512 524.2 512l-408.4 0c-23.8 0-39.3-25-28.6-46.3l27.6-55.2zM36.3 138.3c7.5-10.9 22.5-13.6 33.4-6.1l104 72c10.9 7.5 13.6 22.5 6.1 33.4s-22.5 13.6-33.4 6.1l-104-72c-10.9-7.5-13.6-22.5-6.1-33.4zm534.1-6.1c10.9-7.5 25.8-4.8 33.4 6.1s4.8 25.8-6.1 33.4l-104 72c-10.9 7.5-25.8 4.8-33.4-6.1s-4.8-25.8 6.1-33.4l104-72z"/>
        </svg>

        <div class="text-lb_green font-bold text-4xl">
            LB Minesweeper Multiplayer
        </div>
        <div class="text-lb_lightblue text-lg">
            Work as a team, fulfill the dream!
        </div>
        <div class="flex flex-col items-center space-y-6">
            <form action="./" method="post">
                <button
                        type="submit"
                        class="px-12 py-2 bg-lb_yellow text-white font-bold text-lg rounded-md hover:bg-lb_green/90 transition duration-300"
                >
                    New Game
                </button>
            </form>
            <p class="text-lb_lightblue text-center">
                Launch a game and send the link to a friend to join forces and play together!
            </p>
        </div>
    </div>

    <div class="text-lb_lightblue mb-4 text-center">
        Powered by
        <a href="https://mtocommunity.com" target="_blank" class="text-lb_green hover:underline">
            MTO Crew
        </a> and Luis Ventura
    </div>
</div>
</body>


</html>