package com.nicasia.rfc.changerequestidentification.repo;

import com.nicasia.rfc.changerequestidentification.entity.ChangeRequestIdentification;
import com.nicasia.rfc.changerequestidentification.entity.QChangeRequestIdentification;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ChangeRequestIdentificationRepoImpl extends BaseRepositoryImpl<ChangeRequestIdentification, ChangeRequestIdentificationRepository> {
    QChangeRequestIdentification qChangeRequestIdentification = QChangeRequestIdentification.changeRequestIdentification;

    public ChangeRequestIdentificationRepoImpl(Class<?> domainClass) {
        super(ChangeRequestIdentification.class);
    }

    @Lazy
    @Autowired
    public void setRepository(ChangeRequestIdentificationRepository changeRequestIdentificationRepository){
        this.repository=changeRequestIdentificationRepository;
    }



}
