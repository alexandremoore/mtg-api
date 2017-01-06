package ca.alexandremoore.mtg.service;

import ca.alexandremoore.mtg.JsonLoader;
import ca.alexandremoore.mtg.JsonUnmarshaller;
import ca.alexandremoore.mtg.model.MtgCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.CharEncoding.UTF_8;

@Service
public class MtgCardServiceImpl implements MtgCardService {
    @Autowired
    private JsonLoader jsonLoader;

    @Autowired
    private JsonUnmarshaller jsonUnmarshaller;

    private List<MtgCard> mtgCards;

    @PostConstruct
    private void loadAndUnmarshalData() throws IOException {
        byte[] data = jsonLoader.loadAllCards();
        mtgCards = jsonUnmarshaller.unmarshallCards(data);
    }

    @Override
    public List<MtgCard> findAll() {
        return mtgCards;
    }

    @Override
    public List<MtgCard> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return mtgCards.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public MtgCard findByName(String name) throws UnsupportedEncodingException {
        String decodedName = URLDecoder.decode(name, UTF_8);
        return mtgCards.stream()
                .filter(card -> decodedName.equalsIgnoreCase(card.getName()))
                .findFirst().orElse(null);
    }

    @Override
    public int getSize() {
        return mtgCards.size();
    }
}
