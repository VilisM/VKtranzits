package lv.vktranzits.demo.services.impl;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lv.vktranzits.demo.config.EmployeeDetails;

@Component
public class AuditAwareImpl implements AuditorAware <String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // ja nav autorizējies, izvadīs null vērtību
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // izvada darbinieka e-pastu
        return Optional.of(((EmployeeDetails) authentication.getPrincipal()).getUsername());
    }
}

