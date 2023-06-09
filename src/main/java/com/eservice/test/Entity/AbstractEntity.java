package com.eservice.test.Entity;

import com.eservice.test.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class AbstractEntity implements Serializable {

    private static final long serialVersionUID = -4248379706483318919L;

    @Id
    @JsonView(Views.Thin.class)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long Id = 0;

    @JsonView(Views.Thin.class)
    @Column(name = "boId", nullable = false)
    private String boId = "";

    @JsonView(Views.Thin.class)
    @Column(name = "entityStatus")
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    @JsonView(Views.Thin.class)
    private String createdDate;

    public AbstractEntity() {
        setBoId(SystemConstant.BOID_REQUIRED);
        setEntityStatus(EntityStatus.ACTIVE);
        setCreatedDate(DateUtils.currentDateTime());
    }

    public boolean isBoIdRequired(String boId) {
        return SystemConstant.BOID_REQUIRED.equals(boId);
    }

}
