package apiTest.day6.Pojo2;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class BLGTEST {

    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }

    @Test
    public void testPOJO() {
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 256)
                .when()
                .get("/api/profile/userQuery");
      Blg blg = response.body().as(Blg.class);
        System.out.println("blg.getName() = " + blg.getName());
        System.out.println("blg.getCompany() = " + blg.getCompany());
    }
}

