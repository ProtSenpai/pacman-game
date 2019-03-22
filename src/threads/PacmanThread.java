package threads;

import ui.PacmanController;

public class PacmanThread extends Thread{
	
	private PacmanController pc;

	public PacmanThread(PacmanController pc){
		this.pc = pc;
	}
	
	@Override
	public void run() {
		pc.update();
	}
	
}

