package app.compmanager.competition;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;
import jakarta.transaction.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CompetitionService {

    public List<Competition> getAllCompetitions() {
        return Competition.listAll();
    }

    public Competition getCompetitionById(UUID id) {
        Competition competition = Competition.findById(id);
        if(competition == null) {
            throw new NotFoundException("Competition with id " + id + " not found");
        }
        return competition;
    }

    @Transactional
    public Competition createCompetition(CompetitionCreateRequest request) {
        if(request.startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }
        if(request.endDate != null && request.startDate.isAfter(request.endDate)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        if(!isValidTimezone(request.timezone)) {
            throw new IllegalArgumentException("Invalid timezone");
        }
        Competition competition = new Competition();
        competition.name = request.name;
        competition.startDate = request.startDate;
        competition.endDate = request.endDate;
        competition.timezone = request.timezone;
        competition.location = request.location;
        competition.description = request.description;
        competition.persist();

        return competition;
    }
    

    @Transactional
    public Competition updateCompetition(UUID id, CompetitionUpdateRequest request) {
        Competition competition = Competition.findById(id);
        if(competition == null) {
            throw new NotFoundException("Competition with id " + id + " not found");
        }
        if(request.name != null) {
            competition.name = request.name;
        }
        if(request.startDate != null) {
            competition.startDate = request.startDate;
        }
        if(request.endDate != null) {
            competition.endDate = request.endDate;
        }
        if(request.timezone != null) {
            competition.timezone = request.timezone;
        }
        if(request.location != null) {
            competition.location = request.location;
        }
        if(request.description != null) {
            competition.description = request.description;
        }
        if(request.status != null) {
            competition.status = request.status;
        }
        return competition;
    }

    @Transactional
    public void deleteCompetition(UUID id) {
        boolean deleted = Competition.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Competition with id " + id + " not found");
        }
    }

    private boolean isValidTimezone(String timezone) {
        try {
            ZoneId.of(timezone);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }

}
