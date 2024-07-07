package impacta.ead.estacionamento.negocio;

import impacta.ead.estacionamento.controle.EstacionamentoController;

/**
 * Representa as informações relativas as vagas de estacionamento
 * ou status de ocupação.
 * @author gbb09
 *
 */
public class Vaga {
	public static int TOTAL_VAGAS = 100;
	private static int vagasOcupadas = inicializarOcupadas();
	
	private Vaga(){}
	
	
	/**
	 * verificar a existencia de alguma vaga livre no estacionamento
	 *  
	 * @return True se houver vaga e false se estiver lotado.
	 */
	
	public static boolean temVagaLivre() {
		return (vagasOcupadas < TOTAL_VAGAS);
	}
	
	/**
	 * Buscar o status atual das vagas do estacionamento.
	 */
	public static int inicializarOcupadas() {
		EstacionamentoController controle = new EstacionamentoController();
		return controle.inicializarOcupadas();
	}
	
	/**
	 * Retorna o numero de vagas ocupadas.
	 * @return numero total de vagas ocupadas num determinado instante.
	 */
	public static int ocupadas() {
		return Vaga.vagasOcupadas;
	}
	
	/**
	 * Retorna o numero de vagas livres.
	 * @return numero total de vagas livres num determinado instante.
	 */
	public static int livres() {
		return TOTAL_VAGAS - Vaga.vagasOcupadas;
	}
	/**
	 * Atualiza o número de vagas ocupadas após a entrada do veiculo.
	 * 
	 */
	public static void entrou() {
		Vaga.vagasOcupadas ++;
	}


	public static void saiu() {
		Vaga.vagasOcupadas --;
		
	}

}
