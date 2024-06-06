package luffy;

import java.util.Random;
import java.util.Scanner;

class gb {
	static char board[][];

	gb() {
		board = new char[3][3];
		inital();
	}

	void inital() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
	}

	static void placemark(int row, int col, char mark) {
		board[row][col] = mark;
	}

	boolean colwin() {
		for (int i = 0; i < 3; i++) {
			if (board[0][i] != ' ' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
				return true;
			}
		}
		return false;
	}

	boolean rowin() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] != ' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
				return true;
			}
		}
		return false;
	}

	boolean Crosewin() {
		char c1 = board[0][0];
		if (c1 != ' ' && c1 == board[1][1] && c1 == board[2][2])
			return true;
		char c2 = board[0][2];
		if (c2 != ' ' && c2 == board[1][1] && c2 == board[2][0])
			return true;
		return false;
	}

	boolean draw() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					return false;
				}
			}

		}
		return true;
	}

	void display() {
		System.out.println("--------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("--------------");
		}
	}
}

abstract class Player {
	String name;
	char mark;

	abstract void makeMove();

	boolean valiedMove(int row, int col) {
		if (gb.board[row][col] == ' ') {
			return true;
		}
		return false;
	}
}

class HumanPlayer extends Player {

	HumanPlayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Scanner scan = new Scanner(System.in);
		int row, col;
		do {
			System.out.println("Select the row & col");
			row = scan.nextInt();
			col = scan.nextInt();
		} while (!valiedMove(row, col));
		gb.placemark(row, col, mark);
	}

	boolean valiedMove(int row, int col) {
		if (gb.board[row][col] == ' ') {
			return true;
		}
		return false;
	}
}

class AIplayer extends Player {
	AIplayer(String name, char mark) {
		this.name = name;
		this.mark = mark;
	}

	void makeMove() {
		Random ran = new Random();
		int row, col;
		do {
			System.out.println("Select the row & col :");
			row = ran.nextInt(0, 3);
			col = ran.nextInt(0, 3);
		} while (!valiedMove(row, col));
		gb.placemark(row, col, mark);
	}

}

public class game {
	public static void main(String[] args) {
		gb obj = new gb();
		HumanPlayer p1 = new HumanPlayer("🙋 " + "Ranga", '❌');
		AIplayer p2 = new AIplayer("🖥️" + " AI", 'Ꮎ');
		Player cp;
		cp = p1;
		while (true) {
			obj.display();
			System.out.println(cp.name + " Your turn 🤚 :");
			cp.makeMove();
			if (obj.colwin() || obj.rowin() || obj.Crosewin()) {
				obj.display();
				System.out.println(cp.name + " Has Won 🤝 :");
				break;
			}
			if (obj.draw()) {
				obj.display();
				System.out.println("Draw the match :");
				break;
			}
			if (cp == p1) {
				cp = p2;
			} else {
				cp = p1;
			}
		}

	}
}
