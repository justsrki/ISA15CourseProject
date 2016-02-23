package model.dto.reservation;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class TablesStateRequest {
    @NotNull
    private Integer restaurantId;
    @Future
    private Date startDate;
    @NotNull
    @Min(1)
    @Max(5)
    private Integer length;

    public TablesStateRequest() {
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Date getEndDate() {
        return new Date(startDate.getTime() + length * ReservationDto.HOUR_MILLISECONDS - 1000);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
