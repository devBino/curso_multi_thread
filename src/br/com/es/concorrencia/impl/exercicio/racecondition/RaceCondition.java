package br.com.es.concorrencia.impl.exercicio.racecondition;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;
import br.com.fbm.debug.business.service.annotations.Repetir;

@Repetir(100)
@ExMap(numero = 8, titulo = "Race Condition, Tratamento para Condição de Corrida")
public class RaceCondition extends ExGeneric {

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
		
		try {
			fernando.join();
			bino.join();
			machado.join();
		}catch(Exception exception) {
			
		}
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		
		if( !nome.nomeCompleto.toString().equals("fernando bino machado ") ) {
			saida
				.append("\n")
				.append(nome.nomeCompleto.toString());
	
			System.out.println(saida.toString());
		}
		
		saida.delete(0, saida.length());
		contador++;
		
		if( contador >= 100 ) {
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
	
	public void incrementarENotificarThreadsParaIniciar() {
		indexParteAtual++;
	}
	
	public void addParteNome(String parte) {
		nomeCompleto.append(parte).append(" ");
		indexParteAtual++;
	}

	public int getIndex() {
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

		nome.addParteNome(parteNome);
		
	}
	
}


