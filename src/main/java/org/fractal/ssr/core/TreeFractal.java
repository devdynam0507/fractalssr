package org.fractal.ssr.core;

public class TreeFractal implements FractalEngine {

	@Override
	public void render(int x1, int y1, float scale, float l, double angle, int depth, FractalEngineObserver observer) {
		if(depth > 0) {
			final double radian = Math.toRadians(angle);
			// angle 각도일 때 x,y 좌표는 각각 cos,sin(radian) * l만큼 이동하는데 10을 곱해서 소수점을 정규화 해주고 소수점 버림
			final int x2 = (int) (x1 - ((Math.cos(radian) * l) * 10));
			final int y2 = (int) (y1 - ((Math.sin(radian) * l) * 10));

			observer.onRendered(x1, y1, x2, y2, depth);

			render(x2, y2, scale, l * scale, angle - 30, depth - 1, observer);
			render(x2, y2, scale, l * scale, angle + 30, depth - 1, observer);
		}
	}

}
