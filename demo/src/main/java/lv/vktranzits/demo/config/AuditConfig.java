package lv.vktranzits.demo.config;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lv.vktranzits.demo.services.impl.AuditAwareImpl;


@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    // saglabā ierakstus par izdevušajiem un neizdevušajiem pieslēgumiem.
    @Bean
    public AuditEventRepository auditEventRepository() {
        return new InMemoryAuditEventRepository();
    }

    // funkcija, kura atgriezīs izveides autoru.
    @Bean
    public AuditAwareImpl auditorProvider() {
        return new AuditAwareImpl(); // izsauc implementāciju, kurā tiek noteikts izveides autors.
    }
}

