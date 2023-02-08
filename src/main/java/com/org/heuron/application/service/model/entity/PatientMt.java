package com.org.heuron.application.service.model.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "patient_mt")
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PatientMt {
    @Id
    @Column(name = "psn")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long psn;
    @Column(name = "name")
    String name;
    @Column(name = "age")
    Integer age;
    @Column(name = "sex")
    Integer sex;
    @Column(name = "disease")
    Integer disease;
    @CreationTimestamp()
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;
    @Column(name = "img_nm")
    String imgNm;
}
