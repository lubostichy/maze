package maze.tape;

/**
 * Trieda vytvorí pásku s objektami, hlavy a vypíše na výstup podobu bludiska.
 * 
 * @author Ľuboš Tichý
 */
public class Tape {

	/** 2D páska pre objekty */
	public TapeField[][] arrField;

	/** riadok pásky */
	protected int rowCount;

	/** stĺpec pásky */
	protected int columnCount;

	/** počet hláv */
	protected int headCount;

	/** reťazec objektov */
	protected String whole;

	/**
	 * Vytvorí 2D pole políčok.
	 * 
	 * @param r      riadky
	 * @param c      stĺpce
	 * @param h      počet hráčov
	 * @param format reťazec objektov, ktoré sa vytvoria
	 */
	public Tape(int rowCount, int columnCount, String format) {
		this.rowCount = rowCount;
		this.columnCount = columnCount;
		this.arrField = new TapeField[rowCount][columnCount];
		this.headCount = 0;

		int j = 0;
		int i = 0;
		String symbol;
		for (int k = 0; format.length() > k; k++) {

			if (j == columnCount) {
				j = 0;
				i++;
			}

			symbol = format.substring(k, k + 1);

			try {
				EObject objectType = EObject.valueOfSymbol(symbol);
				this.arrField[i][j] = new TapeField(this, i, j, objectType);
			} catch (IllegalArgumentException e) {
				throw new RuntimeException("Wrong symbol in the maze");
			}

		}
	}

	/**
	 * Vytvorí hráča(hlavu).
	 * 
	 * @param i ID hráča
	 * @return novú hlavu alebo null
	 */
	public TapeHead createHead(int i) {
		// int j=0;
		for (int x = 0; x < this.columnCount; x++) {
			for (int y = 0; y < this.rowCount; y++) {
				if (arrField[x][y].canSeize()) {
					arrField[x][y].isHead = true;
					TapeHead tmp = new TapeHead(i, arrField[x][y]);
					return tmp;
				}

			}
		}
		return null;
	}

	/**
	 * Vypíše na výstup bludisko aj s hráčmi.
	 * 
	 * @param h hlava
	 */
	public void show(TapeHead h) {
		for (int i = 0; i < columnCount; i++) {
			for (int j = 0; j < rowCount; j++) {
				if (arrField[i][j].object != null) // ak je voľné políčko
				{
					if (arrField[i][j].isHead) // je na ňom hráč
					{
						switch (h.dir) {
						case "north":
							System.out.print("^");
							break;
						case "west":
							System.out.print("<");
							break;
						case "south":
							System.out.print("v");
							break;
						case "east":
							System.out.print(">");
							break;
						}
					} else {
						System.out.print(arrField[i][j].object.show() + " "); // voľné políčko
					}
				} else { // ak je políčko s objektom
					if (arrField[i][j].isHead) // je na ňom hráč
					{
						switch (h.dir) {
						case "north":
							System.out.print("^ ");
							break;
						case "west":
							System.out.print("< ");
							break;
						case "south":
							System.out.print("v ");
							break;
						case "east":
							System.out.print("> ");
							break;
						}
					} else {
						System.out.print("P ");
					}
				}
			}
			System.out.println();
		}
	}

}
