import java.util.HashMap;

public class Board {

	int size;
	int[][] tiles;
	int emptyRow, emptyCol;

	public Board(int[][] tiles, int emptyRow, int emptyCol) {
		this.size = 8;
		this.tiles = tiles;

		this.emptyRow = emptyRow;
		this.emptyCol = emptyCol;
	}

	public Board copyBoard() {
		int[][] copy = new int[this.tiles.length][];
		for (int i = 0; i < this.tiles.length; i++) {
			copy[i] = this.tiles[i].clone();
		}
		return new Board(copy, this.emptyRow, this.emptyCol);
	}

	public void swap(int row1, int col1, int row2, int col2) {
		if (row2 != this.emptyRow && col2 != this.emptyCol) {
			System.out.println("Not switching with empty.");
			return;
		}

		int tmp1 = tiles[row1][col1];
		tiles[row1][col1] = tiles[row2][col2];
		tiles[row2][col2] = tmp1;
		this.emptyRow = row1;
		this.emptyCol = col1;
	}

	public boolean equals(Board otherBoard) {
		for (int row = 0; row < this.tiles.length; row++) {
			for (int col = 0; col < this.getTiles()[0].length; col++) {
				if (this.tiles[row][col] != otherBoard.getTiles()[row][col]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if (tiles[y][x] == 0)
					s.append(" ");
				else
					s.append(Integer.toString(tiles[y][x]));
				if (x < 2)
					s.append(" ");
			}
			s.append("\n");
		}
		return s.toString();
	}

	public boolean isSolvable(int goal[]) {
		int[] puzzle = Driver.toLinearArray(this.tiles);

		// Set the goal indexes up using a hashmap.
		//      Value, Index
		HashMap<Integer, Integer> goalMap = new HashMap<Integer, Integer>();

		for (int i = 0; i < goal.length; i++) {
			goalMap.put(goal[i], i);
		}

		// Check for inversions.
		int inversion = 0;
		for (int i = 0; i < goalMap.size(); i++) {
			for (int j = i+1; j < goalMap.size(); j++) {
				if (goalMap.get(puzzle[i]) < goalMap.get(puzzle[j])) {
					inversion++;
				}
			}
		}
		
		return (inversion % 2 == 0);
	}

	public String convertWithIteration(HashMap<Integer, ?> map) {
		StringBuilder mapAsString = new StringBuilder("{");
		for (Integer key : map.keySet()) {
			mapAsString.append(key + "=" + map.get(key) + ", ");
		}
		mapAsString.delete(mapAsString.length() - 2, mapAsString.length()).append("}");
		return mapAsString.toString();
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int[][] getTiles() {
		return tiles;
	}

	public void setTiles(int[][] tiles) {
		this.tiles = tiles;
	}

	public int getEmptyRow() {
		return emptyRow;
	}

	public void setEmptyRow(int emptyRow) {
		this.emptyRow = emptyRow;
	}

	public int getEmptyCol() {
		return emptyCol;
	}

	public void setEmptyCol(int emptyCol) {
		this.emptyCol = emptyCol;
	}

}
