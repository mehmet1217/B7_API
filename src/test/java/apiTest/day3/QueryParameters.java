package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class QueryParameters {
    String devExURL = "http://eurotech.study";
    String petStoreURL = "https://petstore.swagger.io/v2";

    @Test
    public void queryParamPets(){
        /**Given accept type is JSON
         * and query param is status sold
         * When user sends GET request to /pet/findByStatus
         * Then response status code is 200
         * and content type is application/json
         * And "cats" should be in payload/body
         */

        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("status", "sold")
                .when()
                .get(petStoreURL + "/pet/findByStatus");
        assertEquals(response.statusCode(),200);
        assertTrue(response.body().asString().contains("cats"));
        assertEquals(response.header("Content-Type"),"application/json");

    }

    @Test
    public void queryParamDevEx(){

    }
}
