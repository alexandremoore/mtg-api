package ca.alexandremoore.mtg.assembler;

import ca.alexandremoore.mtg.controller.MtgCardController;
import ca.alexandremoore.mtg.controller.MtgSetController;
import ca.alexandremoore.mtg.model.MtgSet;
import ca.alexandremoore.mtg.resource.MtgSetResource;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class MtgSetResourceAssembler extends ResourceAssemblerSupport<MtgSet, MtgSetResource> {

    private Pageable pageable;

    public MtgSetResourceAssembler() {
        super(MtgSetController.class, MtgSetResource.class);
    }

    @Override
    public MtgSetResource toResource(MtgSet entity) {
        MtgSetResource resource = createResourceWithId(entity.getCode(), entity);
        resource.setBlock(entity.getBlock());
        resource.setBorder(entity.getBorder());
        resource.setCode(entity.getCode());
        resource.setName(entity.getName());
        resource.setReleaseDate(entity.getReleaseDate());
        resource.setType(entity.getType());
        // Extra for card in set
        resource.setRarity(entity.getRarity());
        resource.setArtist(entity.getArtist());
        resource.setMultiverseid(entity.getMultiverseid());
        resource.setImageName(entity.getImageName());
        resource.setFlavor(entity.getFlavor());
        resource.setNumber(entity.getNumber());
        resource.setLayout(entity.getLayout());

        resource.add(linkTo(methodOn(MtgCardController.class).findAllCards(entity.getCode(), pageable)).withRel("cards"));

        return resource;
    }
}
