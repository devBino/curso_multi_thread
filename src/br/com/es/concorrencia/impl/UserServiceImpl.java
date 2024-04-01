package br.com.es.concorrencia.impl;

import br.com.fbm.debug.business.exception.BusinessException;
import br.com.fbm.debug.business.generic.ExGeneric;
import br.com.fbm.debug.business.service.annotations.UserService;
import br.com.fbm.debug.business.service.iface.ExUserService;

/**
 * Define o processador dos exercícios implementados
 *  
 * @author Fernando Bino Machado
 */
@UserService
public class UserServiceImpl implements ExUserService {

	@Override
	public void processar(ExGeneric pExGeneric) throws BusinessException {
		
		pExGeneric.iniciarExercicio();
		pExGeneric.processarExercicio();
		pExGeneric.finalizarExercicio();
		
	}
	
	@Override
	public void testeQuiz(ExGeneric pExGeneric, String pEnunciado, String[] pRespostas) throws BusinessException {

		ExUserService.super.testeQuiz(pExGeneric, pEnunciado, pRespostas);
		
	}
	
}
