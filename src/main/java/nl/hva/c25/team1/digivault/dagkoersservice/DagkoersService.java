package nl.hva.c25.team1.digivault.dagkoersservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import nl.hva.c25.team1.digivault.model.Asset;
import nl.hva.c25.team1.digivault.model.EuroKoers;
import nl.hva.c25.team1.digivault.repository.AssetDAO;
import nl.hva.c25.team1.digivault.repository.EuroKoersDAO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;


@Configuration
@EnableScheduling
@Service
public class DagkoersService {
    private final EuroKoersDAO euroKoersDAO;
    private final AssetDAO assetDAO;
    public final double DOLLARKOERS = 0.88;
    public final String URL = "https://api.coinranking.com/v2/coins";
    public final String PATH = "/data/coins";
    public final int MIN_ASSET = 1;
    public final int MAX_ASSET = 20;
    public final String SYMBOL = "symbol";
    public final String PRICE = "price";
    public final String ZONE = "CET";
    public final String OPHAALTIJD = "0 00 09 * * ?";

    public DagkoersService(EuroKoersDAO euroKoersDAO, AssetDAO assetDAO) {
        super();
        this.euroKoersDAO = euroKoersDAO;
        this.assetDAO = assetDAO;
    }

    @Scheduled(cron = OPHAALTIJD, zone = ZONE) // elke dag om 9 am
    public void dagkoersBuilder() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ResponseEntity<String> response
                = restTemplate.getForEntity(URL, String.class);
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode coins = root.at(PATH);
        bouwEuroKoersEnSlaOp(coins);
    }

    public void bouwEuroKoersEnSlaOp(JsonNode coins) {
        EuroKoers euroKoers = new EuroKoers();
        for (int i = MIN_ASSET; i <= MAX_ASSET; i++) {
            Asset asset = assetDAO.vindAssetOpId(i);
            for (JsonNode jsonNode : coins) {
                String afkorting = jsonNode.get(SYMBOL).asText();
                if ((asset.getAfkorting()).equals(afkorting)) {
                    slaEuroKoersOp(i, euroKoers,jsonNode);
                }
            }
        }
    }

    public void slaEuroKoersOp(int i, EuroKoers euroKoers, JsonNode jsonNode) {
        JsonNode dagkoers = jsonNode.get(PRICE);
        euroKoers.setAssetId(i);
        euroKoers.setEuroKoersId(i);
        euroKoers.setDatum(LocalDate.now());
        euroKoers.setKoers(dagkoers.asDouble() * DOLLARKOERS);
        euroKoersDAO.bewaarEuroKoersMetSK(euroKoers);
    }

    //        String accessKey = "coinrankingf65e600fd8d4915137af49c11c136d2619e87f7c49345115";
//            HttpHeaders headers = new HttpHeaders();
//            headers.set("x-access-token", accessKey);
}
