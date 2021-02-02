package com.wani.domain.common.domain;

import com.wani.domain.member.domain.Member;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class CommonEntity {

    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @OneToOne(fetch = FetchType.LAZY)
    @Column(name = "created_by")
    private Member createdMember;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(name = "modified_by")
    @OneToOne(fetch = FetchType.LAZY)
    private Member updatedMember;
}
