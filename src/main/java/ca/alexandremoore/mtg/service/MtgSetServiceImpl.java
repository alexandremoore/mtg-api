package ca.alexandremoore.mtg.service;

import ca.alexandremoore.mtg.JsonLoader;
import ca.alexandremoore.mtg.JsonUnmarshaller;
import ca.alexandremoore.mtg.model.MtgCard;
import ca.alexandremoore.mtg.model.MtgSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MtgSetServiceImpl implements MtgSetService {
    private static final Logger LOG = LoggerFactory.getLogger(MtgSetServiceImpl.class);

    @Autowired
    private JsonLoader jsonLoader;

    @Autowired
    private JsonUnmarshaller jsonUnmarshaller;

    private List<MtgSet> mtgSets;

    private List<MtgCard> allCardsInSetsList = new ArrayList<>();
    private Set<MtgCard> allCardsInSetsSet = new HashSet<>();

    @PostConstruct
    private void loadAndUnmarshalData() throws IOException {
        byte[] data = jsonLoader.loadAllSets();
        mtgSets = jsonUnmarshaller.unmarshallSets(data);

        LOG.info("Start fetching all cards from all sets...");
        for (MtgSet mtgSet : mtgSets) {
            allCardsInSetsSet.addAll(mtgSet.getCards());
        }
        allCardsInSetsList.addAll(new ArrayList<>(allCardsInSetsSet));
        LOG.info("Done fetching all cards from all sets.");

    }

    @Override
    public List<MtgSet> findAll() {
        return mtgSets;
    }

    @Override
    public List<MtgSet> findAll(Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return mtgSets.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public MtgSet findByCode(String code) {
        return mtgSets.stream()
                .filter(set -> code.equalsIgnoreCase(set.getCode()))
                .findFirst().orElse(null);
    }

    @Override
    public List<MtgCard> findAllCardsInSet(String code, Pageable pageable) {
        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        return (findByCode(code).getCards()).stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @Override
    public List<MtgCard> findAllCardsFromAllSets() {
        return allCardsInSetsList;
    }

    @Override
    public List<MtgCard> findAllCardsInSet(String code) {
        return (findByCode(code).getCards());
    }

    @Override
    public Set<String> findAllSetsForCard(String cardName) {
        for (MtgSet mtgSet : mtgSets) {
            for (MtgCard mtgCard : mtgSet.getCards()) {
                if (cardName.equalsIgnoreCase(mtgCard.getName())) {
                    return mtgCard.getPrintings();
                }
            }
        }

        return Collections.emptySet();

    }

    @Override
    public MtgCard findCardinSet(String cardName, MtgSet set) {
        return set.getCards().stream().filter(card -> cardName.equalsIgnoreCase(card.getName())).findFirst().orElse(null);
    }

    private MtgCard findCardInSetByMultiverseId(Integer multiverseId, MtgSet set) {
        if (multiverseId == null) return null;
        return set.getCards().stream()
                .filter(card -> (card.getMultiverseid() != null) ? card.getMultiverseid().intValue() == multiverseId.intValue() : false)
                .findFirst().orElse(null);
    }

    @Override
    public MtgCard findCardByMultiverseId(Integer multiverseId) {
        for (MtgSet mtgSet : mtgSets) {
            MtgCard mtgCard = findCardInSetByMultiverseId(multiverseId, mtgSet);
            if (mtgCard != null) return mtgCard;
        }

        return null;
    }

    @Override
    public int getSize() {
        return mtgSets.size();
    }

    @Override
    public int getAllCardsFromAllSetsSize() {
        return allCardsInSetsList.size();
    }
}
