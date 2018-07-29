import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Batalha extends Thread{
	
	int arena;
	ArrayList<DadosPersonagem> listaDadosPersonagens;
	ArrayList<Personagem> listaPersonagens;
	int qtdPersonagens;
	static int qtdMortos = 0;
	static Semaphore sem = new Semaphore(1);
	
	Random rand = new Random();
	
	public Batalha (int qtd) {
		this.arena = Arena.sortearArena();
		listaDadosPersonagens = new ArrayList<>();
		listaPersonagens = new ArrayList<>();
		criarLista(qtd);
		this.qtdPersonagens = qtd;
	}
	
	private void criarLista(int tamanho) {
		for (int i = 0; i < tamanho; i++) {
			DadosPersonagem pDados = new DadosPersonagem("Personagem" + (i + 1), arena);
			Personagem personagem = new Personagem(pDados, listaDadosPersonagens, arena);
			listaDadosPersonagens.add(pDados);
			listaPersonagens.add(personagem);
		}
	}
	
	synchronized public static void morto() {
		qtdMortos++;
	}
	
	static public int getQtdMortos() {
		return qtdMortos;
	}
			
	private void iniciarBatalha () {
		for (int i = 0; i < listaPersonagens.size(); i++) {
			listaPersonagens.get(i).start();
		}
	}
	
	@Override
	public void run() {
		iniciarBatalha();
		int vencedor = 0;
		while (qtdMortos != (qtdPersonagens - 1)) {
			try {
				Thread.sleep(500);
				System.out.println("(TEMPORARIO) QUANTIDADE DE MORTOS: " + qtdMortos);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} 
		
		for (int i = 0; i < listaDadosPersonagens.size(); i++) {
			if (listaDadosPersonagens.get(i).getHpAtual() > 0) {
				vencedor = i;
			}
		}
		
		listaPersonagens.get(vencedor).interrupt();
		
		System.out.println("\n\n###################VENCEDOR###################\n\n" 
	+ listaDadosPersonagens.get(vencedor).toString());		
		
	}
}
