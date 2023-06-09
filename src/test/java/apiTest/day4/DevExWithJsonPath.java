package apiTest.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.testng.Assert.*;

import org.testng.IResultMap;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DevExWithJsonPath {
    String devExURL = "http://eurotech.study";

    @Test
    public void test(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and()
                .queryParam("id", 213)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        response.prettyPrint();
        System.out.println("response.path(\"email\") = " + response.path("email"));

        System.out.println("****************** JSON PATH METHOD ***********************");

        /**{
         "id": 213,
         "email": "rambo@gmail.com",
         "name": "sylvester",
         "company": "TCS",
         "status": "Automation Test Engineer",
         "profileId": 147
         }*/

        JsonPath jsonData = response.jsonPath();
        int userIdJson = jsonData.getInt("id");
        System.out.println("userIdJson = " + userIdJson);
        String emailJson = jsonData.getString("email");
        System.out.println("emailJson = " + emailJson);
        String nameJson = jsonData.getString("name");
        String companyJson = jsonData.getString("company");
        String stausJson = jsonData.getString("status");

        System.out.println("emailJson = " + emailJson);
        System.out.println("nameJson = " + nameJson);
        System.out.println("companyJson = " + companyJson);
        System.out.println("stausJson = " + stausJson);

        assertEquals(userIdJson,213);
        assertEquals(emailJson,"rambo@gmail.com");
        assertEquals(nameJson,"sylvester");
        assertEquals(companyJson,"TCS");
        assertEquals(stausJson,"Automation Test Engineer");

        assertEquals(jsonData.getString("email"),"rambo@gmail.com");
    }

    @Test
    public void task() {
        /**
         Given accept type is json
         And query param 681
         Status code 200
         Content Type application Json
         verify user information with using JsonPath
         {
         "id": 681,
         "email": "instructorihsan@eurotech.com",
         "name": "Ihsan",
         "company": "EuroTech",
         "status": "Instructor",
         "profileId": 402
         }
         */

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().queryParam("id", 681)
                .when()
                .get(devExURL + "/api/profile/userQuery");
        assertEquals(response.statusCode(),200);
        assertEquals(response.contentType(),"application/json; charset=utf-8");

        JsonPath jsonData = response.jsonPath();
        assertEquals(jsonData.getInt("id"),681);
        assertEquals(jsonData.getString("email"),"instructorihsan@eurotech.com");
        assertEquals(jsonData.getString("name"),"Ihsan");
        assertEquals(jsonData.getString("company"),"EuroTech");
        assertEquals(jsonData.getString("status"),"Instructor");
        assertEquals(jsonData.getInt("profileId"),402);


    }

    @Test
    public void test2(){
        Response response = RestAssured.get(devExURL + "/api/profile");
        assertEquals(response.statusCode(),200);

        JsonPath jsonPath = response.jsonPath();
        // get 5th ID
        int fiftID = jsonPath.getInt("id[4]");
        System.out.println("fiftID = " + fiftID);
        // get all Company name

        List<Object> allCompany = jsonPath.getList("company");
        System.out.println("allCompany = " + allCompany);
        System.out.println("-----------------------------------------------------");
        // Get second user info as Map
        Map<String,Object> secondUserInfo = jsonPath.getMap("user[1]");
        System.out.println("secondUserInfo = " + secondUserInfo);
        System.out.println("secondUserInfo.get(\"name\") = " + secondUserInfo.get("name"));

        // Get second skills
        List<String> seconsSkils = jsonPath.getList("skills[1]");
        System.out.println("seconsSkils = " + seconsSkils);
        String secondSkillFifthSkill = jsonPath.getString("skills[1][4]");
        System.out.println("secondSkillFifthSkill = " + secondSkillFifthSkill);


        System.out.println("******************************* GPATH METHOD ***********************************");
        // Get all user name which has github is null
        List<Object> listGitHubNull = jsonPath.getList("user.findAll{it.github==null}.name"); //
        System.out.println("listGitHubNull = " + listGitHubNull);
        List<Object> listGitHubFull = jsonPath.getList("user.findAll{it.github!=null}.name"); //
        System.out.println("listGitHubFull = " + listGitHubFull);
        System.out.println("------------------------------------------------------------------------");
        List<Object> list10 = jsonPath.getList("user.findAll{it.id<10}.name");
        System.out.println("list10 = " + list10);

    }


}
