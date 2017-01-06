package ca.alexandremoore.mtg.controller;

import ca.alexandremoore.mtg.assembler.MtgCardResourceAssembler;
import ca.alexandremoore.mtg.model.MtgCard;
import ca.alexandremoore.mtg.resource.MtgCardResource;
import ca.alexandremoore.mtg.resource.MtgSetResource;
import ca.alexandremoore.mtg.service.MtgCardService;
import ca.alexandremoore.mtg.service.MtgSetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.containsIgnoreCase;
import static org.apache.commons.lang3.StringUtils.isEmpty;

@RestController
@ExposesResourceFor(MtgSetResource.class)
@RequestMapping(value = "/cards")
public class MtgCardController {

    @Autowired
    private MtgCardService mtgCardService;

    @Autowired
    private MtgSetService mtgSetService;

    @Autowired
    private PagedResourcesAssembler<MtgCard> pagedResourcesAssembler;

    @Autowired
    private MtgCardResourceAssembler mtgCardResourceAssembler;

    @GetMapping(value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MtgCardResource findCard(@PathVariable String name) throws UnsupportedEncodingException {
        MtgCard mtgCard = mtgCardService.findByName(name);

        if (mtgCard != null) {
            MtgCardResource resource = mtgCardResourceAssembler.toResource(mtgCard);
            return resource;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "set")
    public PagedResources<MtgCardResource> findAllCards(@RequestParam(value = "set") String set, @PageableDefault(20) Pageable pageable) {
        List<MtgCard> allCardsInSet = mtgSetService.findAllCardsInSet(set, pageable);
        Page<MtgCard> page = new PageImpl<>(allCardsInSet, pageable, mtgSetService.findAllCardsInSet(set).size());
        return pagedResourcesAssembler.toResource(page, mtgCardResourceAssembler);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<MtgCardResource> findAllCards(@PageableDefault(20) Pageable pageable) {
        Page<MtgCard> page = new PageImpl<>(mtgCardService.findAll(pageable), pageable, mtgCardService.getSize());
        return pagedResourcesAssembler.toResource(page, mtgCardResourceAssembler);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, params = "multiverseid")
    public MtgCardResource findCard(@RequestParam(value = "multiverseid") Integer multiverseId) {
        MtgCard mtgCard = mtgSetService.findCardByMultiverseId(multiverseId);

        if (mtgCard != null) {
            MtgCardResource resource = mtgCardResourceAssembler.toResource(mtgCard);
            return resource;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<MtgCardResource> findCards(
            @RequestParam(value = "name", required = false) String name,
            @PageableDefault(20) Pageable pageable) throws UnsupportedEncodingException {

        List<MtgCard> allCardsFromAllSets = mtgSetService.findAllCardsFromAllSets();
        if (!isEmpty(name)) {
            allCardsFromAllSets = allCardsFromAllSets.stream().filter(card -> containsIgnoreCase(card.getName(), name)).collect(Collectors.toList());
        }

        Set<MtgCard> allUniqueCardsFromAllSets = new HashSet<>();

        for (MtgCard mtgCard : allCardsFromAllSets) {
            MtgCard foundCard = mtgCardService.findByName(mtgCard.getName());
            allUniqueCardsFromAllSets.add(foundCard);
        }

        int pageNumber = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        int total = allUniqueCardsFromAllSets.size();
        allUniqueCardsFromAllSets = allUniqueCardsFromAllSets.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toSet());
        Page<MtgCard> page = new PageImpl<>(new ArrayList<>(allUniqueCardsFromAllSets), pageable, total);
        return pagedResourcesAssembler.toResource(page, mtgCardResourceAssembler);


    }
}
