package com.rest;

import entity.User;
import entity.UsersArchive;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hello")
public class UserService {
    private UsersArchive usersArchive = UsersArchive.INSTANCE;

    @POST
    @Path("/postuser/{username}/{password}/{email}/{id}")
    public Response postUser(@PathParam("username") String username,
                             @PathParam("password") String password,
                             @PathParam("email") String email) {

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setId(usersArchive.getUsersList().size());
        usersArchive.addUser(user);

        return Response.status(200).entity(user.toString()).build();
    }

    @PUT
    @Path("putuser/{index}/put")
    @Consumes(MediaType.APPLICATION_JSON)
    public User putUser(User user, @PathParam("index") int index) {
        usersArchive.getUsersList().set(index, user);
        return user;
    }

    @GET
    @Path("/username/{index}")
    public Response getUser(@PathParam("index") int index) {
        return Response.status(200).entity(usersArchive.getUsersList().get(index)).build();
    }

    @POST
    @Path("/userpost")
    @Consumes(MediaType.APPLICATION_XML)
    public User setPlanet(User user) {
        usersArchive.addUser(user);
        return user;
    }

    @DELETE
    @Path("/user/{index}/delete")
    public Response deleteUser(@PathParam("index") int index) {
        usersArchive.getUsersList().remove(index);
        return Response.status(200).build();
    }
}