package impacta.ead.estacionamento.controle;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Coordena todos os fluxos dos casos de uso do sistema
 * 
 * @author gbb09
 */

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.negocio.Veiculo;
import impacta.ead.estacionamento.persistencia.DAOEstacionamento;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

public class EstacionamentoController {
	
	/**
	 * A partir dos dados do veiculo informados pelo operador realiza
	 * o fluxo de entrada do veiculo no estacionamento registrando
	 * a movimentação gerada
	 * 
	 * @param placa Placa do veiculo
	 * @param marca Marca do veiculo
	 * @param modelo Modelo do veiculo
	 * @param cor Cor do veiculo
	 * @throws EstacionamentoException Quando o estacionamento estiver lotado 
	 * @throws veiculoException Quando o padrão da placa for invalido
	 */
	
	public void processarEntrada(String placa, String marca,
								 String modelo, String cor) 
								 throws EstacionamentoException, veiculoException {
		//verificar se o estacionamento está lotado
		if(!Vaga.temVagaLivre()) {
			throw new EstacionamentoException("Estacionamento lotado!");
		}
		
		//Verificar o padrão de string da placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)) {
			throw new veiculoException("Placa informada inválida!");
		}
		
		//criar uma instacia do veiculo
		Veiculo veiculo = new Veiculo(placa,marca,modelo,cor);
		
		//criar a movimentação vinculando o veiculo e com data de entrada corrente
		Movimentacao movimentacao = new Movimentacao(veiculo, LocalDateTime.now());
		
		//registrar na base de dados a informação
		DAOEstacionamento dao = new DAOEstacionamento();
		dao.criar(movimentacao);
		
		//atualizar o numero de vagas ocupadas
		Vaga.entrou();
		//fim
	}
	/**
	 * A partir de uma placa de veiculo informada, realiza todo o
	 * fluxo de saida de veiculo do estacionamento
	 * @param placa Placa do veículo que estiver saindo
	 * @return Uma instância de movimentação com dados atualizados de valor
	 * @throws veiculoException Quando a placa estiver incorreta
	 * @throws EstacionamentoException Quando o veículo com a placa informada
	 * não é encontrado no estacionamento
	 */
	public Movimentacao processarSaida (String placa) throws veiculoException, EstacionamentoException {
		//validar a placa
		if(!EstacionamentoUtil.validarPadraoPlaca(placa)) {
			throw new veiculoException("Placa inválida");
		}
		
		//Buscar a movimentação aberta baseada na placa
		DAOEstacionamento dao = new DAOEstacionamento();
		Movimentacao movimentacao = dao.buscarMovimentacaoAberta(placa);
		
		if(movimentacao == null) {
			throw new EstacionamentoException("Veículo não encontrado!");
		}
		
		//fazer o calculo do valor a ser pago
		movimentacao.setDataHoraSaida(LocalDateTime.now());
		EstacionamentoUtil.calcularValorPago(movimentacao);
		
		//Atualizar os dados da movimentação
		dao.atualizar(movimentacao);
		
		//Atualizar o status da vaga
		Vaga.saiu();
		
		return movimentacao;
	}
	
	/**
	 * Realiza o fluxo de emissão de relatório de faturamento
	 * baseado num ano e mês informados
	 * 
	 * @param data Data (Mês e Ano) de emissão desejado
	 * 
	 * @return Lista de movimentações que atendem ao filtro
	 */
	
	public List<Movimentacao> emitirRelatorio(LocalDateTime data){
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.consultarMovimentacoes(data);
	}
	
	
	public int inicializarOcupadas() {
		DAOEstacionamento dao = new DAOEstacionamento();
		return dao.getOcupadas();
	}

}
