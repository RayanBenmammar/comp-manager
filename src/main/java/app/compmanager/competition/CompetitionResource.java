package app.compmanager.competition;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/api/competitions")
@Produces("application/json")
@Consumes("application/json")
public class CompetitionResource {

    private final CompetitionService service;

    public CompetitionResource(CompetitionService service) {
        this.service = service;
    }

    @GET
    public List<Competition> getAllCompetitions(){
        return service.getAllCompetitions();
    }

    @GET
    @Path("/{id}")
    public Competition getCompetitionById(@PathParam("id") Long id ) {
        return service.getCompetitionById(id);
    }

    @POST
    public Response createCompetition(@Valid CompetitionCreateRequest request) {
        Competition competition = service.createCompetition(request);

        return Response.created(URI.create("/api/competitions/" + competition.id))
        .entity(competition)
        .build();

    }    

    @PUT
    @Path("/{id}")  
    public Response updateCompetition(@PathParam("id") Long id, @Valid CompetitionUpdateRequest request) {
        Competition competition = service.updateCompetition(id, request);
        return Response.ok(competition).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCompetition(@PathParam("id") Long id) {
        service.deleteCompetition(id);
        return Response.noContent().build();
    }

}
