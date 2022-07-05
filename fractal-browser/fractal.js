import FractalStreaming from "./engine/net.js"
import FractalRenderer from "./engine/render.js";

const networkEngine = new FractalStreaming(onStream, onStreamClose);
const renderer = new FractalRenderer('board');

function onStream(bx, by, x, y) {
    renderer.addQueue(bx, by, x, y);
    if(renderer.queueSize() > 100) {
        renderer.draw();
    }
}

function onStreamClose() {
    renderer.draw();
}

networkEngine.fractalStreaming();