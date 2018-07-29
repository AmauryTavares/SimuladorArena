import java.util.Random;

public enum Raca {
	OGRO(1), ELFO(2), ANAO(3), ASSASSINO(4), VAMPIRO(5),
	MORTO_VIVO(6), MAGO(7), PALADINO(8), DRACONIANA(9);
	
	int valorRaca;
	
	public int getValorRaca() {
		return valorRaca;
	}

	private Raca (int valor) {
		this.valorRaca = valor;
	}
	
	static public int sortearRaca() {
		Random rand = new Random();
		return (rand.nextInt(9) + 1);
	}
}
