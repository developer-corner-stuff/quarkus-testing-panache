package io.arrogantprogrammer;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello!"));
    }

    @Test
    public void testHelloAll() {


        given()
                .when().get("/hello/all")
                .then()
                .statusCode(200)
                .body(is("[Greeting{value='Christopher'}]"));

        assertEquals(1, Greeting.count());
    }

    @BeforeAll
    @Transactional
    public static void setUp() {

        Greeting.deleteAll();
        Greeting greeting = new Greeting("Christopher");
        greeting.persistAndFlush();
    }

}