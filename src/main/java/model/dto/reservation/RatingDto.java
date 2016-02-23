package model.dto.reservation;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class RatingDto {
    @NotNull
    @Min(1)
    @Max(5)
    private Integer value;

    public RatingDto() {
    }

    public RatingDto(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
