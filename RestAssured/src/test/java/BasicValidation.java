import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class BasicValidation {

	final static String url = "http://demo.guru99.com/V4/sinkministatement.php";
	final static String url1 = "http://md5.jsontest.com";
	
	@DataProvider(name = "md5hashes")
	public String[][] createMD5TestData() {
	         
	    return new String[][] {
	            {"testcaseOne", "4ff1c9b1d1f23c6def53f957b1ed827f"},
	            {"testcaseTwo", "39738347fb533d798aca9ae0f56ca126"},
	            {"testcaseThree", "db6b151bb4bde46fddb361043bc3e2d9"}
	    };
	}

	// Verifying Status Code
	@Test
	public static void statusCodeValidation() {
		int statusCode = given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!")
				.queryParam("Account_No", "1").when().get(url).getStatusCode();
		System.out.println("The response status is " + statusCode);

		given().when().get(url).then().assertThat().statusCode(200);
	}

   //Verifying Headers
	@Test
	public static void headersValidation() {
		System.out.println("The headers in the response " + get(url).then().extract().headers());
		given().when().get(url).then().assertThat().header("Server", "Apache");
	}

	// Verifying Status Code line
	@Test
	public static void statusCodeLine() {
		System.out.println("The status line in the response " + get(url).then().extract().statusLine());
		given().when().get(url).then().assertThat().statusLine("HTTP/1.1 200 OK");
	}

	// Verifying Response
	@Test(dataProvider = "md5hashes")
	public void md5JsonTest(String originalText, String md5Hash) {
	         
	    given().
	        param("text", originalText).
	    when().
	        get(url1).
	    then().
	    assertThat().
            body("size()", is(2)).
        and().
	    assertThat().
	        body("md5", equalTo(md5Hash));
	    
	}
	

}
