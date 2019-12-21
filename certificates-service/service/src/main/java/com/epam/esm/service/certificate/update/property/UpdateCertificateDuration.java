package com.epam.esm.service.certificate.update.property;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.stereotype.Component;

@Component
public class UpdateCertificateDuration implements UpdateProperty<GiftCertificate, Integer> {
    public boolean update(GiftCertificate certificate, Integer duration){
        if (duration == null || certificate.getDuration().equals(duration)) return false;
        certificate.setDuration(duration);
        return true;
    }
}
