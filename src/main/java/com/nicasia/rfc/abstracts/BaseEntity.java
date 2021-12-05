package com.nicasia.rfc.abstracts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity extends Persist<Long> {

    @JsonIgnore
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @CreatedDate
    private Date createdAt = new Date();

    @JsonIgnore
    @Getter
    @Setter(AccessLevel.PROTECTED)
    @LastModifiedDate
    private Date lastModified;

    @Version
    private Long version = 1L;

    @CreatedBy
    @Column(updatable = false)
    private Long createdByUserId;

    @LastModifiedBy
    private Long updatedByUserId;

    @JsonIgnore
    @Override
    public boolean isNew() {
        return super.isNew();
    }

    @Override
    public void setId(Long id) {
        super.setId(id);
    }

    @PrePersist
    public void prePersist() {
        this.lastModified = new Date();
    }


}


