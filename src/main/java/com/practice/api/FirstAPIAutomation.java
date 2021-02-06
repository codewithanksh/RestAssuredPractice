package com.practice.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

//given()
//when()
//then()
/**
 * @author : ankush.sharma
 * @date : 6th feb 2021
 */

public class FirstAPIAutomation {


    @Test
    @DisplayName("Get Demo Test")
    public void getDemo() {

        RestAssured.baseURI = "https://reqres.in";

        given().
                when().get("/api/users/")
                .then()
                        .body("page",equalTo(1))
                        .body("data.id",hasItems(1,2,3,4,5,6))
                        .assertThat().statusCode(HttpStatus.SC_OK);
    }

    @Test
    @DisplayName("Post Demo")
    public void postDemo() {

        //Request Body
        String requestBody = "{\"name\":\"morpheus\",\"job\":\"leader\"}";

        RestAssured.baseURI = "https://reqres.in";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/api/users")
        .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED)
                .body("name",equalTo("morpheus"))
                .body("job",equalTo("leader"))
                .extract().response().prettyPrint();

        //One line validation of body ??
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .post("/api/users")
        .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED)
                .body("name",equalTo("morpheus"),"job",equalTo("leader"))
                .extract().response().prettyPrint();

        //Response Body
        //--- To be explained
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/api/users")
                .then()
                .assertThat().statusCode(HttpStatus.SC_CREATED)
                .body("name",equalTo("morpheus"),"job",equalTo("leader"))
                .extract().response();

        String id = response.getBody().jsonPath().getString("id");
        System.out.println(id);
    }

    @Test
    @DisplayName("Put Demo")
    public void putDemo() {

        //Request Body
        String requestBody = "{\"name\":\"Raju\",\"job\":\"SDET III\"}";
        RestAssured.baseURI = "https://reqres.in";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .put("/api/users/2")
        .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("name",equalTo("Raju"))
                .body("job",equalTo("SDET III"))
                .extract().response().prettyPrint();
    }

    @Test
    @DisplayName("Patch Demo")
    public void patchDemo() {

        //Request Body
        String requestBody = "{\"name\":\"Ram\"}";

        RestAssured.baseURI = "https://reqres.in";
        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
        .when()
                .patch("/api/users/2")
        .then()
                .assertThat().statusCode(HttpStatus.SC_OK)
                .body("name",equalTo("Ram"))
                .extract().response().prettyPrint();

    }

    @Test
    @DisplayName("Delete Demo")
    public void deleteDemo() {

        given().
        when().
                delete("/api/users/2")
        .then().
                assertThat().statusCode(204);

        //RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }


}
