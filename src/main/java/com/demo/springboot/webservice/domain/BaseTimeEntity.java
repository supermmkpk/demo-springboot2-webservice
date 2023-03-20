package com.demo.springboot.webservice.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //JPA Entity 클래스들이 BaseTimeEntity를 상속할때 필드들(createdDate, modifiedDate)도 칼럼으로 인식
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate //Entity가 생성돼 저장될 때 시간을 자동 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //조회된 Entity가 수정돼 저장될 때 시간을 자동 저장
    private LocalDateTime modifiedDate;
}
