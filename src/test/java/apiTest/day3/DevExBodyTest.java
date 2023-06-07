package apiTest.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;

public class DevExBodyTest {

    String devExURL = "http://eurotech.study";
    String petStoreURL = "https://petstore.swagger.io/v2";

    @Test
    public void verifyWithPathMethod() {
        /**
         "id": 213,
         "email": "rambo@gmail.com",
         "name": "sylvester",
         "company": "TCS",
         "status": "Automation Test Engineer",
         "profileId": 147
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("id", "213")
                .when()
                .get(devExURL + "/api/profile/userQuery");
        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        // validation methods
        // 1.contains
        // 2.path()
        // 3.jsonPath()
        // 4.hamcresmatches
        // 5.serilization
        // 6.deserialization
        System.out.println("response.path(\"id\") = " + response.path("id"));
        System.out.println("response.path(\"email\") = " + response.path("email"));
        System.out.println("response.path(\"company\") = " + response.path("company"));
        System.out.println("response.path(\"mail\") = " + response.path("name"));
        System.out.println("response.path(\"status\") = " + response.path("status"));
        int actualUserID = response.path("id");
        String actualEmail = response.path("email");
        String actualName = response.path("name");
        String actualCompany = response.path("company");
        String actualStatus = response.path("status");
        assertEquals(actualUserID,213);
        assertEquals(actualEmail,"rambo@gmail.com");
        assertEquals(actualName,"sylvester");
        assertEquals(actualCompany,"TCS");
        assertEquals(actualStatus,"Automation Test Engineer");

    }

    @Test
    public void verifyBodyPetStore(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .pathParam("petID", 13)
                .when()
                .get(petStoreURL + "/pet/{petID}");
        response.prettyPrint();
        int actualID = response.path("id");
        System.out.println("actualID = " + actualID);
        String actualName = response.path("category.name");
        System.out.println("actualName = " + actualName);
        String actualStatus = response.path("status");
        System.out.println("actualStatus = " + actualStatus);

        System.out.println("response.path(\"photoUrls[0]\") = " + response.path("photoUrls[0]"));
        System.out.println("response.path(\"tags.id[0]\") = " + response.path("tags.id[0]"));
        System.out.println("response.path(\"tags.name[0]\") = " + response.path("tags.name[0]"));
        System.out.println("response.path(\"tags[0].id\") = " + response.path("tags[0].id"));
    }
}
