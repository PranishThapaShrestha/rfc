package com.nicasia.rfc.core.usermanagement.user.entity;

import com.nicasia.rfc.shared.abstracts.BaseEntity;
import com.nicasia.rfc.shared.enums.RoleName;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
public class Roles extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @NaturalId
    private RoleName name;

}
