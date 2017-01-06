package ca.alexandremoore.mtg.controller;

import ca.alexandremoore.mtg.assembler.MtgSetResourceAssembler;
import ca.alexandremoore.mtg.model.MtgSet;
import ca.alexandremoore.mtg.resource.MtgSetResource;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ExposesResourceFor(MtgSetResource.class)
@RequestMapping(value = "/sets")
public class MtgSetController {

    @Autowired
    private MtgSetService mtgSetService;

    @Autowired
    private PagedResourcesAssembler<MtgSet> pagedResourcesAssembler;

    @Autowired
    private MtgSetResourceAssembler mtgSetResourceAssembler;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedResources<MtgSetResource> findAllSets(@PageableDefault(20) Pageable pageable) {
        Page<MtgSet> page = new PageImpl<>(mtgSetService.findAll(pageable), pageable, mtgSetService.getSize());
        return pagedResourcesAssembler.toResource(page, mtgSetResourceAssembler);
    }

    @GetMapping(value = "/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public MtgSetResource findSet(@PathVariable String code) {
        MtgSet mtgSet = mtgSetService.findByCode(code);

        if (mtgSet != null) {
            MtgSetResource resource = mtgSetResourceAssembler.toResource(mtgSet);
            return resource;
        } else {
            throw new ResourceNotFoundException();
        }

    }
}
