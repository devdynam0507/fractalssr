package org.fractal.ssr.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.linecorp.armeria.common.HttpData;
import com.linecorp.armeria.common.HttpMethod;
import com.linecorp.armeria.common.HttpResponse;
import com.linecorp.armeria.common.HttpResponseWriter;
import com.linecorp.armeria.common.ResponseHeaders;
import com.linecorp.armeria.server.annotation.Blocking;
import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.decorator.CorsDecorator;
import org.fractal.ssr.core.FractalEngine;
import org.fractal.ssr.core.FractalEngineObserver;
import org.fractal.ssr.core.FractalStreamer;
import org.fractal.ssr.core.TreeFractal;

public class FractalService {

	private FractalEngine engine = new TreeFractal();

	@Get("/fractal")
	@CorsDecorator(origins = "*", credentialsAllowed = true,
		nullOriginAllowed = true, allowedRequestMethods = HttpMethod.GET)
	public HttpResponse fractal(@Param("x") int x, @Param("y") int y) {
		final HttpResponseWriter streaming = HttpResponse.streaming();
		final FractalEngineObserver fractalEngineObserver = new FractalStreamer(streaming);

		streaming.write(ResponseHeaders.of(200));
		streaming.whenConsumed().thenRun(() -> {
			engine.render(x, y, 0.8f, 20, 90, 15, fractalEngineObserver);
			streaming.close();
		});
		return streaming;
	}

}
