import java.util.Random;

public class Main {
	public static void main(String[] args) {
		Random rand = new Random();
		
		DadosPersonagem personagem = new DadosPersonagem("Amaury", (rand.nextInt(4) + 1));
		System.out.println(personagem.toString());
		
		
	}
}
