package nl.hva.c25.team1.digivault.service;

import nl.hva.c25.team1.digivault.model.FinancieelOverzicht;
import nl.hva.c25.team1.digivault.repository.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Erwin, studentnummer 500889293
 * @version 9-12-2021
 */

@Service
public class FinancieelOverzichtService {

    private RootRepository rootRepository;

    @Autowired
        public FinancieelOverzichtService(RootRepository rootRepository) {
        super();
        this.rootRepository = rootRepository;
    }

    public FinancieelOverzicht vindFinancieelOverzicht(int klantId) {
        System.out.println("service");
        FinancieelOverzicht financieelOverzicht = rootRepository.vindFinancieelOverzichtOpId(klantId);
        return financieelOverzicht;
    }
}
