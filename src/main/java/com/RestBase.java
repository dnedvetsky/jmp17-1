package com;

import com.sun.jersey.multipart.FormDataParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Path("/")
public class RestBase {

    @POST
    @Path("/post/{name}/{lastname}")
    public Response postData(@PathParam("name") String name, @PathParam("lastname") String lastname) {
        return Response.status(200).entity("Name and last name" + name + lastname).build();
    }
    @GET
    @Path("/{param}")
    public Response getMsg(@PathParam("param") String msg) {

        String output = "Jersey say : " + msg;

        return Response.status(200).entity(output).build();

    }
    @POST
    @Path("/post/{name}/{lastname}/{image}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response postDataWithImage(@PathParam("name") String name, @PathParam("lastname") String lastname,
                                      @FormDataParam("image") InputStream uploadedInputStream) {
        return Response.status(200).entity("Name and last name c://logo.png " + name + lastname).build();
    }


    private void writeToFile(InputStream uploadedInputStream) {
        try {
            OutputStream out;
            int read = 0;
            byte[] bytes = new byte[1024];

            out = new FileOutputStream(new File("C://logo.png"));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
        }

    }
}