package maze.objects;

public enum EObject {
	
	WALL("w"), 
	GATE("g"), 
	OPENED_GATE("o"),
	PLACE("p"),
	KEY("k"), 
	FINISH("f");
	
	String symbol;
	
	EObject(final String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public static EObject valueOfSymbol(final String symbol) {
		
		switch (symbol.toLowerCase()) {
		case "w":
			return WALL;
		case "g":
			return GATE;
		case "p":
			return PLACE;
		case "k":
			return KEY;
		case "f":
			return FINISH;
		}
		throw new IllegalArgumentException(String.format("The symbol %s does not represent any konstant", symbol));
		
	}
}
