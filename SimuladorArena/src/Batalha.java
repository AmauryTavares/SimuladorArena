import java.util.ArrayList;
import java.util.Random;

public class Batalha extends Thread{
	
	int arena;
	ArrayList<DadosPersonagem> listaPersonagens;
	
	Random rand = new Random();
	
	public Batalha () {
		arena = Arena.sortearArena();
		listaPersonagens = new ArrayList<>();
	}
	
	@Override
	public void run() {

	}
}
