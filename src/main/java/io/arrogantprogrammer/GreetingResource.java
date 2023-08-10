package io.arrogantprogrammer;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/hello")
@Produces(MediaType.TEXT_PLAIN)
public class GreetingResource {

    static final Logger LOGGER = LoggerFactory.getLogger(GreetingResource.class);

    @GET
    @Transactional
    public String hello(@QueryParam("name") final String name) {

        if (name == null) {
            return "Hello!";
        }else{
            Greeting greeting = new Greeting(name);
            greeting.persist();
            return String.format("Hello, %s!", name);
        }
    }

    @GET
    @Path("/all")
    public List<Greeting> hellos() {

        return Greeting.listAll();
    }

}
