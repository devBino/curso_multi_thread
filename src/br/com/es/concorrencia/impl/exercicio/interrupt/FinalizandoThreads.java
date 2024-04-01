package br.com.es.concorrencia.impl.exercicio.interrupt;

import java.util.concurrent.TimeUnit;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;

@ExMap(numero = 3, titulo = "Método Interrupt para encerrar Threads")
public class FinalizandoThreads extends ExGeneric {

	Thread taskContSegundos, taskImprimeTexto;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		
		taskContSegundos = new Thread(new ContagemSegundos());
		taskContSegundos.setName("#TH_Contagem");
		
		taskImprimeTexto = new Thread(new ImpressaoTexto());
		taskImprimeTexto.setName("#TH_Impressao");
		
	}
	
	@Override
	public void processarExercicio() throws BusinessException {
		
		//testes com task de contagem
		taskContSegundos.start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch(Exception exception) {
			
		}
		
		taskContSegundos.interrupt();
		
		//testes com task de impressão
		taskImprimeTexto.start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		}catch(Exception exception) {
			
		}
		
		taskImprimeTexto.interrupt();
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		//NA
	}
	
}

class ContagemSegundos implements Runnable {
	
	@Override
	public void run() {
		try {
			for(int i=0; i<10; i++) {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(Thread.currentThread().getName() + " Pausa de 1 segundo...");
			}
		}catch(Exception exception) {
			System.err.println(Thread.currentThread().getName() + " foi interrompida...");
		}
	}
	
}

class ImpressaoTexto implements Runnable {
	
	@Override
	public void run() {
		imprimeAlgo();
	}
	
	private void imprimeAlgo() {
		
		try {
			TimeUnit.SECONDS.sleep(1);
			System.out.println(Thread.currentThread().getName() + " Imprimindo algo...");
		}catch(Exception exception) {
			System.err.println(Thread.currentThread().getName() + " foi interrompida...");
			return;
		}
		
		if( !Thread.currentThread().isInterrupted() ) {
			imprimeAlgo();
		}
		
	}
	
}
