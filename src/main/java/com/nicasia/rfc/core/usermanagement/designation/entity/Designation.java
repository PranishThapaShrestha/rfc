package com.nicasia.rfc.core.usermanagement.designation.entity;

import com.nicasia.rfc.shared.abstracts.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Designation extends BaseEntity{
    @Column(nullable = false)
    private String desgname;

    @Column(nullable = false)
    private String desgcode;
}
