package com.nicasia.rfc.changerequestidentification.repo;

import com.nicasia.rfc.changerequestidentification.entity.ChangeRequestIdentification;
import com.nicasia.rfc.changerequestidentification.entity.QChangeRequestIdentification;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;

public class ChangeRequestIdentificationRepoImpl extends BaseRepositoryImpl<ChangeRequestIdentification, ChangeRequestIdentificationRepository> {
    QChangeRequestIdentification qChangeRequestIdentification = QChangeRequestIdentification.changeRequestIdentification;

    public ChangeRequestIdentificationRepoImpl(Class<?> domainClass) {
        super(ChangeRequestIdentification.class);
    }

    public void setRepository(ChangeRequestIdentificationRepository changeRequestIdentificationRepository){
        this.repository=changeRequestIdentificationRepository;
    }



}
