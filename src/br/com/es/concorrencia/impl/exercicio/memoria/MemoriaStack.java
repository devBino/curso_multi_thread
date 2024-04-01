package br.com.es.concorrencia.impl.exercicio.memoria;

import java.util.Arrays;
import java.util.stream.Collectors;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;

@ExMap(numero = 4, titulo = "Memória na Stack durante execução de Threads")
public class MemoriaStack extends ExGeneric {

	Thread task;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		task = new Thread(new TaskBuildNameStack("fernando", "bino", "machado"));
		task.setName("#TH_BuildName");
	}
	
	@Override
	public void processarExercicio() throws BusinessException {
		task.start();
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	
}

class TaskBuildNameStack implements Runnable {
	
	private String[] partes;
	
	public TaskBuildNameStack(String... pPartes) {
		partes = pPartes;
	}
	
	@Override
	public void run() {
	
		//operação na variavel nome, na memória de stack
		//no espaço de memória da stack
		String nome = Arrays
				.stream(partes)
				.collect(Collectors.joining(" "));
		
		System.out.println(Thread.currentThread().getName() + " buildou o nome " + nome);
		
	}
	
}
