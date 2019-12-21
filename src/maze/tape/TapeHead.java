package maze.tape;

import maze.objects.Gate;
import maze.objects.Key;

/**
 * Trieda definuje správanie sa hlavy(hráča) ako sú pohyby, zber kľúčov,
 * otvorenie brány..
 * 
 * @author Ľuboš Tichý
 */
public class TapeHead {

	/** identifikátor hlavy */
	private int ident = 0;

	/** políčko, na ktorej sa hlava nachádza */
	private TapeField field = null;

	/** počet kľúčov, ktoré vlastní */
	private int keys = 0;

	/** smer, ktorým sa pozerá */
	private String dir = "north";

	/**
	 * Inicializuje hráča.
	 * 
	 * @param id identifikátor
	 * @param f  políčko, na ktoré má byť hlava umiestnená
	 */
	public TapeHead(final int id, final TapeField f) {
		ident = id;
		field = f;
	}

	/**
	 * Pridá kľúč.
	 */
	public void addKey() {
		keys++;
	}

	/**
	 * Vráti počet klúčov.
	 * 
	 * @return počet kľúčov
	 */
	public int keys() {
		return keys;
	}

	/**
	 * Otočí hráča vľavo.
	 */
	public void left() {
		switch (getDir()) {
		case "north":
			setDir("west");
			break;
		case "west":
			setDir("south");
			break;
		case "south":
			setDir("east");
			break;
		case "east":
			setDir("north");
			break;
		}
	}

	/**
	 * Otočí hráča vpravo.
	 */
	public void right() {
		switch (getDir()) {
		case "north":
			setDir("east");
			break;
		case "east":
			setDir("south");
			break;
		case "south":
			setDir("west");
			break;
		case "west":
			setDir("north");
			break;
		}
	}

	/**
	 * Zistí, aké políčko je pred objektom.
	 * 
	 * @return vráti políčko pred objektom
	 */
	public TapeField frontField() {

		/** pomocné políčko */
		TapeField tmp = field;

		/** index pomocného políčka */
		final int tmpX = field.getX();

		/** index pomocného políčka */
		final int tmpY = field.getY();

		switch (getDir()) {
		case "north":
			if (field.getX() == 0) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX() - 1, tmp.getY());
			tmp.setX(tmpX - 1);
			tmp.setY(tmpY);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case "east":
			if (this.field.getTape().getColumnCount() == (field.getY() + 1)) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY() + 1);
			tmp.setX(tmpX);
			tmp.setY(tmpY + 1);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case "south":
			if (this.field.getTape().getRowCount() == (field.getX() + 1)) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX() + 1, tmp.getY());
			tmp.setX(tmpX + 1);
			tmp.setY(tmpY);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case "west":
			if (this.field.getY() == 0) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY() - 1);
			tmp.setX(tmpX);
			tmp.setY(tmpY - 1);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		default:
			return null;
		}
	}

	/**
	 * Hráč zoberie klúč.
	 * 
	 * @return ak je pred ním klúč vracia true inak false
	 */
	public boolean take() {
		final TapeField tmp = frontField();
		if (tmp.getObject() instanceof Key) {
			field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).setObject(null);
			keys++;
			return true;
		}
		return false;
	}

	/**
	 * Otvorí bránu.
	 * 
	 * @return ak je pred hráčom brána a hráč má klúč, tak vracia true, inak false
	 */
	public boolean open() {
		final TapeField tmp = frontField();
		if (tmp.getObject() instanceof Gate) {
			if (tmp.getObject().canBeOpen()) {
				if (tmp.getObject().open()) {
					keys--;
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Krok dopredu smerom, ktorým je hráč otočený.
	 * 
	 * @return ak je krok možný, tak vracia true, inak false
	 */
	public boolean step() {
		final TapeField tmp = frontField();
		if (tmp != null) {
			if (tmp.canSeize()) {
				this.field.getTape().getArrFieldByCoord(field.getX(), field.getY()).setHead(false);
				this.field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).setHead(true);
				this.field = tmp;
				return true;
			}
		}
		return false;
	}

	public String getDir() {
		return dir;
	}

	public void setDir(final String dir) {
		this.dir = dir;
	}
}
