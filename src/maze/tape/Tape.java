package maze.tape;

import java.util.ArrayList;

import maze.objects.EObject;

/**
 * Trieda vytvorí pásku s objektami, hlavy a vypíše na výstup podobu bludiska.
 * 
 * @author Ľuboš Tichý
 */
public class Tape {

	/** 2D páska pre objekty */
//	private final TapeField[][] arrField;
	private final ArrayList<ArrayList<TapeField>> arrField;

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
		this.arrField = new ArrayList<ArrayList<TapeField>>();// new TapeField[rowCount][columnCount];
		this.headCount = 0;

		int x = 0;
		int y = 0;
		String symbol;
		// final ArrayList<ArrayList<TapeField>> cols = new ArrayList<>();
		ArrayList<TapeField> rows = new ArrayList<TapeField>();
		for (int k = 0; format.length() > k; k++) {

			if (x == columnCount) {
				y++;
				this.arrField.add(rows);
				x = 0;
				rows = new ArrayList<TapeField>();
			}

			symbol = format.substring(k, k + 1);

			try {
				final EObject objectType = EObject.valueOfSymbol(symbol);
				x++;
				rows.add(new TapeField(this, x, y, objectType));
			} catch (final IllegalArgumentException e) {
				throw new RuntimeException("Wrong symbol in the maze");
			}

		}

		this.arrField.add(rows);

	}

	/**
	 * Vytvorí hráča(hlavu).
	 * 
	 * @param i ID hráča
	 * @return novú hlavu alebo null
	 */
	public TapeHead createHead(final int i) {
		for (int y = 0; y < getRowCount(); y++) {
			for (int x = 0; x < getColumnCount(); x++) {
				if (arrField.get(x).get(y).canSeize()) {
					arrField.get(x).get(y).setHead(true);
					return new TapeHead(i, arrField.get(x).get(y));
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
		for (int y = 0; y < getRowCount(); y++) {
			for (int x = 0; x < getColumnCount(); x++) {
				if (arrField.get(x).get(y).isHead()) { // je na ňom hráč
					System.out.print(h.showDir() + " ");
				} else {
					System.out.print(arrField.get(x).get(y).getObject().show() + " "); // voľné políčko
				}
			}
			System.out.println();
		}
	}

	public void show() {
		for (int y = 0; y < getRowCount(); y++) {
			for (int x = 0; x < getColumnCount(); x++) {
				System.out.print(arrField.get(x).get(y).getObject().show() + " "); // voľné políčko
			}
			System.out.println();
		}
	}

	public TapeField getArrFieldByCoord(final int x, final int y) {
		return arrField.get(x).get(y);
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getRowCount() {
		return rowCount;
	}

}
