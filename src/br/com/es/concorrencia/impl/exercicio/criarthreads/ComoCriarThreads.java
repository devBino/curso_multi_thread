package br.com.es.concorrencia.impl.exercicio.criarthreads;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;
import br.com.fbm.debug.business.service.annotations.Repetir;

@Repetir(1)
@ExMap(numero = 1, titulo = "Exemplos de Criação de Threads")
public class ComoCriarThreads extends ExGeneric {

	private Thread task1, task2, task3;
	
	@Override
	public void iniciarExercicio() throws BusinessException {

		//passando implementação anônima de Runnable
		task1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName() + " Implementação Anônima de Runnable");
			}
		});

		//passando uma implementação de Runnable para o construtor
		TamanhoTexto tamanhoTexto = new TamanhoTexto("Rodando Task1 @");
		task2 = new Thread(tamanhoTexto);
		
		task3 = new Thread(()->System.out.println(Thread.currentThread().getName() + " Lambda diretamente no construtor da Thread"));
		
	}
	
	@Override
	public void processarExercicio() throws BusinessException {
		
		//definindo nomes para as Threads
		task1.setName("#Task1");
		task2.setName("#Task2");
		task3.setName("#Task3");
		
		//definindo prioridades de execução 
		//na pratica não tem muito efeito
		//porque o processador decide quem vai executar primeiro
		//com base em uma combinação de vários algoritmos de escalonamento
		//bem com de outros fatores como memória disponível entre outros
		task1.setPriority(Thread.MIN_PRIORITY);
		task2.setPriority(Thread.NORM_PRIORITY);
		task3.setPriority(Thread.MAX_PRIORITY);
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		task1.start();
		task2.start();
		task3.start();
		System.out.println("===========================================================\n");
	}
	
}

class TamanhoTexto implements Runnable {
	
	private String texto;
	
	TamanhoTexto(String texto) {
		this.texto = texto;
	}
	
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " => Tamanho texto " + this.texto + " = " + this.texto.length());
	}
	
}