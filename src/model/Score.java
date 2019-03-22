package model;

public class Score {
	//Attributes
	private String name;
	private int score;
	
	public Score(String n, int s) {
		this.name=n;
		this.score=s;
		
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
