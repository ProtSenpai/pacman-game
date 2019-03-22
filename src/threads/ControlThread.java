package threads;

import javafx.application.Platform;
import ui.PacmanController;

public class ControlThread extends Thread{
	
	private PacmanController pcc;
	
	public ControlThread(PacmanController pcc) {
		this.pcc = pcc;
	}
	
	public void run() {
		while (true) {
			PacmanThread pt = new PacmanThread(pcc);
			Platform.runLater(pt);

			try {
				sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
