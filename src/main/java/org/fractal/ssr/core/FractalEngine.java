package org.fractal.ssr.core;

public interface FractalEngine {

	void render(int x, int y, float scale, float l, double angle, int depth, FractalEngineObserver observer);

}
