package org.fractal.ssr.core;

@FunctionalInterface
public interface FractalEngineObserver {

	void onRendered(final int beforeX, final int beforeY, final int x, final int y, final int depth);

}
