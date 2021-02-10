package com.practice.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import  org.junit.jupiter.api.Assertions;
import static org.hamcrest.Matchers.*;

public class JsonPathDemo {

    @Test
    public void jsonPathDemo() {

        RestAssured.baseURI = "https://reqres.in";


        Response response = given().log().all()
                .expect().log().all().statusCode(HttpStatus.SC_CREATED).response()
                .when().get("/api/users/2");

        JsonPath jp = response.jsonPath();
        Integer id = jp.getInt("data.id");
        String emailId = jp.getString("data.email");
        String firstName = jp.getString("data.first_name");
        String LastName = jp.getString("data.last_name");
        String avatar = jp.getString("data.avatar");

        System.out.println("Id " + id + "\n Email : " + emailId + "\n first Name : " + firstName + "\n Last Name : " + LastName + "\n Avatar : " + avatar);

        String url = jp.getString("support.url");
        String text = jp.getString("support.text");
        System.out.println("URL : " + url + "\n Text : " + text);
        System.out.print("\n\tTest Passed");
        // Assertions
        Assertions.assertEquals(id,2);

    }
}
