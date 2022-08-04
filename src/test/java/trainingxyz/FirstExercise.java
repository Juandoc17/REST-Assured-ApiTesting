package trainingxyz;

import models.Product;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirstExercise {
    @Test
    public void getProduct(){
        String endpoint = "http://localhost:80/api_testing/product/read_one.php";
        var response = given().queryParam("id", 2).when().get(endpoint).then();
        response.log().body();
    }
    @Test
    public void getProducts(){

        String endpoint = "http://localhost:80/api_testing/product/read.php";
        var response = given().
                when().get(endpoint).
                then();
        response.log().body();
    }

    @Test
    public void createProduct(){
        String endpoint = "http://localhost:80/api_testing/product/create.php";
        Product product = new Product("Sweatband", "Excelent for exercise", 5, 3);
        var response = given().
                                            body(product).
                                        when().post(endpoint).then();
        response.log().body();
    }

    @Test
    public void updateProduct(){
        String endpoint = "http://localhost:80/api_testing/product/update.php";
        Product product = new Product(20,"Sweatband", "Excelent for exercise", 6, 3, "Active Wear - Women");
        var response = given().
                                        body(product).
                                        when().put(endpoint).then();
        response.log().body();
    }

    @Test
    public void deleteProduct(){
        String endpoint = "http://localhost:80/api_testing/product/delete.php";
        String body = """
                {
                "id": 20
                }
                """;
        var response = given().
                                    body(body).
                                    when().delete(endpoint).then();
        response.log().body();
    }
}
