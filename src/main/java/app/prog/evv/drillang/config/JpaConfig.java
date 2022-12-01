package app.prog.evv.drillang.config;

import app.prog.evv.drillang.repository.BaseJpaRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "app.prog.evv.drillang.repository",
                        repositoryBaseClass = BaseJpaRepositoryImpl.class)
public class JpaConfig {

}
