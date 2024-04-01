package br.com.es.concorrencia.impl.exercicio.sinaleiro.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import br.com.es.concorrencia.impl.exercicio.sinaleiro.Sinaleiro;

public class Grafico {
	
	String carro = "\uD83D\uDE97";
	String sinalVermelho = "\uD83D\uDD34";
	String sinalVerde = "\uD83D\uDFE2";
	String sinalAmarelo = "\uD83D\uDFE1";
	String unidadeRua = "_";
	String meiaUnidade = ".";
	
	List<String> gradeRua;
	
	StringBuilder rua;
	
	PainelControle controle;
	
	public Grafico() {
		
		rua = new StringBuilder();
		gradeRua = new ArrayList<>();
		controle = PainelControle.getInstance();
		
	}
	
	public void desenhar() {
		
		try {
			TimeUnit.MILLISECONDS.sleep(50);
		}catch(Exception e) {}
		
		gradeRua.clear();
		rua.delete(0, rua.length());
		
		for(int i=0; i<Sinaleiro.TM_RUA; i++) {
			
			String flagSinal = controle.isSinalVermelho()
					? "|" : "";
			
			if( i == controle.getPosicaoGrade() ) {
				gradeRua.add(carro + flagSinal);
				continue;
			}
			
			String localUnidade = controle.isSinalVerde()
					? unidadeRua : meiaUnidade;
			
			gradeRua.add(unidadeRua);
			
		}
		
		String msg = "Boa Viagem...";
		String localSinal = sinalVerde;
		
		if( controle.isSinalAmarelo() ) {
			msg = "Atenção...";
			localSinal = sinalAmarelo;
		}
		
		if( controle.isSinalVermelho() ) {
			msg = "Pare!!!";
			localSinal = sinalVermelho;
		}
		
		rua
			.append("SINAL ")
			.append(" ")
			.append(localSinal)
			.append(" ")
			.append(msg)
			.append("\n");
		
		gradeRua.forEach(r -> {
			rua.append(r);
		});
		
		rua.append("\n".repeat(25));
		
	}
	
	public List<String> getGradeRua() {
		return gradeRua;
	}
	
	public StringBuilder getRua() {
		return rua;
	}
	
}
