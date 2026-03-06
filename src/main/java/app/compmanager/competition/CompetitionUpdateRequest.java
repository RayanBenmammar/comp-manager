package app.compmanager.competition;

import java.time.LocalDate;

public class CompetitionUpdateRequest {
    public String name;

    public LocalDate startDate;

    public LocalDate endDate;

    public String timezone;

    public String location;

    public String description;

    public CompetitionStatus status;
}
