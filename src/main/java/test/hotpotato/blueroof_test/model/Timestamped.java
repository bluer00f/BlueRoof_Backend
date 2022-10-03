package test.hotpotato.blueroof_test.model;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
public abstract class Timestamped {

    @CreatedDate    //생성일자
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate   //최종수정일자
    private LocalDateTime lastModifiedDate;
}
