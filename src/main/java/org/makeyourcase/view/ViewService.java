package org.makeyourcase.view;

import java.net.URISyntaxException;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import com.sun.jersey.api.view.Viewable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.makeyourcase.admin.InitializeService;

@Service
@Path("/")
public class ViewService {

    @Value("${cassandra.node}")
    private String node;

    @Value("${cassandra.keyspace}")
    private String keyspace;

    @Value("${cql.create.keyspace}")
    private String createKeyspaceCql;

    @Autowired
    private InitializeService initializeService;

    @GET
    public Viewable display() {
        return new Viewable("/home");
    }

    @PostConstruct
    public void initialize() throws URISyntaxException {
        createDatabaseIfNecessary();
        initializeService.initialize();
    }

    private void createDatabaseIfNecessary() {
        Cluster cluster = Cluster.builder().addContactPoint(node).build();
        Session initializationSession = cluster.connect();
        initializationSession.execute(createKeyspaceCql);
        initializationSession.close();
    }
}