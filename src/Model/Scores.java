import java.io.Serializable;

public class Scores implements Observer,DisplayScores,Serializable {
	private int scores;
	
	public void display() {
		int theScores = this.scores;
		theScores = theScores + this.scores;
	}
	
	public void update(int theSCores) {
		this.scores = theSCores;
		display();
	}
	
	public int getScores() {
		return this.scores;
	}
	
	public void setScores() {
		this.scores = scores;
	}
}
