import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Trial {

	@Test
	public void getCallFor2008()
	{
		given().
		when().
		   get("http://ergast.com/api/f1/2008/1.json").
		then().
		   assertThat().
		   body("MRData.size()", is(7)).
		and().
		 statusCode(200);
		System.out.println("Test case passed!!");
	}
	
	
}
