package ro.nemeti.wicket.example.service.impl;

import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.nemeti.wicket.example.service.MessageService;

import javax.enterprise.context.ApplicationScoped;

//@Startup
@ApplicationScoped
public class MessageServiceImpl implements MessageService {
    private final static Logger LOG = LoggerFactory.getLogger(MessageServiceImpl.class);

    @ConfigProperty(name = "quarkus.application.version")
    String applicationVersion;
    @ConfigProperty(name = "quarkus.version")
    String quarkusVersion;

    public MessageServiceImpl() {
        LOG.info(">>>>>>>>>>>> Service created >>>>>>>>>>>>");
    }

    @Override
    public String sayHello() {
        return "Hello from Quarkus, v: " + quarkusVersion;
    }
}
