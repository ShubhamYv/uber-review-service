package com.reviewservice.models;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
public abstract class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	protected Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	protected Date updatedAt;

	// Create a builder for BaseModel
	@SuppressWarnings("unchecked")
	public abstract static class BaseModelBuilder<T extends BaseModelBuilder<T>> {
		protected Long id;
		protected Date createdAt;
		protected Date updatedAt;

		public T id(Long id) {
			this.id = id;
			return (T) this;
		}

		public T createdAt(Date createdAt) {
			this.createdAt = createdAt;
			return (T) this;
		}

		public T updatedAt(Date updatedAt) {
			this.updatedAt = updatedAt;
			return (T) this;
		}
	}
}
