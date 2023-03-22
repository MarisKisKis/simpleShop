package task.simpleShop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum Characteristics {

    FIRST_VALUE("value"),
    SECOND_VALUE(100),
    THIRD_VALUE("another value"),
    LAST_VALUE(50);

    private String firstValue;
    private int secondValue;
    private String thirdValue;
    private int lastValue;

    Characteristics(String value) {
        this.firstValue = value;
    }

    Characteristics(int i) {
        this.secondValue = i;
    }
}
