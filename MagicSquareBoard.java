public class MagicSquareBoard {
    private static final int SIZE = 3;
    private static final int TARGET = 15;
    private int[][] grid;

    public MagicSquareBoard() {
        this.grid = new int[SIZE][SIZE];
    }

    public boolean isValidPosition(int pos) {
        return pos >= 1 && pos <= SIZE;
    }

    public boolean isValidNumber(int num) {
        return num >= 1 && num <= 9;
    }

    public void placeNumber(int row, int column, int num) {
        if (!isValidPosition(row) || !isValidPosition(column) || !isValidNumber(num)) {
            return;
        }

        removeExistingNumber(num);
        grid[row - 1][column - 1] = num;
    }

    private void removeExistingNumber(int num) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == num) {
                    grid[i][j] = 0;
                }
            }
        }
    }

    public int getCell(int row, int column) {
        return grid[row][column];
    }

    public int getSize() {
        return SIZE;
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public int[] getSums() {
        int[] sums = new int[8];

        // Horizontal (rows)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                sums[i] += grid[i][j];
            }
        }

        // Vertical (columns)
        for (int j = 0; j < SIZE; j++) {
            for (int i = 0; i < SIZE; i++) {
                sums[j + SIZE] += grid[i][j];
            }
        }

        // Diagonal \
        for (int i = 0; i < SIZE; i++) {
            sums[6] += grid[i][i];
        }

        // Diagonal /
        for (int i = 0; i < SIZE; i++) {
            sums[7] += grid[SIZE - 1 - i][i];
        }

        return sums;
    }

    public boolean isMagicSquare() {
        for (int sum : getSums()) {
            if (sum != TARGET) {
                return false;
            }
        }
        return true;
    }

    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }
    }
}