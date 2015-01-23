import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Scan extends Thread {
	private volatile boolean mFinish = false;
	private int dif = -1;
	private static Lock lock = new ReentrantLock();

	public void finish() {
		mFinish = true;
	}

	private boolean haveRadioStatio(Integer v) {
		for (Integer i : RadioStations.rs)
			if (i.equals(v))
				return true;
		return false;
	}

	public void setScan() {
		dif = -1;
	}

	public void setReset() {
		dif = 1;
	}

	@Override
	public void run() {
		int cur = mainForm.slider.getValue();
		if (lock.tryLock()) {
			try {
				do {
					if (!mFinish) {
						try {
							if (cur == 88 || (dif == 1 && cur == 108))
								return;
							cur += dif;
							sleep(500);
							System.out.println("scan " + cur);
							mainForm.slider.setValue(cur);
							if (haveRadioStatio(cur))
								return;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						return;
					}
				} while (true);
			} finally {
				lock.unlock();
			}
		}

	}
}