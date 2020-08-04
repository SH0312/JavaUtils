package sh.utils.thread;

public class ThreadMain {

	// 스레드 수 사용하는 메소드 및 PC 성능에 따라 적절한 스레드 수 쓸 것.
	public static final int THREADCOUNT = 10;

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ThreadPool muiltiThread = new ThreadPool(THREADCOUNT);
		for (int i = 0; i < 1000; i++) {
			int k = i;
			muiltiThread.execute(() -> {
				process(k);
			});

		}
		muiltiThread.taskEnd();
		long end = System.currentTimeMillis();
		System.out.println("process time : " + (end - start));
	}

	public static void process(int i) {
		try {
			Thread.sleep(10);
			System.out.println(i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
