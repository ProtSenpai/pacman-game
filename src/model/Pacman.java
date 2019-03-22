package model;


public class Pacman {
	
	//Constants
	public static final int ADVANCE = 5;
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	//Attributes
	
	private int level;
	private double radio;
	private double posx;
	private double posy;
	private String direction;
	private int wait;
	private int bounces;
	private boolean stoped;
	private Move state;
	
	//Relations
	
	private Score[] scores;
	
	
	
	
	//Constructor
	
	public Pacman(double radio, double posx, double posy, int wait, int bounces, boolean stoped, Move m ) {
		this.radio = radio;
		this.posx = posx;
		this.posy = posy;
		this.wait = wait;
		this.bounces = bounces;
		this.stoped = stoped;
		state=m;
		scores= new Score[10];
	}
	
	//Get and Set
	
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public double getRadio() {
		return radio;
	}

	public void setRadio(double radio) {
		this.radio = radio;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getWait() {
		return wait;
	}

	public void setWait(int wait) {
		this.wait = wait;
	}

	public int getBounces() {
		return bounces;
	}

	public void setBounces(int bounces) {
		this.bounces = bounces;
	}

	public boolean isStoped() {
		return stoped;
	}

	public void setStoped(boolean stoped) {
		this.stoped = stoped;
	}
	
	
	public Score[] getScores() {
		return scores;
	}

	public void setScores(Score[] scores) {
		this.scores = scores;
	}

	public void move(double maxX,double maxY) {
		switch(state) {
			case RIGHT:
				if(posx+ADVANCE+radio>maxX) {
					state = Move.LEFT;
					posx = maxX-radio;
				}else {
					posx = posx+ADVANCE;					
				}
			break;
			case LEFT:
				if(posx-ADVANCE-radio<0) {
					state = Move.RIGHT;
					posx = radio;
				}else {
					posx = posx-ADVANCE;					
				}
			break;
		case UP:
			if(posy-ADVANCE-radio<0) {
				state = Move.DOWN;
				posy = radio;
			}else {
				posy = posy-ADVANCE;					
			}
			break;
		case DOWN:
			if(posy+ADVANCE+radio>maxY) {
				state = Move.UP;
				posy = maxY-radio;
			}else {
				posy = posy+ADVANCE;					
			}
			break;
		
		}
	}
	
	
}
