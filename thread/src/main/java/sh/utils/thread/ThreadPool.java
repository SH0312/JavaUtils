package sh.utils.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {
	/** 작업 스래드풀 */
	private final ThreadPoolExecutor taskExecutor;

	/**
	 * 스레드풀 생성
	 * 
	 * @param theadCount 스레드 수
	 */
	public ThreadPool(int theadCount) {
		// thread queue size - 2배수 생성 / default integer.MAX_VALUE
		// 해당 size 초과시 블락킹 처리
		int blockingQueueSize = theadCount * 2;

		//ThreadPoolExecutor(기본 풀 Size(최소 스레드 생성 수), 최대 스레드 수, ,keepAliveTime 유지시간(3번 파라미터), 기본 풀 Size보다 스레드가 많아지면 큐에 담음.)
		taskExecutor = new ThreadPoolExecutor(theadCount, theadCount, 0L, TimeUnit.MILLISECONDS,
				new LinkedBlockingQueue<Runnable>(blockingQueueSize));

		// 작업이 가득차면 대기
		taskExecutor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				try {
					executor.getQueue().put(r);
				} catch (InterruptedException e) {
					e.printStackTrace();
					Thread.currentThread().interrupt();
					return;
				}
			}
		});
	}

	/**
	 * 작업 수행
	 *
	 * @param task 작업
	 */
	public void execute(final Runnable task) {
		taskExecutor.execute(task);
	}

	/**
	 * 작업 종료
	 * 
	 */
	public void taskEnd() {
		// 큐에 남은 작업까지 처리 후 종료 됨.
		taskExecutor.shutdown();

		try {
			// 스레드 작업 처리까지 loop
			while (!taskExecutor.awaitTermination(1, TimeUnit.SECONDS)) {
				//System.out.println("running info message");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}