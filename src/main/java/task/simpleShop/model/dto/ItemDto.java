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

    private String name;

    private String description;

    private double price;

    private long amount;

    private String keywords;

    private Characteristics characteristics;

    private Rating rating;

    private List<Feedback> feedbacks;

    private DiscountShortDto discountDto;

    private OrganisationShortDto organisationDto;


    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class DiscountShortDto {
        private long id;
        private Integer value;
    }

    @Getter
    @Setter
    @Builder
    @Jacksonized
    public static class OrganisationShortDto {
        private long id;
        private String name;
    }
}
