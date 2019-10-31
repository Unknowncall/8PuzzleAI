import java.awt.Point;

abstract class Heuristic {

	Board goal;

	public abstract int evaluate(Board board);

}

class HammingHeuristic extends Heuristic {

	public HammingHeuristic(Board goalBoard) {
		this.goal = goalBoard;
	}

	@Override
	public int evaluate(Board board) {

		int tiles = 0;

		for (int row = 0; row < board.getTiles().length; row++) {
			for (int col = 0; col < board.getTiles()[0].length; col++) {
				if (goal.getTiles()[row][col] != board.getTiles()[row][col]) {
					tiles++;
				}
			}
		}

		return tiles;
	}

}

class ManhattanHeuristic extends Heuristic {

	public ManhattanHeuristic(Board goalBoard) {
		this.goal = goalBoard;
	}

	@Override
	public int evaluate(Board board) {

		Point[] correctPoints = new Point[9];

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				correctPoints[goal.getTiles()[row][col]] = new Point(row, col);
			}
		} // Store the points for each item in the board.

		int moves = 0;

		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (correctPoints[board.getTiles()[row][col]].getX() != col
						|| correctPoints[board.getTiles()[row][col]].getY() != row) {
					moves += manhattanDistance(row, col, ((int) (correctPoints[board.getTiles()[row][col]].getX())),
							((int) (correctPoints[board.getTiles()[row][col]].getY())));
				}
			}
		}

		return moves;
	}

	private int manhattanDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}

class ZeroHeuristic extends Heuristic {
	public ZeroHeuristic(Board goalBoard) {
		this.goal = goalBoard;
	}

	@Override
	public int evaluate(Board board) {
		return 0;
	}
}
