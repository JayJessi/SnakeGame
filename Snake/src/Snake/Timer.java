package Snake;

public class Timer {
	long startTime;

	public Timer() {
		reset();
	}

	public long getElapsedTime() {
		long now = System.nanoTime();
		long time = now - startTime;
		
		int i = (int)time;
		return time;
	}

	public void reset() {
		startTime = System.nanoTime();
	}
}
