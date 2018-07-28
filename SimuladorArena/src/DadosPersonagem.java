
public class DadosPersonagem {
	
	boolean emCombate;
	
	synchronized public boolean iniciarBatalha() {
		if (!this.emCombate) {
			this.emCombate = true;
			return true;
		}
		return false;
	}
	
	synchronized public void encerrarBatalha() {
		this.emCombate = false;
	}
}
