package task.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import task.simpleShop.model.dto.FeedbackDto;
import task.simpleShop.model.dto.ItemDto;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @NonNull
    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private long amount;

    @Column(name = "keywords")
    private String keywords;

    @Enumerated(EnumType.STRING)
    @Column(name = "characteristics")
    private Characteristics characteristics;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;

    @OneToMany(mappedBy = "item",
            cascade = CascadeType.MERGE,
            fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Feedback> feedbacks;
    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

}
