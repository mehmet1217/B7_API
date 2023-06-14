package apiTest.day6;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;

public class Pojo_Deserialization_Test {
    @BeforeClass
    public void beforeClass(){
        baseURI = "http://eurotech.study";
    }

    @Test
    public void testPOJO(){
        Response response = given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 681)
                .when()
                .get("/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        EurotechUser oneUser = response.body().as(EurotechUser.class);
        // print all company
        System.out.println("oneUser.getId() = " + oneUser.getId());
        System.out.println("oneUser.getName() = " + oneUser.getName());
        System.out.println("oneUser.getEmail() = " + oneUser.getEmail());
        System.out.println("oneUser.getCompany() = " + oneUser.getCompany());
        System.out.println("oneUser.getStatus() = " + oneUser.getStatus());
        System.out.println("oneUser.getProfileId() = " + oneUser.getProfileId());

        assertEquals(oneUser.getId(),681.0);
        assertEquals(oneUser.getName(),"Ihsan");
        assertEquals(oneUser.getStatus(),"Instructor");
        assertEquals(oneUser.getProfileId(),402.0);


    }
}
