package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SecondExercise {

    @Test
    public void getProduct (){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        given().
                queryParam("id", 22).
                when().
                get(endpoint).
                then().assertThat().
                            statusCode(200).
                            header("Content-Type", equalTo("application/json")).
                            body("id", equalTo("22")).
                            body("name", equalTo("Multivitaminic pills")).
                            body("description", equalTo("Centrum Advance for mens")).
                            body("price", equalTo("30.00")).
                            body("category_id", equalTo("2")).
                            body("category_name", equalTo("Active Wear - Women"));

    }

    @Test
    public void getProducts (){
        String url = "http://localhost:80/api_testing/product/read.php";
        given().
                when().
                get(url).
                then().
                log().headers().
                statusCode(200).
                assertThat().body("records.id", notNullValue()).
                body("records.name", notNullValue()).
                body("records.description", notNullValue()).
                body("records.price", notNullValue()).
                body("records.category_id", notNullValue()).
                body("records.category_name", notNullValue());
    }
    @Test
    public void createProduct(){
        String url = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product("Multivitaminic pills", "Centrum Advance for mens", 30, 2);
        var response = given().
                body(product).
        when().post(url).
                then()
                    .body("message", equalTo("Product was created.")).
                     statusCode(200);
    }

}
