package com.bis.operox.inv.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CLASS_FIELD")
@Cache(usage = CacheConcurrencyStrategy.TRANSACTIONAL,region="classField")
public class ClassField {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID", nullable = false)
	private Long id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ENTITY_TYPE_ID", nullable=false )	
	private EntityType entityTypeId;
	
	@Column(name="FIELD_NAME", nullable = false)
	private String FieldName;
	
	@Column(name="DELIM_STR", nullable = false)
	private String delimStr;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@JsonIgnore
	public EntityType getEntityTypeId() {
		return entityTypeId;
	}

	public void setEntityTypeId(EntityType entityTypeId) {
		this.entityTypeId = entityTypeId;
	}

	public String getFieldName() {
		return FieldName;
	}

	public void setFieldName(String fieldName) {
		FieldName = fieldName;
	}

	public String getDelimStr() {
		return delimStr;
	}

	public void setDelimStr(String delimStr) {
		this.delimStr = delimStr;
	}
}
