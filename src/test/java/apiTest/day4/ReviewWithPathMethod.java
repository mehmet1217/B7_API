package apiTest.day4;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Map;

public class ReviewWithPathMethod {

    String devExURL = "http://eurotech.study";
    @Test
    public void getAllProfiles(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when()
                .get(devExURL + "/api/profile");
        assertEquals(response.statusCode(),200);
        //response.prettyPrint();

        // Finding first element's Id
        int firstID = response.path("id[0]");
        System.out.println("firstID = " + firstID);

        // Finding last elements ID
        int lastID = response.path("id[-1]");
        System.out.println("lastID = " + lastID);

        // Print Second Company
        String secondCompany = response.path("company[1]");
        System.out.println("secondCompany = " + secondCompany);
        System.out.println("******************************************************");

        // Get 1st skills skills as List
        List<String> firstSkillss = response.path("skills[0]");
        System.out.println("firstSkillss = " + firstSkillss);

        for (String skill: firstSkillss) {
            System.out.println("skill = " + skill);
        }

        System.out.println("firstSkillss.size() = " + firstSkillss.size());

        // Print first skills third skill (JAVASCRIPT)
        Object firstSkillsThirdSkill = response.path("skills[0][2]");
        System.out.println("firstSkillsThirdSkill = " + firstSkillsThirdSkill);

        System.out.println("________________________________________________________");

        // Create a Map for first users info
        Map<String,Object> firstUserMap = response.path("user[0]");
        System.out.println("firstUserMap = " + firstUserMap);

        for (String user : firstUserMap.keySet()) {
           // System.out.println("user = " + user); // We will not use in this way
            System.out.println(user+": "+firstUserMap.get(user));
        }
        System.out.println("________________________________________________________");

        // Get first users user id
        Object firstUserId = response.path("user[0].id");
        System.out.println("firstUserId = " + firstUserId);
        System.out.println("________________________________________________________");

        // Get all user IDs - how many

        List<Integer> userIds = response.path("user.id");
        System.out.println("userIds.size = " + userIds.size());
        System.out.println("________________________________________________________");

        // Get second user id

        // Object secondUserId = response.path("user.id[1]"); bU sekilde dogru bir kullanim degil asagidaki
        Object secondUserId = response.path("user[1].id");
        System.out.println("secondUserId = " + secondUserId);

        System.out.println("________________________________________________________");
        List<Integer> allIds = response.path("id");
        for (Integer id : allIds) {
            System.out.println("id = " + id);
        }

    }

}
