package impacta.ead.estacionamento.utilitario;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import impacta.ead.estacionamento.negocio.Movimentacao;
import impacta.ead.estacionamento.negocio.Tarifario;

/**
 * Representa uma classe de apoio às demais do sistema.
 * 
 * @author gbb09
 *
 */

public class EstacionamentoUtil {
	
	/**
	 * Valida a placa com o padrão LLL-NNNN
	 * L = letra
	 * N = número
	 * 
	 * @param placa Placa do veículo
	 * @return True se atender o padrão e false senão
	 */
	public static boolean validarPadraoPlaca(String placa) {
		String padrao = "[A-Z][A-Z][A-Z]-\\d\\d\\d\\d";
		Pattern p = Pattern.compile(padrao);
		Matcher m = p.matcher(placa);
		
		
		return m.matches();
	}
	
	/**
	 * O calculo da estada do veículo baseado no tarifário e na hora 
	 * de entrada e saída do veículo
	 * 
	 * Altera a própria instâcia do parâmetro
	 * 
	 * @param movimentacao Instância da movimentacao
	 */
	public void calcularPagamento(Movimentacao movimentacao) {
		//TODO implementar
	}
	
	/**
	 * Recupera uma propriedade do arquivo de configuração da aplicação
	 * configuration.txt
	 * @param propriedade
	 * @return valor associado a propriedade
	 */

	public static String get(String propriedade) {
		Properties prop = new Properties();
		String valor = null;
		try {
			prop.load(EstacionamentoUtil.class.getResourceAsStream("/recursos/configuration.txt"));
			valor = prop.getProperty(propriedade);
		} catch (IOException e) {
			assert false : "configuração não carregada";
			
		}
		return valor;
	}

	public static String getDataAsString(LocalDateTime dataHoraEntrada) {
		return dataHoraEntrada.toString();
	}

	public static void calcularValorPago(Movimentacao movimentacao) {
		LocalDateTime inicio = movimentacao.getDataHoraEntrada();
		LocalDateTime fim = movimentacao.getDataHoraSaida();
		double valor = 0;
		
		long diffHoras = inicio.until(fim, ChronoUnit.HOURS);
		
		if(diffHoras > 0) {
			valor += Tarifario.VALOR_HORA;
			fim = fim.minus(1, ChronoUnit.HOURS);
		}
		
		long diffMinutos = inicio.until(fim, ChronoUnit.MINUTES);
		
		valor += (diffMinutos/Tarifario.INCREMENTO_MINUTOS) * Tarifario.VALOR_INCREMENTAL;
		
		movimentacao.setValor(valor);
		
	}

	public static LocalDateTime getDate(String rdataEntrada) {
		return LocalDateTime.parse(rdataEntrada,
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
	}
	
	

}
