package ru.pflb.rest_assured.test.education;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;



public class TestMainRest {

    private static RequestSpecification spec;

    @BeforeMethod
    public void initSpecification() {
        RestAssured.baseURI = StaticVariables.baseURI;
        spec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(RestAssured.baseURI)
                .addFilter(new ResponseLoggingFilter())
                .addFilter(new RequestLoggingFilter())
                .build();
    }

    @Test
    public void testStatusCode() {
        given()
                .spec(spec)
                .when()
                .get(StaticVariables.posts1)
                .then().statusCode(200);
    }

    @Test
    public void testSomeData() {
        given().
                spec(spec).
                param("title", "sunt aut facere repellat provident occaecati excepturi optio reprehenderit").
                when().
                get(StaticVariables.posts1).
                then().
                statusCode(200);
    }
}




