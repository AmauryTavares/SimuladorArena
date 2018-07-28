import java.util.ArrayList;
import java.util.Random;

public class Personagem extends Thread{
	
	DadosPersonagem personagem;
	ArrayList<DadosPersonagem> listaPersonagens;
	int arena;
	
	public Personagem (DadosPersonagem dados, ArrayList<DadosPersonagem> listaPersonagens, int arena) {
		this.personagem = dados;
		this.listaPersonagens = listaPersonagens;
		this.arena = arena;
	}
	
	public DadosPersonagem entrarEmCombate(ArrayList<DadosPersonagem> listaPersonagens) {
		
		boolean suaConfirmacao = false;
		int valorAleatorio = 0;
		while (!suaConfirmacao) {
			 suaConfirmacao = personagem.iniciarBatalha();
		}
		
		Random rand = new Random();
		
		boolean ConfirmacaoInimigo = false;
		while (!ConfirmacaoInimigo) {
			valorAleatorio = rand.nextInt(listaPersonagens.size());
			ConfirmacaoInimigo = listaPersonagens.get(valorAleatorio).iniciarBatalha();
		}
		
		return listaPersonagens.get(valorAleatorio);
	}
	
	@Override
	public void run() {
		
	}
}
