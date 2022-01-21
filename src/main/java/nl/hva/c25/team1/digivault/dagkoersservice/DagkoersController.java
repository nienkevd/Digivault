package nl.hva.c25.team1.digivault.dagkoersservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import nl.hva.c25.team1.digivault.model.EuroKoers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DagkoersController {

    private DagkoersService dagkoersService;

    @Autowired
    public DagkoersController(DagkoersService dagkoersService) {
        super();
        this.dagkoersService = dagkoersService;
    }

    @CrossOrigin
    @GetMapping("/dagkoersen")
    public String geefDagkoersen() throws JsonProcessingException {
        dagkoersService.slaDagkoersenOp();
        return "Geslaagd!";
    }

}
