package task.simpleShop.model;

import lombok.Getter;

@Getter
public enum Rating {
    VERY_BAD(1),
    BAD(2),
    AVARAGE(3),
    GOOD(4),
    PERFECT(5);

    private int rating;
    Rating(int i) {
        this.rating = i;
    }
}
