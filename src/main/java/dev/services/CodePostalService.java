package dev.services;

import dev.entities.CodePostal;
import dev.repositories.ICodePostalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
