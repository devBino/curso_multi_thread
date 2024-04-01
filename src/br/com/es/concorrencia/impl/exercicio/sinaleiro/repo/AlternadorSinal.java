package br.com.es.concorrencia.impl.exercicio.sinaleiro.repo;

import java.util.concurrent.TimeUnit;

public class AlternadorSinal implements Runnable {

	private PainelControle controle;
	
	public AlternadorSinal() {
		controle = PainelControle.getInstance();
	}
	
	@Override
	public void run() {
		
		while( !controle.isFim() ) {
			
			int tempo = 1500;
			
			if( controle.isSinalAmarelo() ) {
				tempo = 750;
			}
			
			if( controle.isSinalVermelho() ) {
				tempo = 500;
			}
			
			try {
				TimeUnit.MILLISECONDS.sleep(tempo);
			}catch(final Exception exception) {}

			if( controle.isSinalVerde() && !controle.isSinalAmarelo() && !controle.isSinalVermelho() ) {
				
				controle.setSinalVerde(false);
				controle.setSinalAmarelo(true);
				controle.setSinalVermelho(false);
			
			}else if( !controle.isSinalVerde() && controle.isSinalAmarelo() && !controle.isSinalVermelho() ) {
				
				controle.setSinalVerde(false);
				controle.setSinalAmarelo(false);
				controle.setSinalVermelho(true);
				
			}else if( !controle.isSinalVerde() && !controle.isSinalAmarelo() && controle.isSinalVermelho()  ) {
				controle.setSinalVerde(true);
				controle.setSinalAmarelo(false);
				controle.setSinalVermelho(false);
			}
			
		}
		
	}
	
}
