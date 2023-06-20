package apiTest.day8;

import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutRequestDemo {

    @BeforeClass
    public void beforeClass() {
        baseURI = "http://eurotech.study";
    }

    @Test
    public void addNewExperience() {
        /**{
         "title": "string",
         "company": "string",
         "location": "string",
         "from": "YYYY-MM-DD",
         "to": "YYYY-MM-DD",
         "current": false,
         "description": "string"
         }*/

        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "QA");
        experienceBody.put("company", "SONY");
        experienceBody.put("location", "Berlin");
        experienceBody.put("from", "2014-07-01");
        experienceBody.put("to", "2015-12-31");
        experienceBody.put("current", false);
        experienceBody.put("description", "güzel");

        given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",Autohorization.getToken())
                .and()
                .body(experienceBody)
                .when()
                .post("/api/profile/experience")
                .then().assertThat().statusCode(201);

    }

    @Test
    public void updateExperiencePutMethod(){
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Test Automation Engineer");
        experienceBody.put("company", "Panasonic");
        experienceBody.put("location", "Tokyo");
        experienceBody.put("from", "2016-07-01");
        experienceBody.put("to", "2018-12-31");
        experienceBody.put("current", false);
        experienceBody.put("description", "güzel cok güzel");

        given().log().all().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                //.header("x-auth-token",Autohorization.getToken())
                .headers(Autohorization.getAccessToken("mertens11@gmail.com","Test1234"))
                .and()
                .body(experienceBody)
                .when()
                .put("/api/profile/experience/882")
                .then().log().all().assertThat().statusCode(204);

    }

    @Test
    public void updateExperiencePatchMethod(){
        Map<String, Object> experienceBody = new HashMap<>();
        experienceBody.put("title", "Back-End Test Automation Engineer");
        experienceBody.put("location", "New Delhi");

        experienceBody.put("description", "güzel cok güzel");

        given().log().all().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                //.header("x-auth-token",Autohorization.getToken())
                .headers(Autohorization.getAccessToken("mertens11@gmail.com","Test1234"))
                .and()
                .pathParam("id",897)
                .and()
                .body(experienceBody)
                .when()
                .patch("/api/profile/experience/{id}")
                .then().log().all().assertThat().statusCode(204);

    }

    @Test
    public void deleteExperience(){
        given().log().all()
                .and()
                .contentType(ContentType.JSON)
                .and()
                .header("x-auth-token",Autohorization.getToken())
                .and()
                .pathParam("id",882)
                .and()
                .delete("/api/profile/experience/{id}")
                .then().log().all()
                .assertThat().statusCode(200);
    }


}
