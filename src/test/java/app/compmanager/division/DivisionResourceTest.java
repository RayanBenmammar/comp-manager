package app.compmanager.division;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class DivisionResourceTest {

    @Test
    void testCreateUpdateAndDeleteDivision() {
        Integer competitionId = createCompetition("Division Test Competition");

        String createBody = """
                {
                    "name": "Rx Men",
                    "competitionId": %d
                }
                """.formatted(competitionId);

        Integer divisionId = given()
                .contentType(ContentType.JSON)
                .body(createBody)
                .when().post("/api/divisions")
                .then()
                .statusCode(201)
                .body("name", is("Rx Men"))
                .body("competition.id", is(competitionId))
                .extract().path("id");

        given()
                .when().get("/api/divisions/" + divisionId)
                .then()
                .statusCode(200)
                .body("id", is(divisionId))
                .body("name", is("Rx Men"));

        String updateBody = """
                {
                    "name": "Scaled Men"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .when().put("/api/divisions/" + divisionId)
                .then()
                .statusCode(200)
                .body("name", is("Scaled Men"));

        given()
                .when().delete("/api/divisions/" + divisionId)
                .then()
                .statusCode(204);

        given()
                .when().get("/api/divisions/" + divisionId)
                .then()
                .statusCode(404);
    }

    private Integer createCompetition(String name) {
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
}