import java.util.ArrayList;
import java.util.SplittableRandom;
import java.util.Stack;

public class Driver {

	public static void main(String[] args) {
		//doMyOwnWork();
		doClassWork();

	}
	
	public static void doMyOwnWork() {
		Board goal = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }, 2, 2);
		Board random = Driver.generateRandomBoard();
		while (!random.isSolvable(Driver.toLinearArray(goal.getTiles()))) {
			random = Driver.generateRandomBoard();
		}
		
		System.out.println(random.toString());
		
		solve(random, goal, "Cool!");
	}

	public static void doClassWork() {
		Board puzzle1Start = new Board(new int[][] { { 0, 1, 3 }, { 4, 2, 5 }, { 7, 8, 6 } }, 0, 0);

		Board puzzle1Goal = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }, 2, 2);

		solve(puzzle1Start, puzzle1Goal, "1");

		Board puzzle2Start = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 8, 7, 0 } }, 2, 2);
		Board puzzle2Goal = new Board(new int[][] { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 0 } }, 2, 2);

		solve(puzzle2Start, puzzle2Goal, "2");

		Board puzzle3Start = new Board(new int[][] { { 2, 8, 1 }, { 4, 6, 3 }, { 0, 7, 5 } }, 2, 0);

		Board puzzle3Goal = new Board(new int[][] { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } }, 1, 1);

		solve(puzzle3Start, puzzle3Goal, "3");
	}

	public static void solve(Board start, Board goal, String startMessageModifier) {
		if (start.isSolvable(Driver.toLinearArray(goal.getTiles()))) {
			System.out.println("Solving starting for puzzle: " + startMessageModifier + ". ManhattanHeuristic...");
			long startTime = System.currentTimeMillis();
			Solver solver = new Solver(start, goal, new ManhattanHeuristic(goal));
			Stack<State> solvedStack = solver.solve();
			System.out.println("Solved! Moves taken: " + (solvedStack.size() - 1) + ". Time taken: "
					+ (System.currentTimeMillis() - startTime) + "m/s");

			int stackSize = solvedStack.size();
			for (int i = 0; i < stackSize; i++) {
				System.out.println(solvedStack.pop().getBoard().toString());
			}

			System.out.println("Solving starting for puzzle: " + startMessageModifier + ". HammingHeuristic...");
			startTime = System.currentTimeMillis();
			solver = new Solver(start, goal, new HammingHeuristic(goal));
			solvedStack = solver.solve();
			System.out.println("Solved! Moves taken: " + (solvedStack.size() - 1) + ". Time taken: "
					+ (System.currentTimeMillis() - startTime) + "m/s");

			stackSize = solvedStack.size();
			for (int i = 0; i < stackSize; i++) {
				System.out.println(solvedStack.pop().getBoard().toString());
			}
		} else {
			System.out.println("Puzzle " + startMessageModifier + " is not solvable.\n");
		}

	}

	public static Board generateRandomBoard() {
		SplittableRandom random = new SplittableRandom();

		int[][] tiles = new int[3][3];
		int emptyRow = -1;
		int emptyCol = -1;
		ArrayList<Integer> takenNumbers = new ArrayList<Integer>();

		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				int choice = random.nextInt(0, 9);
				while (takenNumbers.contains(choice)) {
					choice = random.nextInt(0, 9);
				}
				takenNumbers.add(choice);
				tiles[x][y] = choice;

				if (choice == 0) {
					emptyRow = y;
					emptyCol = x;
				}
			}
		}

		return new Board(tiles, emptyRow, emptyCol);

	}

	public static int[] toLinearArray(int[][] tiles) {
		int[] returnValue = new int[8];
		int counter = 0;
		for (int row = 0; row < 3; row++) {
			for (int col = 0; col < 3; col++) {
				if (tiles[row][col] != 0) {
					returnValue[counter] = tiles[row][col];
					counter++;
				}
			}
		}
		return returnValue;
	}
}
