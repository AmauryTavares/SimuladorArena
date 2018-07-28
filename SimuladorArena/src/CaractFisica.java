import java.util.Random;

public enum CaractFisica {
	AGIL(1), MEDIANO(2), FORTE(3);
	
	int valorCaractFisica;
	
	public int getValorCaractFisica() {
		return valorCaractFisica;
	}

	private CaractFisica (int valor) {
		this.valorCaractFisica = valor;
	}
	
	static public int sortearCaractFisica() {
		Random rand = new Random();
		return (rand.nextInt(3) + 1);
	}
}
