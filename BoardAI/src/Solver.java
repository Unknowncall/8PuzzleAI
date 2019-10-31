import java.util.ArrayList;
import java.util.Stack;

public class Solver {

	private Board startBoard, goalBoard;

	// A Heuristic evaluator
	private Heuristic heuristic;

	// Important stats to compare our heuristics
	int discovered, explored, cost;

	public Solver(Board start, Board goal, Heuristic heuristic) {
		this.startBoard = start;
		this.goalBoard = goal;
		this.heuristic = heuristic;
		discovered = 0;
		explored = 0;
		cost = 0;
	}

	public Stack<State> solve() {
		// A* ALGORITHM:

		ArrayList<State> openSet = new ArrayList<State>();
		ArrayList<State> closedSet = new ArrayList<State>();

		State start = new State(this.startBoard, null, 0, this.heuristic.evaluate(this.startBoard));

		openSet.add(start);
		discovered++;

		while (!openSet.isEmpty()) {

			State currentState = null;

			for (State state : openSet) {
				if (currentState == null) {
					currentState = state;
				} else if (currentState.getfScore() > state.getfScore()) {
					currentState = state;
				}
			}

			openSet.remove(currentState);

			if (currentState.board.equals(goalBoard)) {
				cost = currentState.gScore;
				return constructPath(currentState);
			}

			closedSet.add(currentState);
			explored++;

			ArrayList<State> neighbors = currentState.getNeighbors();
			for (State neighbor : neighbors) {
				if (closedSet.contains(neighbor)) {
					continue;
				}

				neighbor.setParent(currentState);
				neighbor.setgScore(currentState.getgScore() + 1);

				neighbor.sethScore(heuristic.evaluate(neighbor.board));
				neighbor.setfScore(neighbor.getgScore() + neighbor.gethScore());

				openSet.add(neighbor);
				discovered++;
			}
		}

		return null;
	}

	public Stack<State> constructPath(State state) {
		Stack<State> stack = new Stack<State>();
		State current = state;

		while (current != null) {
			stack.push(current);
			current = current.parent;
		}

		cost = stack.size() - 1;
		return stack;
	}

}
