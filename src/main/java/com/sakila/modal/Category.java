package com.sakila.modal;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicUpdate
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "category")
@Getter // Fixed: Clean declarative encapsulation via Lombok
@Setter // Fixed: Clean declarative encapsulation via Lombok
@NoArgsConstructor // Fixed: Automatic generation of boilerplate empty constructor
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	// Fixed: Set explicitly to IDENTITY for safe, scalable primary key allocation
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_ID", unique = true, nullable = false)
	private int categoryId;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;

	// Fixed: Replaced raw vulnerable String with secure LocalDateTime mapping
	// configuration
	@Column(name = "LAST_UPDATE", nullable = false)
	private LocalDateTime lastUpdate;
}
