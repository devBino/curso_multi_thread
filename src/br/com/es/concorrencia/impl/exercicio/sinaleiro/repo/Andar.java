package br.com.es.concorrencia.impl.exercicio.sinaleiro.repo;

import java.util.concurrent.TimeUnit;

import br.com.es.concorrencia.impl.exercicio.sinaleiro.Sinaleiro;

public class Andar implements Runnable {

	private PainelControle controle;
	private Grafico grafico;
	
	public Andar(final Grafico grafico) {
		controle = PainelControle.getInstance();
		this.grafico = grafico;
	}
	
	@Override
	public void run() {
		while( !controle.isFim() ) {
			display();
		}
	}
	
	private void display() {
		
		while( controle.isSinalVermelho() ) {
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
				wait();
			}catch(Exception e) {}
		}
		
		grafico.desenhar();
		
		System.out.println( grafico.getRua() );
		
		if( controle.getPosicaoGrade() >= Sinaleiro.TM_RUA ) {
			controle.setFim(true);
			return;
		}
		
		int posGrade = controle.getPosicaoGrade();
		controle.setPosicaoGrade(++posGrade);
		
	}
	
}
