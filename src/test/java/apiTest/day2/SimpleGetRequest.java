package apiTest.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SimpleGetRequest {

    String devExURL = "http://eurotech.study";

    @Test
    public void test1() {

        Response response = RestAssured.get(devExURL + "/api/profile");
        //print status code
        System.out.println("response.statusCode() = " + response.statusCode());
        // print body
        response.prettyPrint();
    }

    @Test
    public void test2() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");

        System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 200);

        //response.prettyPrint();

        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

    @Test
    public void test3() {
        // Verify test with using RestAssured library
        RestAssured.given()
                .accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType("application/json; charset=utf-8");
    }

    @Test
    public void test4() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");
        Assert.assertEquals(response.statusCode(),200);

        //System.out.println("response.body().toString() = " + response.body().asString());
        Assert.assertTrue(response.body().asString().contains("John Snow"));
    }


}
