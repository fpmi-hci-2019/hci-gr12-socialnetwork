package com.epam.esm.entity;

import java.sql.Timestamp;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Order.class)
public abstract class Order_ {

	public static volatile ListAttribute<Order, CertificateSnapshot> certificates;
	public static volatile SingularAttribute<Order, Long> id;
	public static volatile SingularAttribute<Order, User> user;
	public static volatile SingularAttribute<Order, Timestamp> timestamp;

	public static final String CERTIFICATES = "certificates";
	public static final String ID = "id";
	public static final String USER = "user";
	public static final String TIMESTAMP = "timestamp";

}

