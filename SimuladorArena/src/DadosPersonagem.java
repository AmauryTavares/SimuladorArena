import java.util.Random;

public class DadosPersonagem {
	
	private boolean emCombate, passivaMortoVivo;
	private String nome;
	private int hp, força, inteligencia, defFisica, defMagica,
		energia, velocidade, agilidade, raca, personalidade,
		caractFisica, taxaFuga, taxaCritico, taxaResistencia,
		taxaAcerto, taxaEsquiva, escudo, turnosMortoVivo;
	
	Random rand = new Random();
	
	private void gerarCaractFisica() {
		if (this.caractFisica == CaractFisica.AGIL.getValorCaractFisica()) {
			this.setHp(rand.nextInt(201) + 600);
			this.setForça(rand.nextInt(41) + 80);
			this.setInteligencia(rand.nextInt(41) + 170);
			this.setVelocidade(rand.nextInt(31) + 140);
			this.setAgilidade(rand.nextInt(31) + 140);
			this.setDefFisica(rand.nextInt(21) + 50 + (this.força/6));
			this.setDefMagica(rand.nextInt(41) + 110 + (this.inteligencia/6));
			this.setEnergia(rand.nextInt(4) + 2 + (this.velocidade/80));
			this.setTaxaFuga(rand.nextInt(16) + 10 + (this.velocidade/15));
			this.setTaxaCritico(rand.nextInt(16) + 20 + (this.agilidade/10));
			this.setTaxaResistencia(rand.nextInt(8) + 5 + (this.força/10));
			this.setTaxaAcerto(rand.nextInt(21) + 60 + (this.agilidade/10));
			this.setTaxaEsquiva(rand.nextInt(16) + 25 + (this.agilidade/10));
			
		} else if (this.caractFisica == CaractFisica.MEDIANO.getValorCaractFisica()) {
			this.setHp(rand.nextInt(251) + 900);
			this.setForça(rand.nextInt(31) + 120);
			this.setInteligencia(rand.nextInt(31) + 120);
			this.setVelocidade(rand.nextInt(31) + 90);
			this.setAgilidade(rand.nextInt(21) + 110);
			this.setDefFisica(rand.nextInt(21) + 70 + (this.força/6));
			this.setDefMagica(rand.nextInt(21) + 70 + (this.inteligencia/6));
			this.setEnergia(rand.nextInt(4) + 1 + (this.velocidade/80));
			this.setTaxaFuga(rand.nextInt(8) + 8 + (this.velocidade/15));
			this.setTaxaCritico(rand.nextInt(11) + 10 + (this.agilidade/10));
			this.setTaxaResistencia(rand.nextInt(16) + 15 + (this.força/10));
			this.setTaxaAcerto(rand.nextInt(21) + 50 + (this.agilidade/10));
			this.setTaxaEsquiva(rand.nextInt(11) + 20 + (this.agilidade/10));
			
		} else if (this.caractFisica == CaractFisica.FORTE.getValorCaractFisica()) {
			this.setHp(rand.nextInt(401) + 1200);
			this.setForça(rand.nextInt(41) + 170);
			this.setInteligencia(rand.nextInt(41) + 80);
			this.setVelocidade(rand.nextInt(21) + 70);
			this.setAgilidade(rand.nextInt(21) + 80);
			this.setDefFisica(rand.nextInt(41) + 110 + (this.força/6));
			this.setDefMagica(rand.nextInt(21) + 50 + (this.inteligencia/6));
			this.setEnergia(rand.nextInt(3) + 1 + (this.velocidade/80));
			this.setTaxaFuga(rand.nextInt(6) + 5 + (this.velocidade/15));
			this.setTaxaCritico(rand.nextInt(11) + 5 + (this.agilidade/10));
			this.setTaxaResistencia(rand.nextInt(21) + 25 + (this.força/10));
			this.setTaxaAcerto(rand.nextInt(16) + 45 + (this.agilidade/10));
			this.setTaxaEsquiva(rand.nextInt(11) + 10 + (this.agilidade/10));
		}
	}
	
	private void bonusRaca() {
		if (this.raca == Raca.OGRO.getValorRaca()) {
			
		}
	}
	
	public boolean isPassivaMortoVivo() {
		return passivaMortoVivo;
	}
	
	public void setPassivaMortoVivo(boolean passivaMortoVivo) {
		this.passivaMortoVivo = passivaMortoVivo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getForça() {
		return força;
	}

	public void setForça(int força) {
		this.força = força;
	}

	public int getInteligencia() {
		return inteligencia;
	}

	public void setInteligencia(int inteligencia) {
		this.inteligencia = inteligencia;
	}

	public int getDefFisica() {
		return defFisica;
	}

	public void setDefFisica(int defFisica) {
		this.defFisica = defFisica;
	}

	public int getDefMagica() {
		return defMagica;
	}

	public void setDefMagica(int defMagica) {
		this.defMagica = defMagica;
	}

	public int getEnergia() {
		return energia;
	}

	public void setEnergia(int energia) {
		this.energia = energia;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(int velocidade) {
		this.velocidade = velocidade;
	}

	public int getAgilidade() {
		return agilidade;
	}

	public void setAgilidade(int agilidade) {
		this.agilidade = agilidade;
	}

	public int getRaca() {
		return raca;
	}

	public void setRaca(int raca) {
		this.raca = raca;
	}

	public int getPersonalidade() {
		return personalidade;
	}

	public void setPersonalidade(int personalidade) {
		this.personalidade = personalidade;
	}

	public int getCaractFisica() {
		return caractFisica;
	}

	public void setCaractFisica(int caractFisica) {
		this.caractFisica = caractFisica;
	}

	public int getTaxaFuga() {
		return taxaFuga;
	}

	public void setTaxaFuga(int taxaFuga) {
		this.taxaFuga = taxaFuga;
	}

	public int getTaxaCritico() {
		return taxaCritico;
	}

	public void setTaxaCritico(int taxaCritico) {
		this.taxaCritico = taxaCritico;
	}

	public int getTaxaResistencia() {
		return taxaResistencia;
	}

	public void setTaxaResistencia(int taxaResistencia) {
		this.taxaResistencia = taxaResistencia;
	}

	public int getTaxaAcerto() {
		return taxaAcerto;
	}

	public void setTaxaAcerto(int taxaAcerto) {
		this.taxaAcerto = taxaAcerto;
	}

	public int getTaxaEsquiva() {
		return taxaEsquiva;
	}

	public void setTaxaEsquiva(int taxaEsquiva) {
		this.taxaEsquiva = taxaEsquiva;
	}

	public int getEscudo() {
		return escudo;
	}

	public void setEscudo(int escudo) {
		this.escudo = escudo;
	}

	public int getTurnosMortoVivo() {
		return turnosMortoVivo;
	}

	public void setTurnosMortoVivo(int turnosMortoVivo) {
		this.turnosMortoVivo = turnosMortoVivo;
	}

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
