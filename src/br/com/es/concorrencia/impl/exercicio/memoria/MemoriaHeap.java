package br.com.es.concorrencia.impl.exercicio.memoria;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;

@ExMap(numero = 5, titulo = "Memória Heap durante execução de Threads")
public class MemoriaHeap extends ExGeneric {

	Thread task1,task2;
	private Nome nome;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		
		nome = new Nome();
		
		task1 = new Thread(new TaskBuildNameHeap(nome, "homer"));
		task1.setName("#TH_Homer");
		
		task2 = new Thread(new TaskBuildNameHeap(nome, "simpson"));
		task2.setName("#TH_Simpson");
		
	}
	
	@Override
	public void processarExercicio() throws BusinessException {
		task1.start();
		task2.start();
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		
		saida
			.append("[")
			.append(nome.conteudo.toString())
			.append("]\n")
			.append("=========================================================================");
		
		exibirSaida();
		
	}
	
}

class Nome {
	
	public StringBuilder conteudo;
	
	public Nome() {
		conteudo = new StringBuilder();
	}
	
}

class TaskBuildNameHeap implements Runnable {
	
	private String[] partes;
	private Nome nome;
	
	public TaskBuildNameHeap(final Nome pNome, String... pPartes) {
		partes = pPartes;
		nome = pNome;
	}
	
	@Override
	public void run() {
	
		//operação no atributo nome, na memória de heap
		//no espaço de memória da heap
		//já que contém a referência de uma variavel na memória heap
		//recebida no construtor dessa impl de Runnable
		for(String p : partes) {
			nome.conteudo
				.append(p)
				.append(" ");
		}
		
		System.out.println(Thread.currentThread().getName() + " buildou o nome [" + nome.conteudo.toString() + "] em " + nome);
		
	}
	
}
