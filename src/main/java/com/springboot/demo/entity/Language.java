package com.springboot.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * <p>
 * {@link Word} represents the table <b>Languages</b> in the database.
 * </p>
 * 
 * @author krisnasamy.ayassamy
 * @since 1.0.0
 * @author ikkesh.ramanna
 */
@Entity
@Table(name = "languages")
public class Language {
	
	/**
	 * <p>
	 * Auto incremented primary key.
	 * </p>
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	/**
	 * <p>
	 * The language code. For example: EN, FR, etc...
	 * </p>
	 */
	@Column(name = "languageCode")
	private String languageCode;
	
	/**
	 * <p>
	 * No Argument Constructor.
	 * </p>
	 */
	public Language() {}
	
	/**
	 * <p>
	 * Constructor to initialize of the member variables.
	 * </p>
	 */
	public Language(Integer id, String languageCode) {
		super();
		this.id = id;
		this.languageCode = languageCode;
	}

	/*
	 * Getters and Setters.
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	@Override
	public String toString() {
		return "Languages [id=" + id + ", languageCode=" + languageCode + "]";
	}
}