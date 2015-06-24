package org.jmotor.restlet;

import org.jmotor.model.Stack;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import java.util.Arrays;
import java.util.List;

/**
 * Component:
 * Description:
 * Date: 2015/6/18
 *
 * @author Andy Ai
 */
@Path("/v1/stacks")
public class StacksRestlet {
    @GET
    @Produces("application/json")
    public List<Stack> stacks() {
        return Arrays.asList(
                Stack.Builder.newBuilder().id(1).groupId("javax.servlet").artifactId("javax.servlet-api").version("3.1.0").build(),
                Stack.Builder.newBuilder().id(2).groupId("com.google.guava").artifactId("guava").version("18.0").build()
        );
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Stack filterByArtifactId(@NotNull @PathParam("id") Integer id) {
        switch (id) {
            case 1:
                return Stack.Builder.newBuilder().id(1).groupId("javax.servlet").artifactId("javax.servlet-api").version("3.1.0").build();
            case 2:
                return Stack.Builder.newBuilder().id(2).groupId("com.google.guava").artifactId("guava").version("18.0").build();
            default:
                throw new WebApplicationException("Stack not found, id: " + id, 404);
        }
    }
}
