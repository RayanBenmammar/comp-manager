package app.compmanager.competition;

import java.time.LocalDate;
import java.util.UUID;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Competition extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank(message = "Competition name is mandatory")
    @Column(nullable = false)
    public String name;

    @NotNull(message = "Competition start date is mandatory")
    @Column(name = "start_date", nullable = false)
    public LocalDate startDate;

    @Column(name = "end_date")
    public LocalDate endDate;

    @NotBlank(message = "Competition timezone is mandatory")
    @Column(nullable = false)
    public String timezone;

    public String location;

    public String description;

    @NotNull()
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public CompetitionStatus status = CompetitionStatus.DRAFT;
    
}
