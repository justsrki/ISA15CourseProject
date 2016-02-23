package model.dto.reservation;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class ReservationDto {

    public static final long HOUR_MILLISECONDS = 60 * 60 * 1000;
    private Integer id;
    @Future
    private Date startDate;
    @Min(1)
    @NotNull
    private Integer length;
    @NotNull
    private int restaurantId;
    @NotEmpty
    private List<Integer> tables;

    public ReservationDto() {
    }

    public ReservationDto(Date startDate, Integer length, int restaurantId, List<Integer> tables) {
        this.startDate = startDate;
        this.length = length;
        this.restaurantId = restaurantId;
        this.tables = tables;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getEndDate() {
        return new Date(startDate.getTime() + length * HOUR_MILLISECONDS - 1000);
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

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<Integer> getTables() {
        return tables;
    }

    public void setTables(List<Integer> tables) {
        this.tables = tables;
    }
}
