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
	private final int ident;

	/** políčko, na ktorej sa hlava nachádza */
	private TapeField field;

	/** počet kľúčov, ktoré vlastní */
	private int keys;

	/** smer, ktorým sa pozerá */
	private EDirection dir;

	/**
	 * Inicializuje hráča.
	 * 
	 * @param id identifikátor
	 * @param f  políčko, na ktoré má byť hlava umiestnená
	 */
	public TapeHead(final int ident, final TapeField field) {
		this.ident = ident;
		this.field = field;
		this.keys = 0;
		this.dir = EDirection.NORTH;
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
		case NORTH:
			setDir(EDirection.WEST);
			break;
		case WEST:
			setDir(EDirection.SOUTH);
			break;
		case SOUTH:
			setDir(EDirection.EAST);
			break;
		case EAST:
			setDir(EDirection.NORTH);
			break;
		}
	}

	/**
	 * Otočí hráča vpravo.
	 */
	public void right() {
		switch (getDir()) {
		case NORTH:
			setDir(EDirection.EAST);
			break;
		case EAST:
			setDir(EDirection.SOUTH);
			break;
		case SOUTH:
			setDir(EDirection.WEST);
			break;
		case WEST:
			setDir(EDirection.NORTH);
			break;
		}
	}

	public String showDir() {

		switch (dir) {
		case NORTH:
			return "^";
		case WEST:
			return "<";
		case SOUTH:
			return "v";
		case EAST:
			return ">";
		}
		throw new RuntimeException("direction is unknown");

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
		case NORTH:
			if (field.getX() == 0) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX() - 1, tmp.getY());
			tmp.setX(tmpX - 1);
			tmp.setY(tmpY);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case EAST:
			if (this.field.getTape().getColumnCount() == (field.getY() + 1)) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY() + 1);
			tmp.setX(tmpX);
			tmp.setY(tmpY + 1);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case SOUTH:
			if (this.field.getTape().getRowCount() == (field.getX() + 1)) {
				return null;
			}
			tmp = field.getTape().getArrFieldByCoord(tmp.getX() + 1, tmp.getY());
			tmp.setX(tmpX + 1);
			tmp.setY(tmpY);
			tmp.setObject(field.getTape().getArrFieldByCoord(tmp.getX(), tmp.getY()).getObject());
			tmp.setTape(field.getTape());
			return tmp;
		case WEST:
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

	public EDirection getDir() {
		return dir;
	}

	public void setDir(final EDirection dir) {
		this.dir = dir;
	}
}
