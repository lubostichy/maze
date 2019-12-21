package maze.tape;

/**
 * Trieda vytvorí pásku s objektami, hlavy a vypíše na výstup podobu bludiska.
 * 
 * @author Ľuboš Tichý
 */
public class Tape {

	/** 2D páska pre objekty */
	private final TapeField[][] arrField;

	/** riadok pásky */
	private final int rowCount;

	/** stĺpec pásky */
	private final int columnCount;

	/** počet hláv */
	private final int headCount;

	/** reťazec objektov */
	private String whole;

	/**
	 * Vytvorí 2D pole políčok.
	 * 
	 * @param r      riadky
	 * @param c      stĺpce
	 * @param h      počet hráčov
	 * @param format reťazec objektov, ktoré sa vytvoria
	 */
	public Tape(final int rowCount, final int columnCount, final String format) {
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
				final EObject objectType = EObject.valueOfSymbol(symbol);
				this.arrField[i][j] = new TapeField(this, i, j, objectType);
			} catch (final IllegalArgumentException e) {
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
	public TapeHead createHead(final int i) {
		// int j=0;
		for (int x = 0; x < this.getColumnCount(); x++) {
			for (int y = 0; y < this.getRowCount(); y++) {
				if (arrField[x][y].canSeize()) {
					arrField[x][y].setHead(true);
					return new TapeHead(i, arrField[x][y]);
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
	public void show(final TapeHead h) {
		for (int i = 0; i < getColumnCount(); i++) {
			for (int j = 0; j < getRowCount(); j++) {
				if (arrField[i][j].getObject() != null) // ak je voľné políčko
				{
					if (arrField[i][j].isHead()) // je na ňom hráč
					{
						switch (h.getDir()) {
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
						System.out.print(arrField[i][j].getObject().show() + " "); // voľné políčko
					}
				} else { // ak je políčko s objektom
					if (arrField[i][j].isHead()) // je na ňom hráč
					{
						switch (h.getDir()) {
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

	public TapeField getArrFieldByCoord(final int x, final int y) {
		return arrField[x][y];
	}


	public int getColumnCount() {
		return columnCount;
	}

	public int getRowCount() {
		return rowCount;
	}

}
