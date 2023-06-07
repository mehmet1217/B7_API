package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import java.lang.module.ResolutionException;

public class GetDevEx {
    String devExURL = "http://eurotech.study";

    @Test
    public void test1() {
        /**
         * Class Task
         * Given accept type Json
         * When user sends GET request to /api/profile
         * Then verify that response status code is 200
         * and body is JSON format
         * and response body contains jakop
         */

  /*    Response response = RestAssured.get(devExURL+"/api/profile");
       Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");
        System.out.println("response.statusCode() = " + response.statusCode());
        Assert.assertEquals(response.statusCode(),200);
        Assert.assertTrue(response.body().asString().contains("jakop"));           */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");
        // Verify status code
        assertEquals(response.statusCode(), 200);
        // verify contenttype Json format
        assertEquals(response.contentType(), "application/json; charset=utf-8");
        // Verify "Osinski LLC"
        assertTrue(response.body().asString().contains("Osinski LLC"));

    }

    @Test
    public void headersTest(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");

        System.out.println("response.header(\"Content-Type\") = " + response.header("Content-Type"));
        System.out.println("response.header(\"ETag\") = " + response.header("ETag"));
        System.out.println("response.header(\"Connection\") = " + response.header("Connection"));
        System.out.println("response.header(\"Date\") = " + response.header("Date"));
        assertEquals(response.header("Content-Type"),"application/json; charset=utf-8");
        assertTrue(response.headers().hasHeaderWithName("ETag"));
        //assertEquals(response.header("Date"),"Wed, 07 Jun 2023 17:07:10 GMT");  zaman farkli doldugu icin dogrulama olamaz
        assertTrue(response.headers().hasHeaderWithName("Date"));
    }
}
