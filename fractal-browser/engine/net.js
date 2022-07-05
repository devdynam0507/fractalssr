class FractalStreaming {

    constructor(onStream, onClose) {
        this.onStream = onStream;
        this.onClose = onClose;
    }

    preParser = (a)=>a.split('}{').map((item,index) => {
        if(a.split('}{').length>1){
           if (index== 0){return item+'}'} // first msg
           else if (index<a.split('}{').length-1){return '{'+item+'}'} // middle msgs
           else {return '{'+item}} // last msg
        else {return a} // no need to do anything if single msg
    })

    async fractalStreaming() {
        const x = parseInt(window.innerWidth / 2);
        const y = parseInt(window.innerHeight);
        const response = await fetch(`http://13.124.75.42:8080//fractal?x=${x}&y=${y}`);
        const reader = response.body.getReader();
        const decoder = new TextDecoder("utf-8");
        while(true) {
            const { value, done } = await reader.read();
            if (done) {
                break;
            }
            const text = decoder.decode(value);
            this.preParser(text).forEach(value => {
                value = JSON.parse(value);
                this.onStream(value.bx, value.by, value.x, value.y);
            });
        }
        this.onClose();
    }

}

export default FractalStreaming;