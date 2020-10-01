package com.springboot.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * <p>
 * {@link Word} represents the table <b>Words</b> in the database.
 * </p>
 * 
 * @author krisnasamy.ayassamy
 * @since 1.0.0
 * @author ikkesh.ramanna
 */
@Entity
@Table(name = "words")
public class Word {
	
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
	 * Word found in a dictionary of a particular language.
	 * </p>
	 */
	@Column(name = "word")
	private String word;
	
	/**
	 * <p>
	 * {@link Language} entity that the {@link Word#word} is associated with.
	 * </p>
	 * 
	 * <p>
	 * There is a Many-To-One relationship between the Word and Language
	 * entities.
	 * </p>
	 */
	@ManyToOne
	@JoinColumn(name="language")
	private Language language;

	/**
	 * <p>
	 * No Argument Constructor.
	 * </p>
	 */
	public Word() {}
	
	/**
	 * <p>
	 * Constructor to initialize of the member variables.
	 * </p>
	 */
	public Word(Integer id, String word, Language language) {
		super();
		this.id = id;
		this.word = word;
		this.language = language;
	}

	/*
	 * Getters and Setter.
	 */
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return "Word [id=" + id + ", word=" + word + ", language=" + language + ", getId()=" + getId() + ", getWord()="
				+ getWord() + ", getLanguage()=" + getLanguage() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
