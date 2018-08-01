package Chess;

import GameEntities.*;

/**
 * The ChessGame class is used for holding chess objects which contains the
 * board, pieces and handles alternating turns.
 */
public class ChessGame {

	private ChessBoard chessBoard;
	private King player1King;
	private King player2King;

	/**
	 * Create new instances of necessary properties.
	 */
	public ChessGame(boolean standard) {
		chessBoard = new ChessBoard();
		if (standard) {
			setupTeam(0, "player1");
			setupTeam(7, "player2");
		} else {
			make960Board();
		}
	}

	private void make960Board() {
		placePawns();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 2; j++) {

			}
		}
	}
	
	//places pawns for both players
	private void placePawns() {
		for (int i = 0; i < 8; i++) {
			ChessPiece p = new Pawn("Player1", new ChessLocation(1, i), this);
		}
		for (int i = 0; i < 8; i++) {
			ChessPiece p = new Pawn("Player2", new ChessLocation(6, i), this);
		}
	}

	/**
	 * Sets up pieces for each player.
	 * 
	 * @param side
	 *            Starting side of the player
	 * @param player
	 *            String of the player
	 */
	private void setupTeam(int side, String player) {
		int one = (side > 0) ? -1 : 1;
		int colIncerment = 0;

		// Rook
		ChessPiece r1 = new Rook(player, new ChessLocation(side, colIncerment), this);
		ChessPiece r2 = new Rook(player, new ChessLocation(side, 7 - colIncerment), this);
		colIncerment += 1;

		// Knight
		ChessPiece n1 = new Knight(player, new ChessLocation(side, colIncerment), this);
		ChessPiece n2 = new Knight(player, new ChessLocation(side, 7 - colIncerment), this);
		colIncerment += 1;

		// Bishop
		ChessPiece b1 = new Bishop(player, new ChessLocation(side, colIncerment), this);
		ChessPiece b2 = new Bishop(player, new ChessLocation(side, 7 - colIncerment), this);
		colIncerment += 1;

		// King & Queen
		if (player.equalsIgnoreCase("player1")) {
			player1King = new King(player, new ChessLocation(side, colIncerment), this);
		} else {
			player2King = new King(player, new ChessLocation(side, colIncerment), this);
		}

		ChessPiece q = new Queen(player, new ChessLocation(side, 7 - colIncerment), this);

		// Pawns
		for (int i = 0; i < 8; i++) {
			ChessPiece p = new Pawn(player, new ChessLocation(side + one, i), this);
		}
	}

	/**
	 * Returns the ChessBoard.
	 * 
	 * @return The board object of the chess game.
	 */
	public ChessBoard getChessBoard() {
		return chessBoard;
	}

	public King getPlayer1King() {
		return player1King;
	}

	public King getPlayer2King() {
		return player2King;
	}
}
