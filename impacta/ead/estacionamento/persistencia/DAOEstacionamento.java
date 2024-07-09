package impacta.ead.estacionamento.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import impacta.ead.estacionamento.controle.EstacionamentoException;
import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Vaga;
import impacta.ead.estacionamento.negocio.Veiculo;
import impacta.ead.estacionamento.utilitario.EstacionamentoUtil;

/**
 * Representa a camada de persistência da aplicação.
 * Realiza o mapeamento objeto-relacional
 * 
 * 
 * @author gbb09
 *
 */

public class DAOEstacionamento {
	/**
	 * Armazena os dados da movimentação
	 * 
	 * 
	 * @param movimentacao instancia de movimentação
	 * @throws EstacionamentoException se houver erro de registro
	 */

	public void criar(Movimentacao movimentacao) throws EstacionamentoException {
		String cmd1 = EstacionamentoUtil.get("insertMov");
		String cmd2 = EstacionamentoUtil.get("atualizaVaga");

		Connection conexao = null;
		try {
			conexao = getConnection();

			conexao.setAutoCommit(false);

			PreparedStatement stmt = conexao.prepareStatement(cmd1);
			stmt.setString(1, movimentacao.getVeiculo().getPlaca());
			stmt.setString(2, movimentacao.getVeiculo().getMarca());
			stmt.setString(3, movimentacao.getVeiculo().getModelo());
			stmt.setString(4, movimentacao.getVeiculo().getCor());
			stmt.setString(5, EstacionamentoUtil.getDataAsString(movimentacao.getDataHoraEntrada()));

			stmt.execute();

			stmt = conexao.prepareStatement(cmd2);
			stmt.setInt(1, Vaga.ocupadas() + 1);

			stmt.execute();
			conexao.commit();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conexao.rollback();
				throw new EstacionamentoException("Erro ao registrar veiculo");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	/**
	 * Atualiza os dados de data de saída e valor de movimentação.
	 * 
	 * @param movimentacao Instância de movimentação
	 * @throws EstacionamentoException 
	 */

	public void atualizar(Movimentacao movimentacao) throws EstacionamentoException {
		String cmd1 = EstacionamentoUtil.get("updateMov");
		String cmd2 = EstacionamentoUtil.get("atualizaVaga");

		Connection conexao = null;
		try {
			conexao = getConnection();

			conexao.setAutoCommit(false);

			PreparedStatement stmt = conexao.prepareStatement(cmd1);
			stmt.setDouble(1, movimentacao.getValor());
			stmt.setString(2, EstacionamentoUtil.getDataAsString(movimentacao.getDataHoraSaida()));
			stmt.setString(3, movimentacao.getVeiculo().getPlaca());
			
			stmt.execute();

			stmt = conexao.prepareStatement(cmd2);
			stmt.setInt(1, Vaga.ocupadas() - 1);

			stmt.execute();
			conexao.commit();
		} catch (SQLException e) {
			try {
				e.printStackTrace();
				conexao.rollback();
				throw new EstacionamentoException("Erro ao registrar veiculo");
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	/**
	 * Busca a movimentação cujo o veiculo tem a placa informada que ainda está estacionado
	 * (data de saída nula)
	 * 
	 * @param movimentacao
	 * @return A movimentação encontrada ou null se não houver.
	 * 
	 */

	public Movimentacao buscarMovimentacaoAberta(String placa) {
		String cmd = EstacionamentoUtil.get("getMovAberta");
		Connection conexao = null;
		Movimentacao movimentacao = null;
		
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			ps.setString(1, placa);
			
			ResultSet resultado = ps.executeQuery();
			
			if(resultado.next()) {
				String rplaca = resultado.getString("placa");
				String rdataEntrada = resultado.getString("data_entrada");
				Veiculo veiculo = new Veiculo(rplaca);
				movimentacao = new Movimentacao(veiculo,
						EstacionamentoUtil.getDate(rdataEntrada));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conexao);
		}
		return movimentacao;
	}
	/**
	 * Consulta todas as movimentações fechadas 
	 * (pagas e com data de saida preenchida) no mês e ano da 
	 * data informada
	 * 
	 * 
	 * @param data Data de consulta
	 * @return Lista de movimentações do ano e mês informados
	 */

	public List<Movimentacao> consultarMovimentacoes(LocalDateTime data) {
		// TODO implementar
		return null;
	}

	public static Connection getConnection() throws SQLException {

		String url = EstacionamentoUtil.get("url");
		String usuario = EstacionamentoUtil.get("usuario");
		String senha = EstacionamentoUtil.get("senha");

		Connection conexao = DriverManager.getConnection(url, usuario, senha);
		return conexao;
	}

	public static void closeConnection(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
		}
	}
	public int getOcupadas() {
		int ocupadas = 0;
		Connection conexao = null;
		String cmd = EstacionamentoUtil.get("consultaOcupadas");
		try {
			conexao = getConnection();
			PreparedStatement ps = conexao.prepareStatement(cmd);
			
			ResultSet resultado = ps.executeQuery();
			if(resultado.next()) {
				ocupadas = resultado.getInt("ocupadas");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection(conexao);
		}
		return ocupadas;
	}

}
