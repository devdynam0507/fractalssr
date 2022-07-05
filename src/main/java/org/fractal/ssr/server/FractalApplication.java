package org.fractal.ssr.server;

import java.nio.file.Paths;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.file.FileService;

public class FractalApplication {

	public static void main(String[] args) {
		Server server = Server.builder()
			.http(8080)
			.annotatedService(new FractalService())
			.serviceUnder("/",
				FileService.of(Paths.get("/home/ubuntu/fractal-browser/")))
			.build();
		server.start().join();
	}

}
