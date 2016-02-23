package model.dto.reservation;

import java.util.List;

/**
 * @author - Srđan Milaković
 */
@SuppressWarnings("unused")
public class InvitationsDto {
    private List<Integer> userIds;

    public InvitationsDto() {
    }

    public InvitationsDto(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
