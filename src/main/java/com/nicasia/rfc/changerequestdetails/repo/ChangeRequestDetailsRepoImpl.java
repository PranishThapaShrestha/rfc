package com.nicasia.rfc.changerequestdetails.repo;

import com.nicasia.rfc.changerequestdetails.entity.ChangeRequestDetails;
import com.nicasia.rfc.changerequestdetails.entity.QChangeRequestDetails;
import com.nicasia.rfc.shared.abstracts.BaseRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class ChangeRequestDetailsRepoImpl extends BaseRepositoryImpl<ChangeRequestDetails, ChangeRequestDetailsRepository> {

    QChangeRequestDetails qChangeRequestDetails = QChangeRequestDetails.changeRequestDetails;

    public ChangeRequestDetailsRepoImpl() {
        super(ChangeRequestDetails.class);
    }

    @Lazy
    @Autowired
    public void setRepository(ChangeRequestDetailsRepository changeRequestDetailsRepository) {
        this.repository = changeRequestDetailsRepository;
    }
}
