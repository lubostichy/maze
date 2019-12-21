package maze.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

import maze.tape.Tape;
import maze.tape.TapeHead;

public class Service {

	private static final String BAD_EXPRESSION_TEXT = "Bad expression, please try again";
	private static final String TRUE_TEXT = "True";
	private static final String FALSE_TEXT = "False";

	public void playGame() {
		try (Scanner sc = new Scanner(System.in)) {
			Tape t1 = null;
			TapeHead h1 = null;
			String input;

			while (true) {
				System.out.println("Set a game:");
				input = sc.nextLine();
				if (input.toUpperCase().startsWith(ECommand.GAME.toString()) && input.length() <= 5) {

					t1 = file(input.substring(5));

					if (t1 != null) {
						h1 = t1.createHead(0);
						break;
					}
				}
				System.out.println(BAD_EXPRESSION_TEXT);
			}
			
			Objects.requireNonNull(t1);
			Objects.requireNonNull(h1);

			System.out.println("The game begins...");

			while (true) {
				System.out.println("command:");

				input = sc.nextLine();

				ECommand command;
				try {
					command = ECommand.valueOf(input.toUpperCase());
				} catch (IllegalArgumentException e) {
					System.out.println(BAD_EXPRESSION_TEXT);
					continue;
				}
				switch (command) {
				case SHOW:
					t1.show(h1);
					break;
				case CLOSE:
					sc.close();
					break;
				case STEP:
					if (h1.step()) {
						System.out.println(TRUE_TEXT);
					} else {
						System.out.println(FALSE_TEXT);
					}
					break;
				case LEFT:
					h1.left();
					System.out.println(TRUE_TEXT);
					break;
				case RIGHT:
					h1.right();
					System.out.println(TRUE_TEXT);
					break;
				case TAKE:
					if (h1.take()) {
						System.out.println(TRUE_TEXT);
					} else {
						System.out.println(FALSE_TEXT);
					}
					break;
				case OPEN:
					if (h1.open()) {
						System.out.println(TRUE_TEXT);
					} else {
						System.out.println(FALSE_TEXT);
					}
					break;
				case KEYS:
					System.out.println("Keys: " + h1.keys());
					break;
				default:
					break;				
				}
			}

		} 

	}

	/**
	 * Metóda načíta bludisko z textového súboru a vracia vytvorenú pásku.
	 * 
	 * @param name názov bludiska
	 * @return vracia vytvorenú pásku(bludisko)
	 */
	public static Tape file(String name) {
		Tape tmp = null;
		int row = 0;
		int col = 0;

		Path currentRelativePath = Paths.get("");
		String s = currentRelativePath.toAbsolutePath().toString();

		try {
			FileInputStream fstream = new FileInputStream(s + "/examples/" + name);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String whole = "";

			while ((strLine = br.readLine()) != null) {
				if (strLine.matches(".*\\d.*")) {
					String[] parts = strLine.split(" ");

					row = Integer.parseInt(parts[0]);
					col = Integer.parseInt(parts[1]);

					continue;
				}

				whole = whole.concat(strLine);
				whole = whole.replaceAll("\\s+", "");
			}

			tmp = new Tape(row, col, 0, whole);

			in.close();
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());

		}
		return tmp;
	}
}
