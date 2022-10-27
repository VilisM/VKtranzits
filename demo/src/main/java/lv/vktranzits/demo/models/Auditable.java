package lv.vktranzits.demo.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<T> {

    // Saglabā ierakstu par to, kas ir izveidojis.
    @CreatedBy
    protected T createdBy;

    // Saglabā entītijas izveides datumu.
    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Date createdDate;

    // Saglabā ierakstu par to, kas ir pēdējais labojis.
    @LastModifiedBy
    protected T lastModifiedBy;

    // saglabā pēdējo modificēšanas datumu
    @LastModifiedDate
    @Column(nullable = false, updatable = false)
    protected Date lastModifiedDate;
}
