package com.github.crudprac.entity;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Time {

        @Column(name = "created_date")
@CreatedDate
private String createdDate;

@Column(name = "modified_date")
@LastModifiedDate
private String modifiedDate;

@PrePersist //해당 엔티티를 저장하기 이전에 실행
public void onPrePersist(){
this.createdDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
this.modifiedDate = this.createdDate;
}

@PreUpdate //해당 엔티티를 업데이트 하기 이전에 실행
public void onPreUpdate(){
this.modifiedDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
}
}