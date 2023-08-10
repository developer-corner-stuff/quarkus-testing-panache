package io.arrogantprogrammer;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.QueryParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/repository")
public class RepositoryGreetingResource {

    static final Logger LOGGER = LoggerFactory.getLogger(RepositoryGreetingResource.class);

    @Inject
    GreetingRepository greetingRepository;

    @GET
    @Path("/hello")
    @Transactional
    public String hello(@QueryParam("name") final String name) {

        if (name == null) {
            return "Hello!";
        }else{
            Greeting greeting = new Greeting(name);
            greetingRepository.persist(greeting);
            return String.format("Hello, %s!", name);
        }
    }

    @GET
    @Path("/all")
    public List<Greeting> hellos() {

        return greetingRepository.listAll();
    }

}
