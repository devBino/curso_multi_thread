package br.com.es.concorrencia.app;

import br.com.fbm.debug.StartApplication;

/**
 * Inicializa aplicação de estudos do curso da udemy
 * sobre MultiThreading
 * 
 * @author Fernando Bino Machado
 */
public class StartUserApplication extends StartApplication { 
	
	/**
	 * Define pacote base dos exercícios e inicia aplicação
	 * @param args
	 */
	public static void main(String[] args) {
		setPackageExercicios("src","br","com","es","concorrencia","impl");
		exibirWindow();
	}

}
