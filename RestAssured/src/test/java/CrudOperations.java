import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Collections;

public class CrudOperations {

	//Verify get request
	@Test
	public void ValidateGetRequest()
	{
		given().
		queryParam("page", "2").
		when().
		get("https://reqres.in/api/users").
		then().
		assertThat().statusCode(200).
		and().
		assertThat().
		body("size()", is(6)).
		and().
		assertThat().
		body("page", equalTo(2)).
		and().
		assertThat().
		body("per_page", equalTo(6)).
		and().
		assertThat().
		body("total", equalTo(12)).
		and().
		assertThat().
		body("total_pages", equalTo(2)).
		and().
		body("data.size()", is(6)).
		and().
		body("data.id[0]", equalTo(7)).
		and().
		body("data.email[0]", equalTo("michael.lawson@reqres.in")).
		and().
		body("data.first_name[0]", equalTo("Michael")).
		and().
		body("data.last_name[0]", equalTo("Lawson")).
		and().
		body("data.avatar[0]", equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/follettkyle/128.jpg"));
				
		
	}
	
	
	//Verify Post request
	@Test
	public void validatePostRequest()
	{
		given().
		body("{\r\n" + 
				"    \"name\": \"Sonali\",\r\n" + 
				"    \"job\": \"Tester\"\r\n" + 
				"}").
		when().
		post("https://reqres.in/api/users").
		then().
		assertThat().statusCode(201).
		and().
		assertThat().
		body("$", hasKey("id")).and().
		assertThat().
		body("$", hasKey("createdAt"));
		
	}
	
	
	//Verify Put request
	@Test
	public void validatePutRequest()
	{
		given().
		body("{\r\n" + 
				"    \"name\": \"Sonali\",\r\n" + 
				"    \"job\": \"Tester\"\r\n" + 
				"}").
		when().
		put("https://reqres.in/api/users/218").
		then().
		assertThat().statusCode(200).
		and().
		assertThat().
		body("$", hasKey("updatedAt"));
		
	}
	
	
	//Verify Delete request
	@Test
	public void validateDeleteRequest()
	{
		given().
		when().
		delete("https://reqres.in/api/users/218").
		then().
		assertThat().statusCode(204).
		and().
		assertThat().
		body(blankOrNullString());
		
	}
		
	
	
	
	
}
