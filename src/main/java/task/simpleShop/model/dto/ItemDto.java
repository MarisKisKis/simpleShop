package task.simpleShop.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.*;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@Builder
@Jacksonized
public class ItemDto {

    private long id;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private double price;

    @NotBlank
    private long amount;

    private String keywords;

    private Characteristics characteristics;

    @NotBlank
    private Rating rating;

    private List<Feedback> feedbacks;

    @NotBlank
    private DiscountDto discountDto;

    @NotBlank
    private OrganisationDto organisationDto;


    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class DiscountDto {
        private Integer value;
    }

    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class OrganisationDto {
        private String name;
    }
}
