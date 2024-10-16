new Game({
    state: 'PROGRESS',
})

function draw() {
    const game = Game.getInstance();
    if (game === null) return;

    game.draw();
}

// Events

// Right click
canvas.addEventListener("contextmenu", function (event) {
    event.preventDefault();
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const gap = Game.getInstance().getGap();
    const cellSize = canvas.width / Game.getInstance().getSize() - gap;

    const col = Math.floor(x / (cellSize + gap));
    const row = Math.floor(y / (cellSize + gap));

    // Gap
    const insideCellX = x % (cellSize + gap) < cellSize;
    const insideCellY = y % (cellSize + gap) < cellSize;

    if (insideCellX && insideCellY && row < Game.getInstance().getSize() && col < Game.getInstance().getSize()) {
        console.log(`Click on row ${row}, col ${col}`);
        GameSocket.getInstance().toggleFlagCell(col, row);
    } else {
        console.log('Click on gap, not a cell');
    }
});

// Left click
canvas.addEventListener("click", function (event) {
    const rect = canvas.getBoundingClientRect();
    const x = event.clientX - rect.left;
    const y = event.clientY - rect.top;
    const gap = Game.getInstance().getGap();
    const cellSize = canvas.width / Game.getInstance().getSize() - gap;

    const col = Math.floor(x / (cellSize + gap));
    const row = Math.floor(y / (cellSize + gap));

    // Gap
    const insideCellX = x % (cellSize + gap) < cellSize;
    const insideCellY = y % (cellSize + gap) < cellSize;

    if (insideCellX && insideCellY && row < Game.getInstance().getSize() && col < Game.getInstance().getSize()) {
        console.log(`Click on row ${row}, col ${col}`);
        GameSocket.getInstance().discoverCell(col, row);
    } else {
        console.log('Click on gap, not a cell');
    }
});

console.log(window.location.toString().split("?uuid=").pop())

draw();