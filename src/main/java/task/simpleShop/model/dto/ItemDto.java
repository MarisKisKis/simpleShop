package task.simpleShop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@Jacksonized
public class ItemDto {

    private long id;

    @NotBlank
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @NotBlank
    private double price;

    @NotBlank
    private long amount;

    private List<String> keywords;

    private Characteristics characteristics;

    @NotBlank
    private Rating rating;

    private List<FeedbackDto> feedbacks;

}
