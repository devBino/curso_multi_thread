package br.com.es.concorrencia.impl.exercicio.sinaleiro.repo;

public class PainelControle {

	private static PainelControle instance;
	
	private boolean sinalVerde, sinalVermelho, sinalAmarelo, fim;
	private int posicaoGrade;
	
	private PainelControle() {
		sinalVerde = true;
	}
	
	public static PainelControle getInstance() {
		
		if(instance == null) {
			instance = new PainelControle();
		}
		
		return instance;
		
	}
	
	public synchronized void setSinalAmarelo(boolean sinalAmarelo) {
		this.sinalAmarelo = sinalAmarelo;
		notify();
	}
	
	public synchronized boolean isSinalAmarelo() {
		return sinalAmarelo;
	}
	
	public synchronized void setSinalVerde(boolean sinalVerde) {
		this.sinalVerde = sinalVerde;
		notify();
	}
	
	public synchronized boolean isSinalVerde() {
		return sinalVerde;
	}
	
	public synchronized void setSinalVermelho(boolean sinalVermelho) {
		this.sinalVermelho = sinalVermelho;
		notify();
	}
	 
	public synchronized boolean isSinalVermelho() {
		return sinalVermelho;
	}
	
	public synchronized void setPosicaoGrade(int posicaoGrade) {
		this.posicaoGrade = posicaoGrade;
		notify();
	}
	
	public synchronized int getPosicaoGrade() {
		return posicaoGrade;
	}
	
	public synchronized void setFim(boolean fim) {
		this.fim = fim;
		notifyAll();
	}
	
	public synchronized boolean isFim() {
		return fim;
	}
	
	
}
