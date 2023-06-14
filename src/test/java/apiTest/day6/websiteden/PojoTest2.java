package apiTest.day6.websiteden;

import apiTest.day6.websiteden.APITest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PojoTest2 {
    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }

    @Test
    public void testPOJO() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 681)
                .when()
                .get("/api/profile/userQuery");
        APITest apiTest = response.body().as(APITest.class);
        System.out.println("apiTest.getName() = " + apiTest.getName());
    }
}
