package ca.alexandremoore.mtg.service;

import ca.alexandremoore.mtg.model.MtgCard;
import ca.alexandremoore.mtg.model.MtgSet;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface MtgSetService {

    List<MtgSet> findAll();

    List<MtgSet> findAll(Pageable pageable);

    MtgSet findByCode(String code);

    List<MtgCard> findAllCardsInSet(String code);

    List<MtgCard> findAllCardsInSet(String code, Pageable pageable);

    List<MtgCard> findAllCardsFromAllSets();

    Set<String> findAllSetsForCard(String cardName);

    MtgCard findCardinSet(String cardName, MtgSet set);

    MtgCard findCardByMultiverseId(Integer multiverseId);

    int getSize();

    int getAllCardsFromAllSetsSize();
}
