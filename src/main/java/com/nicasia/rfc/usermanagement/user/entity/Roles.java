package com.nicasia.rfc.usermanagement.user.entity;

import com.nicasia.rfc.abstracts.BaseEntity;
import com.nicasia.rfc.enums.RoleName;
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
