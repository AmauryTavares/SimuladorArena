import java.util.ArrayList;
import java.util.Random;

public class Batalha extends Thread{
	
	int arena;
	ArrayList<DadosPersonagem> listaDadosPersonagens;
	ArrayList<Personagem> listaPersonagens;
	
	Random rand = new Random();
	
	public Batalha (int qtd) {
		arena = Arena.sortearArena();
		listaDadosPersonagens = new ArrayList<>();
		listaPersonagens = new ArrayList<>();
		criarLista(qtd);
	}
	
	private void criarLista(int tamanho) {
		for (int i = 0; i < tamanho; i++) {
			DadosPersonagem pDados = new DadosPersonagem("Personagem" + (i + 1), arena);
			Personagem personagem = new Personagem(pDados, listaDadosPersonagens, arena);
			listaDadosPersonagens.add(pDados);
			listaPersonagens.add(personagem);
		}
	}
	
	private void iniciarBatalha () {
		for (int i = 0; i < listaPersonagens.size(); i++) {
			listaPersonagens.get(i).start();
		}
	}
	
	@Override
	public void run() {
		iniciarBatalha();
		int contador = 999;
		int vencedor = 0;
		while (!isInterrupted()) {
			if (contador > 1) {
				//System.out.println("CONTADOR: " + contador);
	
				contador = 0;
				for (int i = 0; i < listaPersonagens.size(); i++) {
					if (listaDadosPersonagens.get(i).getHpAtual() > 0) {
						contador += 1;
						vencedor = i;
					}
				}
			} else {
				System.out.println(listaDadosPersonagens.get(vencedor).toString());
				Thread.currentThread().interrupt();
			}
		}
	}
}
