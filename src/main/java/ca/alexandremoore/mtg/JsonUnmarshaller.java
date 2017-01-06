package ca.alexandremoore.mtg;

import ca.alexandremoore.mtg.model.MtgCard;
import ca.alexandremoore.mtg.model.MtgSet;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class JsonUnmarshaller {

    private static final Logger LOG = LoggerFactory.getLogger(JsonUnmarshaller.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public JsonUnmarshaller(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public List<MtgSet> unmarshallSets(byte[] data) throws IOException {
        LOG.info("Unmarshalling mtgjson Sets data");

        Map<String, MtgSet> mtgSets = objectMapper.readValue(data, new TypeReference<Map<String, MtgSet>>() {
        });

        LOG.info("Done unmarshalling mtgjson Sets data");
        return new ArrayList<>(mtgSets.values());
    }

    public List<MtgCard> unmarshallCards(byte[] data) throws IOException {
        LOG.info("Unmarshalling mtgjson Cards data");

        Map<String, MtgCard> cards = objectMapper.readValue(data, new TypeReference<Map<String, MtgCard>>() {
        });

        LOG.info("Done unmarshalling mtgjson Cards data");
        return new ArrayList<>(cards.values());
    }

}
