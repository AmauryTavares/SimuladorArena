import java.util.Random;

public enum Personalidade {
	CORAJOSO(1), MEDROSO(2), ESTRATEGISTA(3), ESPERTO(4), INDECISO(5),
	AMBICIOSO(6), DEFENSOR(7), ADPTADOR(8);
	
	int valorPersonalidade;
	
	public int getValorPersonalidade() {
		return valorPersonalidade;
	}

	private Personalidade (int valor) {
		this.valorPersonalidade = valor;
	}
	
	static public int sortearPersonalidade() {
		Random rand = new Random();
		return (rand.nextInt(8) + 1);
	}
}
