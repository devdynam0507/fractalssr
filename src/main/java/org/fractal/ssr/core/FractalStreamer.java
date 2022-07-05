package org.fractal.ssr.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import com.linecorp.armeria.common.HttpData;
import com.linecorp.armeria.common.HttpResponseWriter;

public class FractalStreamer implements FractalEngineObserver {

    private final static Gson gson = new GsonBuilder().create();

    private final JsonObject jsonObject;
    private final HttpResponseWriter stream;

    public FractalStreamer(final HttpResponseWriter stream) {
        this.jsonObject = new JsonObject();
        this.stream = stream;
    }

    @Override
    public void onRendered(int beforeX, int beforeY, int currentX, int currentY, int depth) {
        writeJson(currentX, currentY, beforeX, beforeY);
        final String json = gson.toJson(jsonObject);
        stream.write(HttpData.ofAscii(json));
        clearJson();
    }

    private void writeJson(int bx, int by, int cx, int cy) {
        jsonObject.addProperty("x", cx);
        jsonObject.addProperty("y", cy);
        jsonObject.addProperty("bx", bx);
        jsonObject.addProperty("by", by);
    }

    private void clearJson() {
        jsonObject.remove("x");
        jsonObject.remove("y");
        jsonObject.remove("bx");
        jsonObject.remove("by");
    }

}
