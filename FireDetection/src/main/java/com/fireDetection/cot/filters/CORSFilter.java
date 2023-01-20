package com.fireDetection.cot.filters;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext cros) throws IOException {
        cros.getHeaders().add("Access-Control-Allow-Origin", "*");
        cros.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
        cros.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cros.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
        cros.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}