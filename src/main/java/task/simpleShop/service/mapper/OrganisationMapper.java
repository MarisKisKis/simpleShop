package task.simpleShop.service.mapper;

import task.simpleShop.model.Organisation;
import task.simpleShop.model.dto.OrganisationDto;

public class OrganisationMapper {

    public static Organisation toOrganisation(OrganisationDto organisationDto) {
        return Organisation.builder()
                .name(organisationDto.getName())
                .description(organisationDto.getDescription())
                .image(organisationDto.getImage())
                .imageContentType(organisationDto.getImageContentType())
                .items(organisationDto.getItems())
                .build();
    }
}
