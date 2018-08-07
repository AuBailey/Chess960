package Chess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
		List<Character> pieces = new ArrayList<>(Arrays.asList('R', 'N', 'B', 'K', 'Q', 'B', 'N', 'R'));
		String shuffled = "";
		do {
			shuffled = "";
			Collections.shuffle(pieces);
			for(int i=0;i<pieces.size();i++) {
				shuffled+=pieces.get(i);
			}
		}while(!valid(shuffled));
		placePieces(shuffled);
	}

	private void placePieces(String shuffled) {
		for(int i=0;i<shuffled.length();i++) {
			switch(shuffled.charAt(i)) {
			case 'R':
				ChessPiece r1 = new Rook("player1", new ChessLocation(0,i), this);
				ChessPiece r2 = new Rook("player2", new ChessLocation(7, i), this);
				break;
			case 'N':
				ChessPiece n1 = new Knight("player1", new ChessLocation(0, i), this);
				ChessPiece n2 = new Knight("player2", new ChessLocation(7, i), this);
				break;
			case 'B':
				ChessPiece b1 = new Bishop("player1", new ChessLocation(0,i), this);
				ChessPiece b2 = new Bishop("player2", new ChessLocation(7,i), this);
				break;
			case 'K':
				player1King = new King("player1", new ChessLocation(0,i), this);
				player2King = new King("player2", new ChessLocation(7,i), this);
				break;
			case 'Q':
				ChessPiece q1 = new Queen("player1", new ChessLocation(0,i), this);
				ChessPiece q2 = new Queen("player2", new ChessLocation(7,i), this);
				break;
			}
		}
	}

	public boolean valid(String shuffled) {
		if(shuffled.isEmpty()) {
			return false;
		}
		boolean valid = true;
		int r1Index = -1;
		int kIndex = -1;
		int b1Index = -1;
		loop: for (int i = 0; i < shuffled.length(); i++) {
			switch (shuffled.charAt(i)) {
			case 'R':
				if (r1Index == -1) {
					r1Index = i;
				} else {
					if (kIndex == -1) {//if one knight has been placed makes sure that the king has been placed before the second knight
						valid = false;
						break loop;
					}
				}
				break;
			case 'K':
				if (r1Index == -1) {//makes sure the first knight has been placed
					valid = false;
					break loop;
				} else {
					kIndex = i;
				}
				break;
			case 'B':
				if (b1Index == -1) {
					b1Index = i;
				} else {
					if (b1Index % 2 == i % 2) {//makes sure both bishops are on different color squares only after at least on has been placed
						valid = false;
						break loop;
					}
				}
				break;
			}
		}
		return valid;
	}

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
