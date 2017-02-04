package com.github.kazenetu.jerseyServer;

import javax.ws.rs.ApplicationPath;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import com.github.kazenetu.jerseyServer.service.UserService;

@ApplicationPath("app")
public class ServerApplication extends ResourceConfig {

    public ServerApplication() {
        packages(this.getClass().getPackage().getName());

        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bindAsContract(UserService.class);
            }
        });
    }
}
