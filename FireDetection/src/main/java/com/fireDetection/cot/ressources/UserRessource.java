package com.fireDetection.cot.ressources;

import com.fireDetection.cot.Exceptions.UserAlreadyExistsException;
import com.fireDetection.cot.entities.user;
import com.fireDetection.cot.Exceptions.UserNotFoundException;

import com.fireDetection.cot.repositories.UserRepository;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@ApplicationScoped
@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserRessource {
    private static final Supplier<WebApplicationException> NOT_FOUND =
            () -> new WebApplicationException(Response.Status.NOT_FOUND);
    @Inject
    UserRepository userRepository;
    @Inject

    @GET
    public List<user> findUser() {
        return userRepository.findAll().collect(Collectors.toList());
    }
    @POST
    @Path("/signup")
    public Response createUser(@Valid user user) {
        try {
            if (userRepository.findById(user.getEmail()).isPresent()) {
                throw new UserAlreadyExistsException("User with id " + user.getEmail() + " already exists");
            }

            userRepository.save(user);
            return Response.ok("User added successfully!").build();
        }
        catch (UserAlreadyExistsException e) {
            return  Response.status(400, e.getMessage()).build();
        }
    }



    @POST
    @Path("/adduser")
    public Response addUser(@Valid user user) {
        try {
            if (userRepository.findById(user.getEmail()).isPresent()) {
                throw new UserNotFoundException("User with id " + user.getEmail() + " already exist!");
            }
            userRepository.save(user);
            return Response.ok("User added successfully!").build();
        }
        catch (UserNotFoundException e) {
            return Response.status(400, e.getMessage()).build();
        }
    }
    @GET
    @Path("/{email}")
    public user getUserById(@PathParam("email") String email) {
        return userRepository.findById(email).orElseThrow(NOT_FOUND);
    }
    @DELETE
    @RolesAllowed("ADMIN")
    @Path("/{email}")
    public Response deleteUser(@PathParam("email") String email) {
        try {
            if (!userRepository.findById(email).isPresent()) {
                throw new UserNotFoundException("User with email " + email + " NOT FOUND!");
            }
            user userDB = userRepository.findById(email).get();
            userRepository.save(userDB);
            return Response.ok("User with email " + email + " deleted!").build();
        }
        catch (UserNotFoundException e){
            return Response.status(400, e.getMessage()).build();
        }
    }
}