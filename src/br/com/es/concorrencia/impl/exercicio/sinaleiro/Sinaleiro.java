package br.com.es.concorrencia.impl.exercicio.sinaleiro;

import br.com.es.concorrencia.impl.exercicio.sinaleiro.repo.AlternadorSinal;
import br.com.es.concorrencia.impl.exercicio.sinaleiro.repo.Andar;
import br.com.es.concorrencia.impl.exercicio.sinaleiro.repo.Grafico;
import br.com.es.concorrencia.impl.exercicio.sinaleiro.repo.PainelControle;
import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;
import br.com.fbm.debug.business.service.annotations.Repetir;

@Repetir(100)
@ExMap(numero = 10, titulo = "Sinaleiro para exercício de concorrência")
public class Sinaleiro extends ExGeneric {

	public static int TM_RUA = 145;
	
	PainelControle controle;
	Grafico grafico;
	Thread andar, alternador;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		
		controle = PainelControle.getInstance();
		grafico = new Grafico();
		
		alternador = new Thread(new AlternadorSinal());
		andar = new Thread(new Andar(grafico));
		
	}
	
	@Override
	public void processarExercicio() throws BusinessException {

		alternador.start();
		andar.start();
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		
		try {
			alternador.join();
			andar.join();
		}catch(final Exception e) {}
		
		controle.setSinalVerde(true);
		controle.setSinalVermelho(false);
		controle.setSinalAmarelo(false);
		controle.setFim(false);
		controle.setPosicaoGrade(0);
		
		andar = null;
		alternador = null;
		grafico = null;
		
		System.out.println("\n\n\n");
		
	}
	
}
