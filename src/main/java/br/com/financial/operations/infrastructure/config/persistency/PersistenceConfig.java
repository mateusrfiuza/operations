package br.com.financial.operations.infrastructure.config.persistency;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("br.com.financial.operations.infrastructure.database")
@EntityScan("br.com.financial.operations.infrastructure.database")
public class PersistenceConfig {

}
