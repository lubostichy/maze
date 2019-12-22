package maze;

import maze.core.Service;

/**
 * Trieda obsahuje spracovanie príkazov zadaných z príkazového riadka a
 * načítanie bludiska zo suboru.
 * 
 * @author Ľuboš Tichý
 */
public class Main {
	/**
	 * Spracováva vstup, na základe vstupu rozhoduje o ďalších akciách.
	 * 
	 * @param args argumenty
	 */
	public static void main(String[] args) {
		System.out.println(args[0]);
		Service service = new Service();
		service.playGame();
	}

}
