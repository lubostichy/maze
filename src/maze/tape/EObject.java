package maze.tape;

public enum EObject {
	
	WALL("w"), 
	GATE("g"), 
	PLAYER("p"),
	KEY("k"), 
	FINISH("f");
	
	String symbol;
	
	EObject(String symbol) {
		this.symbol = symbol;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
	public static EObject valueOfSymbol(String symbol) {
		
		switch (symbol.toLowerCase()) {
		case "w":
			return WALL;
		case "g":
			return GATE;
		case "p":
			return PLAYER;
		case "k":
			return KEY;
		case "f":
			return FINISH;
		}
		throw new IllegalArgumentException(String.format("The symbol %s does not represent any konstant", symbol));
		
	}
}
