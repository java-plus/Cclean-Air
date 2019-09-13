package dev.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.entities.CodePostal;
import dev.repositories.ICodePostalRepository;

@Service
@Transactional
public class CodePostalService {

	private ICodePostalRepository codePostalRepository;

	@Autowired
	public CodePostalService(ICodePostalRepository codePostalRepository) {
		this.codePostalRepository = codePostalRepository;
	}

	public void sauvegarderCodePostal(CodePostal cp) {
		codePostalRepository.save(cp);
	}

}
