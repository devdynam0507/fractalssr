import {
    initializeCanvas, 
    getStartPoints
} from './context.js';

class FractalRenderer {

    constructor(canvasName) {
        const [canvas, ctx] = initializeCanvas(canvasName);
        const [x, y] = getStartPoints();

        this.canvas = canvas;
        this.ctx = ctx;
        this.queue = [];
    }

    addQueue(bx, by, x, y) {
        this.queue.push({ bx: bx, by: by, x: x, y: y });
    }

    queueSize() {
        return this.queue.length;
    }

    async draw() {
        if (this.queue.length > 0) {
            const data = this.queue.pop();
            this.ctx.beginPath();
            this.ctx.moveTo(data.bx, data.by);
            this.ctx.lineTo(data.x, data.y);
            this.ctx.moveTo(data.x, data.y);
            this.ctx.stroke();
            this.ctx.fillStyle = 'red';
            this.ctx.fill();
            window.requestAnimationFrame(() => this.draw());
        }
    }

}

export default FractalRenderer;