package nl.hva.c25.team1.digivault.dagkoersservice;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.AssetDAO;
import nl.hva.c25.team1.digivault.repository.EuroKoersDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class DagkoersService {

    private EuroKoersDAO euroKoersDAO;
    private AssetDAO assetDAO;

    public DagkoersService(EuroKoersDAO euroKoersDAO, AssetDAO assetDAO) {
        super();
        this.euroKoersDAO = euroKoersDAO;
        this.assetDAO = assetDAO;
    }

        @Scheduled(cron = "0 0 12 * * ?")// elke dag 12pm
    public void slaDagkoersenOp() throws JsonProcessingException {
        EuroKoers euroKoers = new EuroKoers();
        RestTemplate restTemplate = new RestTemplate();

        String url
                = "https://api.coinranking.com/v2/coins";

//      In Javascript in headers  x-access-token: "coinrankingf65e600fd8d4915137af49c11c136d2619e87f7c49345115";

        ResponseEntity<String> response
                = restTemplate.getForEntity(url, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode coins = root.at("/data/coins");

        for (int i = 1; i < 21; i++) {
            Asset asset = assetDAO.vindAssetOpId(i);
            if (coins.isArray()) {
                for (JsonNode jsonNode : coins) {
                    String afkorting = jsonNode.get("symbol").asText();
                    if ((asset.getAfkorting()).equals(afkorting)) {
                        JsonNode dagkoers = jsonNode.get("price");
                        euroKoers.setAssetId(i);
                        euroKoers.setEuroKoersId(i);
                        euroKoers.setDatum(LocalDate.now());
                        euroKoers.setKoers(dagkoers.asDouble());
                        euroKoersDAO.updateEuroKoers(euroKoers);
                    }
                }
            }
        }
    }


}
