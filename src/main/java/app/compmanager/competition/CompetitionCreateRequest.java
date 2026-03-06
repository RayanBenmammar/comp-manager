package app.compmanager.competition;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CompetitionCreateRequest {
    @NotBlank(message = "Competition name is mandatory")
    public String name;

    @NotNull(message = "Competition start date is mandatory")
    public LocalDate startDate;

    public LocalDate endDate;

    @NotBlank(message = "Competition timezone is mandatory")
    public String timezone;

    public String location;

    public String description;
}
