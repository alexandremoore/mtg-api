package ca.alexandremoore.mtg.service;


import ca.alexandremoore.mtg.model.MtgCard;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
public interface MtgCardService {

    List<MtgCard> findAll();

    List<MtgCard> findAll(Pageable pageable);

    MtgCard findByName(String name) throws UnsupportedEncodingException;

    int getSize();
}
