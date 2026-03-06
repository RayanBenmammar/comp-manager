package app.compmanager.competition;

import java.net.URI;
import java.util.List;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/competitions")
@Produces("application/json")
@Consumes("application/json")
public class CompetitionResource {

    @GET
    public List<Competition> getAllCompetitions(){
        return Competition.listAll();
    }

    @GET
    @Path("/{id}")
    public Competition getCompetitionById(@PathParam("id") Long id ) {
        Competition competition = Competition.findById(id);
        if(competition == null) {
            throw new NotFoundException("Competition with id " + id + " not found");
        }
        return competition;
    }

    @POST
    @Transactional
    public Response createCompetition(@Valid CompetitionCreateRequest request) {
        Competition competition = new Competition();
        competition.name = request.name;
        competition.startDate = request.startDate;
        competition.endDate = request.endDate;
        competition.timezone = request.timezone;
        competition.location = request.location;
        competition.description = request.description;
        competition.persist();

        return Response.created(URI.create("/api/competitions/" + competition.id))
        .entity(competition)
        .build();

    }    

    @PUT
    @Path("/{id}")  
    @Transactional
    public Competition updateCompetition(@PathParam("id") Long id, @Valid CompetitionUpdateRequest request) {
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

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCompetition(@PathParam("id") Long id) {
        boolean deleted = Competition.deleteById(id);
        if (!deleted) {
            throw new NotFoundException("Competition with id " + id + " not found");
        }
        return Response.noContent().build();
    }

}
