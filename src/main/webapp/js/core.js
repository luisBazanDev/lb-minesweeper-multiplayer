const canvas = document.getElementById("game");

class Game {

    constructor() {
        this.state = "PROGRESS" // WIN / GAME_OVER / PROGRESS
        this.cells = [];

        if(typeof Game.instance === "object") {
            return Game.instance;
        }

        Game.instance = this;
        return this;
    }

    sync({cells, state}) {
        this.state = state;
        for (let i = 0; i < cells.length; i++) {
            for (let j = 0; j < cells[i].length; j++) {
                // {{x:  number, y: number, value: number, type: string}}
                const cell = cells[i][j];
                console.log(cell);
                if(!this.cells[i]) this.cells[i] = [];
                this.cells[i][j] = new Cell(cell)
            }
        }
        this.draw();
    }

    static getInstance() {
        return new Game();
    }

    getSize() {
        return this.cells.length;
    }

    getGap() {
        return (canvas.width / this.getSize()) * 0.1
    }

    draw() {
        const context = canvas.getContext('2d');

        context.reset();

        const gap = this.getGap();
        const width = canvas.width / this.getSize() - gap;
        const height = canvas.width / this.getSize() - gap;
        let xCell = 0;
        let yCell = 0;

        for (let x = 0; x < this.cells.length; x++) {
            for (let y = 0; y < this.cells[x].length; y++) {
                xCell = x * (width + gap);
                yCell = y * (height + gap);
                this.cells[x][y].drawInCanvasContext(context, xCell, yCell, width, height);
            }
        }
    }
}

class Cell {
    constructor({x,y,value,type}) {
        this.x = x;
        this.y = y;
        this.value = value; // 0-8 values, -1 flag, -2 hidden
        this.type = type; // NONE, MINE
    }

    isMine() {
        return this.type === "MINE";
    }

    isFlag() {
        return this.value === -1;
    }

    isHidden() {
        return this.value === -2;
    }

    /**
     * Draw the cell
     * @param {CanvasRenderingContext2D} context
     * @param {number} x
     * @param {number} y
     * @param {number} width
     * @param {number} height
     */
    drawInCanvasContext(context, x, y, width, height) {
        if (this.isMine()) {
            context.fillStyle = "#65181C";
            context.fillRect(x, y, width, height);
            if(!this.isFlag()) return;
            context.fillStyle = "#EBB723";
            const flagX = x + width / 6;
            const flagY = y + width / 6;
            const flagWidth = width * 2 / 3;
            const flagHeight = height * 2 / 3;
            context.fillRect(flagX, flagY, flagWidth, flagHeight);
            return;
        }
        if (this.isHidden()) {
            context.fillStyle = "#0B4C6C";
            context.fillRect(x, y, width, height);
            return;
        }
        if (this.isFlag()) {
            context.fillStyle = "#0B4C6C";
            context.fillRect(x, y, width, height);
            context.fillStyle = "#EBB723";
            const flagX = x + width / 6;
            const flagY = y + width / 6;
            const flagWidth = width * 2 / 3;
            const flagHeight = height * 2 / 3;
            context.fillRect(flagX, flagY, flagWidth, flagHeight);
            return;
        }
        if (this.value === 0) {
            context.fillStyle = "#DDDDDD";
            context.fillRect(x, y, width, height);
            return;
        }
        // Draw number
        context.fillStyle = "#DDDDDD";
        context.font = "bold " + (width  * 3 / 4) + "px Arial";
        context.fillRect(x, y, width, height);

        context.fillStyle = "#000000";
        context.fillText(this.value, x + width * 0.30, y + height * 0.75);
    }
}