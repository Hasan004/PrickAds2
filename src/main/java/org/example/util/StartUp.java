package org.example.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Singleton
@Startup
public class StartUp {

    @Inject
    VulDatabase vulDatabase;

    @PostConstruct
    public void init(){
        vulDatabase.voeruit();
    }

}
