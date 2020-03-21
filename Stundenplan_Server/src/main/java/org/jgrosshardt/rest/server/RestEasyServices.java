package org.jgrosshardt.rest.server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ApplicationPath("/stundenplan")
public class RestEasyServices extends Application {

    private Set<Object> singletons = new HashSet<Object>();

    public RestEasyServices() {
        singletons.add(new StundenplanCrudService());
        singletons.add(new StundenplanAuthService());
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    @Override
    public Set<Class<?>> getClasses() {
        return super.getClasses();
    }

    @Override
    public Map<String, Object> getProperties() {
        return super.getProperties();
    }
}
