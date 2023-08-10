package io.arrogantprogrammer;

import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class RepositoryGreetingResourceTest {

    @InjectMock
    GreetingRepository greetingRepository;

    @Test
    @Transactional
    public void testHelloEndpoint() {

        Mockito.when(greetingRepository.listAll()).thenReturn(new ArrayList<>(){{
            add(new Greeting("Tigger"));
        }});

        Mockito.when(greetingRepository.count()).thenReturn(2L);

        given()
                .when().get("/repository/hello")
                .then()
                .statusCode(200)
                .body(is("Hello!"));

        assertEquals(2, greetingRepository.count());
    }

    @Test
    @Transactional
    public void testHelloAll() {

        Mockito.when(greetingRepository.listAll()).thenReturn(new ArrayList<>(){{
            add(new Greeting("Tigger"));
        }});

        given()
                .when().get("/repository/all")
                .then()
                .statusCode(200)
                .body(is("[Greeting{value='Tigger'}]"));

        assertEquals(0, Greeting.count());
    }
}
