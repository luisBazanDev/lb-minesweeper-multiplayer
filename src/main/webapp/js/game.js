new Game({
    state: 'PROGRESS',
    cells: [
        [
            new Cell({x: 0, y: 0, value: -2, type: 'NONE'}),
            new Cell({x: 0, y: 1, value: 3, type: 'NONE'}),
            new Cell({x: 0, y: 2, value: -2, type: 'NONE'}),
            new Cell({x: 0, y: 3, value: -2, type: 'NONE'})
        ],
        [
            new Cell({x: 1, y: 0, value: -1, type: 'NONE'}),
            new Cell({x: 1, y: 1, value: 0, type: 'NONE'}),
            new Cell({x: 1, y: 2, value: 2, type: 'NONE'}),
            new Cell({x: 1, y: 3, value: 2, type: 'NONE'})
        ],
        [
            new Cell({x: 2, y: 0, value: -2, type: 'NONE'}),
            new Cell({x: 2, y: 1, value: -2, type: 'MINE'}),
            new Cell({x: 2, y: 2, value: -1, type: 'MINE'}),
            new Cell({x: 2, y: 3, value: -1, type: 'MINE'})
        ],
        [
            new Cell({x: 3, y: 0, value: -2, type: 'NONE'}),
            new Cell({x: 3, y: 1, value: -2, type: 'MINE'}),
            new Cell({x: 3, y: 2, value: -2, type: 'MINE'}),
            new Cell({x: 3, y: 3, value: -2, type: 'MINE'})
        ]
    ]
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
    } else {
        console.log('Click on gap, not a cell');
    }

    // cellRightClick(cell);
    // TODO: logic to click
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
    } else {
        console.log('Click on gap, not a cell');
    }

    // TODO: logic to click
    // if (!game.gameOver) {
    //     cellClick(cell);
    // }
    // draw();
});

console.log(window.url.parameters.get("uuid"))

draw();