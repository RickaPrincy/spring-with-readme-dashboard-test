package com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.factory;

import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.ReadmeMonitorConf;
import com.ricka.princy.bonjouraurevoir.endpoint.rest.security.readme.monitor.model.ReadmeRequestCreator;
import org.springframework.stereotype.Component;

@Component
public class ReadmeRequestCreatorFactory {
    public ReadmeRequestCreator createReadmeRequestCreator(ReadmeMonitorConf readmeMonitorConf){
        return ReadmeRequestCreator.builder()
            .version(readmeMonitorConf.getVersion())
            .name(readmeMonitorConf.getName())
            .build();
    }
}
