package com.nicasia.rfc.usermanagement.department.entity;

import com.nicasia.rfc.abstracts.BaseEntity;
import com.nicasia.rfc.enums.Status;
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
public class Department extends BaseEntity {

    @Column(nullable = false)
    private String deptname;

    @Column(nullable = false)
    private String deptcode;

    private Status status;
}
