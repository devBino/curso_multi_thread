package br.com.es.concorrencia.impl.exercicio.raceconditiontratamento;

import java.util.concurrent.TimeUnit;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;
import br.com.fbm.debug.business.service.annotations.Repetir;

@Repetir(10)
@ExMap(numero = 9, titulo = "Race Condition, Tratamento para Condição de Corrida")
public class RaceConditionTratamento extends ExGeneric {

	static int DELAY = 200;
	Integer contador;
	
	Nome nome;
	Thread fernando, bino, machado;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		
		if(contador == null) {
			contador = 0;
		}
		
		nome = new Nome();
		fernando = new Thread(new TaskAppend(nome, "fernando", 1));
		bino = new Thread(new TaskAppend(nome, "bino", 2));
		machado = new Thread(new TaskAppend(nome, "machado", 3));
		
	}
	
	@Override
	public void processarExercicio() throws BusinessException {

		fernando.start();
		bino.start();
		machado.start();
		
		nome.incrementarENotificarThreadsParaIniciar();
		
		try {
			fernando.join();
			bino.join();
			machado.join();
		}catch(Exception exception) {
			
		}
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		
		saida
			.append("\n")
			.append(nome.nomeCompleto.toString());

		System.out.println(saida.toString());
		
		saida.delete(0, saida.length());
		contador++;
		
		if( contador >= 1 ) {
			System.out.println("\n\nFim Exercício\n\n");
		}
		
	}
	
}

class Nome {
	
	public StringBuilder nomeCompleto;
	public int indexParteAtual;
	
	public Nome() {
		nomeCompleto = new StringBuilder();
	}
	
	public synchronized void incrementarENotificarThreadsParaIniciar() {
		indexParteAtual++;
		notify();
	}
	
	public synchronized void addParteNome(String parte) {
		nomeCompleto.append(parte).append(" ");
		indexParteAtual++;
		notify();
	}

	public synchronized int getIndex() {
		return indexParteAtual;
	}
	
	
}

class TaskAppend implements Runnable {
	
	Nome nome;
	String parteNome;
	int indiceTask;
	
	public TaskAppend(final Nome nome, String parteNome, int indiceTask) {
		this.nome = nome;
		this.parteNome = parteNome;
		this.indiceTask = indiceTask;
	}
	
	@Override
	public void run() {

		while(nome.getIndex() < indiceTask) {
			try {
				TimeUnit.SECONDS.sleep(1);
				System.out.println(parteNome + " esperado...");
				wait();
			}catch(Exception exception) {}
		}

		System.out.println(parteNome + " adicionado!!!");
		nome.addParteNome(parteNome);
		
	}
	
}


