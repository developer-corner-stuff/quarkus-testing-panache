package io.arrogantprogrammer;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.Mock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class AnotherGreetingResourceTest {

    static final Logger LOGGER = LoggerFactory.getLogger(AnotherGreetingResourceTest.class);

    @Test
    public void testMockingListAll() {

        PanacheMock.mock(Greeting.class);

        //mock the listAll method
        Mockito.when(Greeting.listAll()).thenReturn(new ArrayList<>(){{
            add(new Greeting("Pooh"));
            add(new Greeting("Tigger"));
            add(new Greeting("Eeyore"));
        }});

        given()
                .when().get("/hello/all")
                .then()
                .statusCode(200)
                .body(containsString("Greeting{value='Eeyore'}"));

        // mocks always return a default value
        assertEquals(0, Greeting.count());
    }

    @Test
    public void testReturningTheActualCount() {

        PanacheMock.mock(Greeting.class);

        //mock the listAll method
        Mockito.when(Greeting.listAll()).thenReturn(new ArrayList<>(){{
            add(new Greeting("Pooh"));
            add(new Greeting("Tigger"));
            add(new Greeting("Eeyore"));
        }});

        Mockito.when(Greeting.count()).thenCallRealMethod();

        given()
                .when().get("/hello/all")
                .then()
                .statusCode(200)
                .body(containsString("Greeting{value='Eeyore'}"));

        // return the actual value
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
