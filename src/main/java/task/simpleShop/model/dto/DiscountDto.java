package task.simpleShop.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.Item;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@Jacksonized
public class DiscountDto {
    @NotNull
    private Integer value;
    @NotNull
    private LocalDateTime start;
    @NotNull
    private LocalDateTime end;

    private List<Item> items;
}
