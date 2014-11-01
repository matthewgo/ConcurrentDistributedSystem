package Server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Monitor {

	private static Monitor monitor;

	public static Monitor getInstance() {
		if (monitor == null)
			monitor = new Monitor();
		return monitor;
	}

	Lock lock = new ReentrantLock();

	Condition cond = lock.newCondition();

	int count = 1;

	public void waiting() {
		lock.lock();

		try {
			while (count == 0) {
				cond.await();
			}
			count--;
			System.out.println("subtracted");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void signaling() {
		lock.lock();
		cond.signalAll();
		count++;
		lock.unlock();
	}

}
