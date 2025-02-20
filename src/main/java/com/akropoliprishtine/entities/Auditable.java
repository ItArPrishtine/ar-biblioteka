package com.akropoliprishtine.entities;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import java.util.Date;


@Getter(AccessLevel.PROTECTED)
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable<U>
{
    @CreatedBy
    @Column(name = "created_by")
    protected U createdBy;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Date createdDate;

    @LastModifiedBy
    @Column(name = "last_modified_by")
    protected U lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate;
    
    @Column(name = "deleted", columnDefinition = "boolean default false")
    protected boolean deleted = Boolean.FALSE;
    
}
