import java.util.Random;

public enum Arena {
	CAMPO_GLACIAL(1), DESERTO(2), SELVA(3), PANTANO(4);
	
	int valorArena;
	
	public int getValorArena() {
		return valorArena;
	}

	private Arena (int valor) {
		this.valorArena = valor;
	}
	
	static public int sortearArena() {
		Random rand = new Random();
		return (rand.nextInt(4) + 1);
	}
}
