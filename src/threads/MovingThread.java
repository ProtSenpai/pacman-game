package threads;

import model.Pacman;
import ui.PacmanController;

public class MovingThread extends Thread{
	
	private PacmanController pc;
    private Pacman pacman;
	//private boolean stop;
	//private boolean move;
//	private boolean sprite;
	

	public MovingThread(PacmanController pc, Pacman pacman ){
		this.pc = pc;
		this.pacman= pacman;
	}
	
	@Override
	public void run() {
		
		while(true) {
				pacman.move((double)pc.getWith(),(double) pc.getHeigth());
//				pc.closeMouth(sprite);
//				pc.openMouth(sprite);
				try {
					sleep(pacman.getWait());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		
	}
	



	
	
}