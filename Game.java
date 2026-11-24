public class Game {
    private MagicSquareBoard board;
    private ConsoleUI ui;
    private boolean isRunning;

    public Game() {
        this.board = new MagicSquareBoard();
        this.ui = new ConsoleUI();
        this.isRunning = true;
    }

    public void start() {
        while (isRunning) {
            board.reset();
            while (!board.isFull()) {
                playTurn();
            }
            handleGameEnd();
        }
    }

    private void playTurn() {
        ui.printHeaders();
        ui.printBoard(board);

        int row = ui.askInt("Masukkan baris (1-3) : ");
        if (!board.isValidPosition(row)) {
            ui.pauseWithMessage("Baris tidak valid! Tekan Enter untuk lanjut...");
            return;
        }

        int col = ui.askInt("Masukkan kolom (1-3) : ");
        if (!board.isValidPosition(col)) {
            ui.pauseWithMessage("Kolom tidak valid! Tekan Enter untuk lanjut...");
            return;
        }

        int num = ui.askInt("Masukkan angka (1-9) : ");
        if (!board.isValidNumber(num)) {
            ui.pauseWithMessage("Angka harus 1-9! Tekan Enter untuk lanjut...");
            return;
        }

        board.placeNumber(row, col, num);
    }

    private void handleGameEnd() {
        ui.printHeaders();
        ui.printBoard(board);
        ui.printResult(board.getSums(), board.isMagicSquare());
        isRunning = ui.askPlayAgain();
    }
}