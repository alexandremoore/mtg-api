package ca.alexandremoore.mtg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class JsonLoader {

    private static final Logger LOG = LoggerFactory.getLogger(JsonLoader.class);

    private static final String ALL_SETS_X_JSON = "AllSets-x.json";
    private static final String ALL_CARDS_JSON = "AllCards.json";

    private final URI allSetsX;
    private final URI allCards;

    private final RestTemplate restTemplate;

    @Autowired
    public JsonLoader(RestTemplate restTemplate) throws URISyntaxException {
        this.restTemplate = restTemplate;
        this.allSetsX = getClass().getClassLoader().getResource(ALL_SETS_X_JSON).toURI();
        this.allCards = getClass().getClassLoader().getResource(ALL_CARDS_JSON).toURI();
    }

    public byte[] loadAllSets() throws IOException {
        return load(allSetsX);
    }

    public byte[] loadAllCards() throws IOException {
        return load(allCards);
    }

    private byte[] load(URI uri) throws IOException {
        LOG.info("Loading {}", uri);
        byte[] data;
        if ("file".equals(uri.getScheme())) {
            data = readFile(uri);
        } else {
            data = restTemplate.getForObject(uri, byte[].class);
        }
        return data;
    }

    private static byte[] readFile(URI uri) throws IOException {
        Path path = Paths.get(uri);
        return Files.readAllBytes(path);
    }

}
