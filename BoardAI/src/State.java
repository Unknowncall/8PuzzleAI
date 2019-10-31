import java.util.ArrayList;

public class State {

	Board board;
	State parent;
	int gScore, hScore, fScore;
	
	// gScore: the current minimum bumber of moves needed to reach this state.
	// hScore: The hueristic value of this state.
	// fScore = gScore + hScore

	public State(Board board, State parent, int gScore, int hScore) {
		this.board = board;
		this.parent = parent;
		this.gScore = gScore;
		this.hScore = hScore;
		this.fScore = this.gScore + this.hScore;
	}

	public boolean equal(State other) {
		return (this.equals(other)) ? true : false;
	}

	public ArrayList<State> getNeighbors() {
		ArrayList<State> states = new ArrayList<State>();
		
		// up
		if (board.getEmptyRow() != 0) {
			Board up = board.copyBoard();
			up.swap(up.getEmptyRow() - 1, up.getEmptyCol(), up.getEmptyRow(), up.getEmptyCol());
			State state = new State(up, this, this.gScore + 1, this.hScore);
			if (!(this.parent != null && this.parent.getBoard().getTiles().equals(up.getTiles()))) {
				states.add(state);
			}
		}
		// left
		if (board.getEmptyCol() != 0) {
			Board left = board.copyBoard();
			left.swap(left.getEmptyRow(), left.getEmptyCol() - 1, left.getEmptyRow(), left.getEmptyCol());
			State state = new State(left, this, this.gScore + 1, this.hScore);
			if (!(this.parent != null && this.parent.getBoard().getTiles().equals(left.getTiles()))) {
				states.add(state);
			}
		}
		// right
		if (board.getEmptyCol() != 2) { 
			Board right = board.copyBoard();
			right.swap(right.getEmptyRow(), right.getEmptyCol() + 1, right.getEmptyRow(), right.getEmptyCol());
			State state = new State(right, this, this.gScore + 1, this.hScore);
			if (!(this.parent != null && this.parent.getBoard().getTiles().equals(right.getTiles()))) {
				states.add(state);
			}
		}
		// down
		if (board.getEmptyRow() != 2) {
			Board down = board.copyBoard();
			down.swap(down.getEmptyRow() + 1, down.getEmptyCol(), down.getEmptyRow(), down.getEmptyCol());
			State state = new State(down, this, this.gScore + 1, this.hScore);
			if (!(this.parent != null && this.parent.getBoard().getTiles().equals(down.getTiles()))) {
				states.add(state);
			}
		}

		return states;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public int getgScore() {
		return gScore;
	}

	public void setgScore(int gScore) {
		this.gScore = gScore;
	}

	public int gethScore() {
		return hScore;
	}

	public void sethScore(int hScore) {
		this.hScore = hScore;
	}

	public int getfScore() {
		return this.getgScore() + this.gethScore();
	}

	public void setfScore(int fScore) {
		this.fScore = fScore;
	}

}
