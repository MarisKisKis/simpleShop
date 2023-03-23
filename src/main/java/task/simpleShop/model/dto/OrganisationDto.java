package task.simpleShop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.Item;

import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class OrganisationDto {

    private String name;

    private String description;

    private byte[] image;

    private String imageContentType;

    private List<Item> items;
}
