package ca.alexandremoore.mtg.assembler;

import ca.alexandremoore.mtg.controller.MtgCardController;
import ca.alexandremoore.mtg.model.MtgCard;
import ca.alexandremoore.mtg.model.MtgSet;
import ca.alexandremoore.mtg.resource.MtgCardResource;
import ca.alexandremoore.mtg.service.MtgSetService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.lang3.CharEncoding.UTF_8;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MtgCardResourceAssembler extends ResourceAssemblerSupport<MtgCard, MtgCardResource> {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MtgCardResourceAssembler.class);

    @Autowired
    private MtgSetService mtgSetService;

    @Autowired
    private MtgSetResourceAssembler mtgSetResourceAssembler;

    public MtgCardResourceAssembler() {
        super(MtgCardController.class, MtgCardResource.class);
    }

    @Override
    public MtgCardResource toResource(MtgCard entity) {
        String cardId = generateId(entity.getName());
        MtgCardResource resource = createResourceWithId(cardId, entity);
        resource.setCardId(cardId);
        resource.setName(entity.getName());
        resource.setCmc(entity.getCmc());
        resource.setColors(entity.getColors());
        resource.setManaCost(entity.getManaCost());
        resource.setPower(entity.getPower());
        resource.setToughness(entity.getToughness());
        resource.setText(entity.getText());
        resource.setTypes(entity.getTypes());
        resource.setSubtypes(entity.getSubtypes());

        Set<String> allSetsForCard = mtgSetService.findAllSetsForCard(entity.getName());
        Set<MtgSet> editions = new HashSet<>();
        for (String set : allSetsForCard) {
            MtgSet mtgSet = mtgSetService.findByCode(set);
            MtgCard mtgCard = mtgSetService.findCardinSet(entity.getName(), mtgSet);
            mtgSet.setRarity(mtgCard.getRarity());
            mtgSet.setArtist(mtgCard.getArtist());
            mtgSet.setMultiverseid(mtgCard.getMultiverseid());
            mtgSet.setImageName(mtgCard.getImageName());
            mtgSet.setFlavor(mtgCard.getFlavor());
            mtgSet.setNumber(mtgCard.getNumber());
            mtgSet.setLayout(mtgCard.getLayout());
            editions.add(mtgSet);

            // Add a link to card in set
            if (mtgCard.getMultiverseid() != null) {
                resource.add(linkTo(methodOn(MtgCardController.class).findCard(mtgCard.getMultiverseid())).withRel("link to card in set " + mtgSet.getName()));
            }

        }
        resource.setEditions(mtgSetResourceAssembler.toResources(editions));
        return resource;
    }

    private String generateId(String name) {
        try {
            return URLEncoder.encode(name, UTF_8).toLowerCase();
        } catch (UnsupportedEncodingException e) {
            LOG.warn(e.getMessage(), e);
            return name.toLowerCase();
        }
    }
}
