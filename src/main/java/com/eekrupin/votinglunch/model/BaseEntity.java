package com.eekrupin.votinglunch.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.MappedSuperclass;

/**
 * Created by eekrupin on 09.08.2017.
 */


@MappedSuperclass

@Access(AccessType.FIELD)
public class BaseEntity {

    public static final int START_SEQ = 100000;

}
