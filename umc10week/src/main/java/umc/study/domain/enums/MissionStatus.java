package umc.study.domain.enums;

public enum MissionStatus {
    ONGOING,
    COMPLETED,
    CHALLENGING,
    EXPIRED;

    public MissionStatus changeStatus() {
        if (this.equals(CHALLENGING)) {
            return COMPLETED;
        }
        return this;
    }
}