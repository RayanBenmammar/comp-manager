package app.compmanager.participant;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class ParticipantResourceTest {

    @Test
    void testCreateUpdateAndDeleteParticipant() {
        String competitionId = createCompetition("Participant Test Competition");
        String rxDivisionId = createDivision("Rx Women", competitionId);
        String scaledDivisionId = createDivision("Scaled Women", competitionId);

        String createBody = """
                {
                    "firstName": "Alice",
                    "lastName": "Martin",
                    "email": "alice@example.com",
                    "gender": "FEMALE",
                    "competitionId": "%s",
                    "divisionId": "%s"
                }
                """.formatted(competitionId, rxDivisionId);

        String participantId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/api/participants")
                .then()
                .statusCode(201)
                .body("firstName", is("Alice"))
                .body("lastName", is("Martin"))
                .body("status", is("REGISTERED"))
                .body("competition.id", is(competitionId))
                .body("division.id", is(rxDivisionId))
                .body("division.competition", is((Object) null))
                .extract().path("id");

        given()
                .when().get("/api/participants/" + participantId)
                .then()
                .statusCode(200)
                .body("id", is(participantId))
                .body("email", is("alice@example.com"));

        String updateBody = """
                {
                    "divisionId": "%s",
                    "status": "CONFIRMED"
                }
                """.formatted(scaledDivisionId);

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/api/participants/" + participantId)
                .then()
                .statusCode(200)
                .body("division.id", is(scaledDivisionId))
                .body("status", is("CONFIRMED"));

        given()
                .when().delete("/api/participants/" + participantId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/participants/" + participantId)
                .then()
                .statusCode(404);
    }

    private String createCompetition(String name) {
        String body = """
                {
                    "name": "%s",
                    "startDate": "2027-06-15",
                    "timezone": "Europe/Paris"
                }
                """.formatted(name);

        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/competitions")
                .then()
                .statusCode(201)
                .extract().path("id");
    }

    private String createDivision(String name, String competitionId) {
        String body = """
                {
                    "name": "%s",
                    "competitionId": "%s"
                }
                """.formatted(name, competitionId);

        return given()
                .contentType(ContentType.JSON)
                .body(body)
                .when().post("/api/divisions")
                .then()
                .statusCode(201)
                .extract().path("id");
    }
}