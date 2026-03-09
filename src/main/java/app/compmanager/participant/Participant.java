package app.compmanager.participant;
import java.time.LocalDate;
import java.util.UUID;

import app.compmanager.competition.Competition;
import app.compmanager.division.Division;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Participant extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @NotBlank()
    @Column(name = "first_name", nullable = false)
    public String firstName;

    @NotBlank()
    @Column(name = "last_name", nullable = false)
    public String lastName;

    @Email(message = "Participant email must be valid")
    @Column(unique = true)
    public String email;

    @Enumerated(EnumType.STRING)
    public ParticipantGender gender;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "competition_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public Competition competition;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "division_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "competition"})
    public Division division;   

    @NotNull()
    @Enumerated(EnumType.STRING)
    public ParticipantStatus status = ParticipantStatus.REGISTERED;
    
    @NotNull()
    @Column(name = "registration_date", nullable = false)
    public LocalDate registrationDate = LocalDate.now();
    
}
