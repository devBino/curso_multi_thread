package br.com.es.concorrencia.impl.exercicio.datarace;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.ExMap;
import br.com.fbm.debug.business.service.annotations.Repetir;

@Repetir(10)
@ExMap(numero = 7, titulo = "Data Racing, concorrência de dados.")
public class DataRace extends ExGeneric {

	static boolean SINC = false;
	static int MAX_LOOP = 2000;
	static int DELAY = 5;
	
	Thread taskIncrement, taskLogger;
	Increment increment;
	
	@Override
	public void iniciarExercicio() throws BusinessException {
		increment = new Increment();
		taskIncrement = new Thread(new Logger(increment));
		taskLogger = new Thread(new Incrementable(increment));
	}
	
	@Override
	public void processarExercicio() throws BusinessException {
		
		taskIncrement.start();
		taskLogger.start();
		
		int totalDelay = MAX_LOOP * 2;
		
		try {
			TimeUnit.MILLISECONDS.sleep(totalDelay);
		}catch(final Exception exception) {}
		
	}
	
	@Override
	public void finalizarExercicio() throws BusinessException {
		
		saida
			.append("OCORRÊNCIAS:\n")
			.append( 
				increment
					.getLogs()
					.stream()
					.filter(s -> s.contains("ERRO"))
					.collect(Collectors.joining()))
			.append("===================================================================\n");
		
		exibirSaida();
		
	}
	
}

class Logger implements Runnable {
	
	Increment increment;
	
	public Logger(final Increment increment) {
		this.increment = increment;
	}
	
	@Override
	public void run() {
		for(int i=0; i<DataRace.MAX_LOOP; i++) {
			if( !DataRace.SINC ) {
				increment.incrementUnsafe();
				continue;
			}
			increment.incrementSafe();
		}
	}
	
}

class Incrementable implements Runnable {
	
	Increment increment;
	
	public Incrementable(final Increment increment) {
		this.increment = increment;
	}
	
	@Override
	public void run() {
		for(int i=0; i<DataRace.MAX_LOOP; i++) {
			if( !DataRace.SINC ) {
				increment.logUnsafe();
				continue;
			}
			increment.logSafe();
		}
	}
	
}

class Increment {
	
	int x, y;
	List<String> logs;
	
	public Increment(){
		logs = new ArrayList<>();
	}
	
	public synchronized void incrementSafe() {
		x++;
		y++;
	}
	
	public void incrementUnsafe() {
		x++;
		y++;
	}
	
	public synchronized void logSafe() {
		
		String flagStatus = x!=y ? "ERRO" : "SUCESSO";
		
		String log = new StringBuilder()
				.append("x ")
				.append(x)
				.append(" y ")
				.append(y)
				.append(" ")
				.append(flagStatus)
				.append("\n")
				.toString();
		
		logs.add(log);
		
	}

	public void logUnsafe() {
		
		String flagStatus = x!=y ? "ERRO" : "SUCESSO";
		
		String log = new StringBuilder()
				.append("x ")
				.append(x)
				.append(" y ")
				.append(y)
				.append(" ")
				.append(flagStatus)
				.append("\n")
				.toString();
		
		logs.add(log);
		
	}
	
	public List<String> getLogs() {
		return logs;
	}
	
}
