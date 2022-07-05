function getStartPoints() {
    const startX = window.innerWidth / 2;
    const startY = window.innerHeight;

    return [startX, startY];
}

function initializeCanvas(canvasName) {
    let canvas = document.getElementById(canvasName);
    let ctx = canvas.getContext('2d');
    ctx.canvas.width = window.innerWidth;
    ctx.canvas.height = window.innerHeight;

    return [canvas, ctx];
}

export {
    getStartPoints,
    initializeCanvas
}