import java.util.ArrayList;
import java.util.Random;

public class Personagem extends Thread{
	
	DadosPersonagem personagem;
	ArrayList<DadosPersonagem> listaPersonagens;
	int arena;
	boolean atacarMesmoInimigo;
	int mesmoInimigo;
	int ataqueCobra, ataqueSanguessuga, infeccaoAlimentar, doencaInseto;
	boolean morto = false;
	boolean recuperarHP = false;
	Random rand = new Random();
	
	public Personagem (DadosPersonagem dados, ArrayList<DadosPersonagem> listaPersonagens, int arena) {
		this.personagem = dados;
		this.listaPersonagens = listaPersonagens;
		this.arena = arena;
	}
	
	public DadosPersonagem entrarEmCombate (ArrayList<DadosPersonagem> listaPersonagens) {
		
		boolean suaConfirmacao = false;
		boolean ConfirmacaoInimigo = false;
		int valorAleatorio = 0;
		
		while (!ConfirmacaoInimigo) {
			try {
				personagem.encerrarBatalha();
				Thread.sleep(rand.nextInt(501));
				while (!suaConfirmacao && personagem.getHpAtual() > 0) {
					 suaConfirmacao = personagem.iniciarBatalha();
				}
				
				Random rand = new Random();
				
				valorAleatorio = rand.nextInt(listaPersonagens.size());
				while (listaPersonagens.get(valorAleatorio).getNome().equals(personagem.getNome())	// enquanto for o mesmo personagem(não é possível atacar si mesmo) ou o personagem estiver morto
						|| listaPersonagens.get(valorAleatorio).getHpAtual() == 0) {
					valorAleatorio = rand.nextInt(listaPersonagens.size());
				}
				
				if (personagem.getHpAtual() > 0 && suaConfirmacao) {
					if (personagem.getPersonalidade() == Personalidade.ESTRATEGISTA.getValorPersonalidade()) {
						if (atacarMesmoInimigo) {	
							ConfirmacaoInimigo = listaPersonagens.get(mesmoInimigo).iniciarBatalha(); // ataca o mesmo inimigo
							if (ConfirmacaoInimigo == false) {	// se falhar procura outro
								atacarMesmoInimigo = false;
							}
						} else {
							ConfirmacaoInimigo = listaPersonagens.get(valorAleatorio).iniciarBatalha();
							if (ConfirmacaoInimigo && listaPersonagens.get(mesmoInimigo).getHpAtual() > 0 && listaPersonagens.get(mesmoInimigo).isDormindo()) { 	//Se atacar o inimigo ativa o ataque estratégico
								atacarMesmoInimigo = true;
								mesmoInimigo = valorAleatorio;
							}
						}
					} else if (personagem.getPersonalidade() == Personalidade.ESPERTO.getValorPersonalidade()) {
						if (listaPersonagens.get(valorAleatorio).getHpAtual() < personagem.getHpAtual()) {	//Ataca o inimigo somente quando tem vida menor
							ConfirmacaoInimigo = listaPersonagens.get(valorAleatorio).iniciarBatalha();
						}
					} else if (personagem.getPersonalidade() == Personalidade.CORAJOSO.getValorPersonalidade()) {
						if (listaPersonagens.get(valorAleatorio).isDormindo() == false) {	//Ataca o inimigo apenas quando está acordado
							ConfirmacaoInimigo = listaPersonagens.get(valorAleatorio).iniciarBatalha();
						}
					} else {
						ConfirmacaoInimigo = listaPersonagens.get(valorAleatorio).iniciarBatalha();
					}
				}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		
		return listaPersonagens.get(valorAleatorio);
	}
	
	public void atacar (DadosPersonagem atacante, DadosPersonagem inimigo) {

		boolean critico = false;
		boolean passivaAssassino = false;
		int danoFisico = atacante.getForca() * (1 - (inimigo.getDefFisica()/(inimigo.getDefFisica() + 100))); //(Força do Personagem * (1 - (defFísica do Inimigo / (defFísica do Inimigo + 100)))
		int danoMagico = atacante.getInteligencia() * (1 - (inimigo.getDefMagica()/(inimigo.getDefMagica() + 100))); 
		int danoHibrido = (int)((danoFisico + danoMagico) * 0.5);
		if ((rand.nextInt(100) + 1) > inimigo.getTaxaFuga()) {	// Se menor, o inimigo foge
			
			if (atacante.getRaca() == Raca.ASSASSINO.getValorRaca()) {
				if (inimigo.isDormindo()) { 	//passiva 300% quando inimigo está dormindo
					danoFisico = danoFisico * 2;
					passivaAssassino = true;
				} 
			}
			
			if (passivaAssassino == false && (rand.nextInt(100) + 1) <= atacante.getTaxaCritico()) { // verifica se foi um ataque critico
				danoFisico = (int)(danoFisico * 1.5);  // 150%	
				danoMagico = (int)(danoMagico * 1.5);  // 150%	
				danoHibrido = (int)(danoHibrido * 1.5);  // 150%	
				critico = true;
			}
			
			if ((rand.nextInt(100) + 1) <= atacante.getTaxaAcerto()) {	// verifica de o ataque acerta
				if ((rand.nextInt(100) + 1) > inimigo.getTaxaEsquiva()) {	// verifica se o inimigo desvia
					danoFisico = danoFisico * (1 - (inimigo.getTaxaResistencia()/100)); // Calculo de dano com resistencia do inimigo
					danoMagico = danoMagico * (1 - (inimigo.getTaxaResistencia()/100)); // Calculo de dano com resistencia do inimigo
					danoHibrido = danoHibrido * (1 - (inimigo.getTaxaResistencia()/100));
					
					if (atacante.getRaca() == Raca.OGRO.getValorRaca()
							|| atacante.getRaca() == Raca.ELFO.getValorRaca()
							|| atacante.getRaca() == Raca.ANAO.getValorRaca()
							|| atacante.getRaca() == Raca.ASSASSINO.getValorRaca()
							|| atacante.getRaca() == Raca.MORTO_VIVO.getValorRaca()) {
						
						inimigo.setHpAtual(inimigo.getHpAtual() - danoFisico);
	
						if (passivaAssassino) {
							System.out.println(atacante.getNome() + " atacou sorrateiramente e causou " + danoFisico + " de dano físico à " + inimigo.getNome());
						} else if (critico) {
							System.out.println(atacante.getNome() + " atacou em um ponto CRÍTICO e causou " + danoFisico + " de dano físico à " + inimigo.getNome());
						} else {
							System.out.println(atacante.getNome() + " causou " + danoFisico + " de dano físico à " + inimigo.getNome());
						}
						
					} else if (atacante.getRaca() == Raca.VAMPIRO.getValorRaca()
							|| atacante.getRaca() == Raca.MAGO.getValorRaca()) {
						
						inimigo.setHpAtual(inimigo.getHpAtual() - danoMagico);
						
						
						if (critico) {
							System.out.println(atacante.getNome() + " atacou em um ponto CRÍTICO e causou " + danoMagico + " de dano mágico à " + inimigo.getNome());
						} else {
							System.out.println(atacante.getNome() + " causou " + danoMagico + " de dano mágico à " + inimigo.getNome());
						}
						
						if (atacante.getRaca() == Raca.VAMPIRO.getValorRaca()) {
							atacante.setHpAtual(atacante.getHpAtual() + (int)(danoMagico * 0.25));
							System.out.println(atacante.getNome() + " sugou " + (int)(danoMagico * 0.25) + " de sangue de " + inimigo.getNome());
						}
						
					} else if (atacante.getRaca() == Raca.PALADINO.getValorRaca()
							|| atacante.getRaca() == Raca.DRACONIANA.getValorRaca()) {
						
						if (atacante.getRaca() == Raca.DRACONIANA.getValorRaca()) {
							inimigo.setHpAtual(inimigo.getHpAtual() - 300);
							
							System.out.println(atacante.getNome() + " causou " + 300 + " de dano real à " + inimigo.getNome());
							
						} else {
							inimigo.setHpAtual(inimigo.getHpAtual() - danoHibrido);
							
							if (critico) {
								System.out.println(atacante.getNome() + " atacou em um ponto CRÍTICO e causou " + danoHibrido + " de dano híbrido  à " + inimigo.getNome());
							} else {
								System.out.println(atacante.getNome() + " causou " + danoHibrido + " de dano híbrido à " + inimigo.getNome());
							}
							
						}
					}
					
					if (inimigo.getHpAtual() == 0) {
						System.out.println(atacante.getNome() + " matou " + inimigo.getNome());
					}
					
				}
			}
		}
	}
	
	public void dormir() {
		try {
			personagem.setDormindo(true);
			Thread.sleep(3000 + (personagem.getEnergiaTotal() * 500));	// 3s + (energia * 0.5s)
			personagem.setDormindo(false);
			personagem.setEnergiaAtual(personagem.getEnergiaTotal());	//recupera a energia
			if (personagem.getRaca() == Raca.ANAO.getValorRaca() && personagem.getHpAtual() > 0) {
				personagem.setEscudo(personagem.getEscudo() + 100);	//Recupera 100 do escudo do robô
			} else if (personagem.getRaca() == Raca.MORTO_VIVO.getValorRaca()
					&& personagem.getTurnosMortoVivo() > 0) {
				personagem.setTurnosMortoVivo(personagem.getTurnosMortoVivo() - 1);
			}
			
			recuperarHP = true;
			
		} catch (InterruptedException ex) {	
		}
	}
	
	@Override
	public void run() {
		while (!morto) { 
			if(personagem.getHpAtual() > 0) { //Verifica se o personagem está morto
				if (personagem.getEnergiaAtual() > 0) {
					
					if (recuperarHP && personagem.getHpAtual() > 0) {
						personagem.setHpAtual(personagem.getHpAtual() + (int)((personagem.getHpTotal() - personagem.getHpAtual()) * 0.1));	//Recupera 10% da vida perdida
						recuperarHP = false;
					}
					
					if(personagem.isEmCombate() == false && personagem.getHpAtual() > 0) {//Verifica a energia do personagem
						personagem.setEnergiaAtual(personagem.getEnergiaAtual() - 1); 	//Energia--
						boolean continuaAtaque = true;
						if (personagem.getPersonalidade() == Personalidade.INDECISO.getValorPersonalidade()) {
							if (rand.nextInt(100) % 2 == 0) {		// 50% de chance de desistir do ataque
								continuaAtaque = false;	
								System.out.println(personagem.getNome() + " desistiu do ataque");
							}
						}
						
						if (continuaAtaque && personagem.getHpAtual() > 0) {
							//Perigos das arenas
							if (arena == Arena.CAMPO_GLACIAL.getValorArena() ) {
								if ((rand.nextInt(100) + 1) <= 5) { 	// 5% de chance morrer
									personagem.setHpAtual(0);
									continuaAtaque = false;
									System.out.println(personagem.getNome() + " morreu congelado");
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(151) + 200));
									System.out.println(personagem.getNome() + " sofreu um ataque de urso");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " foi morto por um urso");
									}
								} else if ((rand.nextInt(100) + 1) <= 15) {		//15%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(51) + 100));
									System.out.println(personagem.getNome() + " foi atingido por uma avalanche");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " foi morto em uma avalanche");
									}
								} else if ((rand.nextInt(100) + 1) <= 15) {		//15%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(51) + 100));
									System.out.println(personagem.getNome() + " caiu em um buraco na neve");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu preso em um buraco na neve");
									}
								}
							} else if (arena == Arena.DESERTO.getValorArena() ) {
								if ((rand.nextInt(100) + 1) <= 5) { 	// 5% de chance morrer
									personagem.setHpAtual(0);
									continuaAtaque = false;
									System.out.println(personagem.getNome() + " morreu de sede");
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									ataqueCobra = 3;		//renova
									System.out.println(personagem.getNome() + " foi picado por uma cobra venenosa");
								} else if ((rand.nextInt(100) + 1) <= 20) {		//20%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(21) + 80));
									System.out.println(personagem.getNome() + " caiu em uma areia movediça");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu soterrado em uma areia movediça");
									}
								} else if ((rand.nextInt(100) + 1) <= 35) {		//35%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(31) + 50));
									System.out.println(personagem.getNome() + " está sofrendo no calor escaldante do deserto");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu de calor excessivo");
									}
								}
							} else if (arena == Arena.PANTANO.getValorArena() ) {
								if ((rand.nextInt(100) + 1) <= 5) { 	// 5% de chance morrer
									personagem.setHpAtual(0);
									continuaAtaque = false;
									System.out.println(personagem.getNome() + " morreu de febre do pântano");
								} else if ((rand.nextInt(100) + 1) <= 25) {		//25%
									ataqueSanguessuga = 3;		//renova
									System.out.println(personagem.getNome() + " ficou coberto de sanguessugas pelo corpo");
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(151) + 200));
									System.out.println(personagem.getNome() + " foi atacado por um crocodilo feroz");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " foi morto por um ataque de crocodilos");
									}
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									infeccaoAlimentar = 3;		// renova
									System.out.println(personagem.getNome() + " contraiu uma infecção alimentar");
								}
							} else if (arena == Arena.SELVA.getValorArena() ) {
								if ((rand.nextInt(100) + 1) <= 5) { 	// 5% de chance morrer
									personagem.setHpAtual(0);
									continuaAtaque = false;
									System.out.println(personagem.getNome() + " caiu em uma armadilha e morreu");
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									personagem.setHpAtual(personagem.getHpAtual() - (rand.nextInt(151) + 200));
									System.out.println(personagem.getNome() + " foi atacado por um tigre");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " foi devorado por um tigre");
									}
								} else if ((rand.nextInt(100) + 1) <= 20) {		//20%
									doencaInseto = 2;	// renova
									System.out.println(personagem.getNome() + " está contaminado por doença de insetos");
								} else if ((rand.nextInt(100) + 1) <= 10) {		//10%
									infeccaoAlimentar = 3; 		// renova
									System.out.println(personagem.getNome() + " contraiu um infecção alimentar");
								}
							}
							
							//Calculo de dano dos perigos por turno da arena
							if (continuaAtaque && personagem.getHpAtual() > 0) {
								int dano;
								if (ataqueCobra > 0 && continuaAtaque) {
									ataqueCobra--;
									dano = rand.nextInt(51) + 100;
									personagem.setHpAtual(personagem.getHpAtual() - dano);
									System.out.println(personagem.getNome() + " perdeu " + dano + " de vida por conta do envenamento");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu envenenado");
									}
								}
								if (ataqueSanguessuga > 0 && continuaAtaque) {
									ataqueSanguessuga--;
									dano = rand.nextInt(31) + 50;
									personagem.setHpAtual(personagem.getHpAtual() - dano);
									System.out.println("Sanguessugas consumiram " + dano + " de vida de " + personagem.getNome());
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu sem uma gota de sangue por sanguessugas");
									}
								}
								
								if (infeccaoAlimentar > 0 && continuaAtaque) {
									infeccaoAlimentar--;
									dano = rand.nextInt(71) + 80;
									personagem.setHpAtual(personagem.getHpAtual() - dano);
									System.out.println(personagem.getNome() + " perdeu " + dano + " de vida por causa de uma infecção alimentar");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu no campo de batalha depois de vários dias de infecção alimentar");
									}
								}
								
								if (doencaInseto > 0 && continuaAtaque) {
									doencaInseto--;
									dano = rand.nextInt(31) + 60;
									personagem.setHpAtual(personagem.getHpAtual() - dano);
									System.out.println(personagem.getNome() + " perdeu " + dano + " de vida por causa uma doença que contraiu durante uma de suas batalhas");
									if (personagem.getHpAtual() == 0) {
										continuaAtaque = false;
										System.out.println(personagem.getNome() + " morreu no campo de batalha procurando uma cura para a doença dos insetos");
									}
								}
							}
							
							
							//fase de ataque
							if (continuaAtaque && personagem.getHpAtual() > 0) {
								DadosPersonagem inimigo = entrarEmCombate(listaPersonagens);
								atacar(personagem, inimigo);
								
								if (inimigo.isDormindo() == false && inimigo.getHpAtual() > 0) {	//inimigo acordado e vivo
									atacar(inimigo, personagem);
								}
								
								personagem.encerrarBatalha();
								inimigo.encerrarBatalha();
								
							}
						}
					}
				} else {
					if (personagem.getPersonalidade() == Personalidade.AMBICIOSO.getValorPersonalidade()) {	
						if ((rand.nextInt(100) + 1) <= 30) { 	//30% de chance de atacar novamente
							personagem.setEnergiaAtual(personagem.getEnergiaAtual() + 1);
						} else {
							System.out.println(personagem.getNome() + " dormiu");
							dormir();
						}
					} else {
						System.out.println(personagem.getNome() + " dormiu");
						dormir();
					}
				}
			} else {
				morto = true;
				try {
					Batalha.sem.acquire();
					Batalha.morto();
					Batalha.sem.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
