package task.simpleShop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name = "organisation_id")
    private Organisation organisation;

    @NonNull
    @Column(name = "price")
    private double price;

    @Column(name = "amount")
    private long amount;

    @Column(name = "discount")
    private String discount;

    private List<String> keywords;

    @Enumerated(EnumType.STRING)
    @Column(name = "characteristics")
    private Characteristics characteristics;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private Rating rating;
}
