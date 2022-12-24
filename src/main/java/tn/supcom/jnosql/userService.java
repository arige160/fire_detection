package tn.supcom.jnosql;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;
import java.util.function.Supplier;
import static java.util.stream.Collectors.toList;

@ApplicationScoped
@Path("user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class userService {

    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);

    @Inject
    private userRepository repository;

    @GET
    public List<user> findAll() {
        return repository.findAll().collect(toList());
    }

    @GET
    @Path("/{id}")
    public user findById(@PathParam("id") int id) {
        return repository.findById(id).orElseThrow(NOT_FOUND);
    }

    @GET
    @Path("/{username}")
    public List<user> findByUsername(@PathParam("username") String username) {
        return repository.findByUsername(username)
                .collect(toList());
    }

    @POST
    public void save(user hero) {
        repository.save(hero);
    }

    @PUT
    @Path("/{id}")
    public void update(@PathParam("id") int id, user hero) {
        repository.save(hero);
    }

    @Path("/{id}")
    @DELETE
    public void delete(@PathParam("id") int id) {
        repository.deleteById(id);
    }
}