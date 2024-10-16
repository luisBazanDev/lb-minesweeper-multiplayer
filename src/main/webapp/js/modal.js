function showModal(isWin) {
    const modal = document.getElementById("gameModal");
    const modalMessage = document.getElementById("modalMessage");

    if (isWin) {
        modalMessage.textContent = "You cleared the field! You win!";
        modalMessage.classList.remove("text-red-600");
        modalMessage.classList.add("text-white");
    } else {
        modalMessage.textContent = "You triggered a mine! Game over";
        modalMessage.classList.remove("text-white");
        modalMessage.classList.add("text-red-600");
    }

    modal.classList.remove("hidden");
}

function hideModal() {
    document.getElementById("gameModal").classList.add("hidden");
}

function newGame() {
    hideModal();
    GameSocket.getInstance().newGame();
    console.log("New game started");
}