package com.springboot.demo.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.springboot.demo.entity.Language;

/**
 * <p>
 * {@link LanguageDao} defines database functionalities for the table
 * <b>Languages</b>.
 * It inherits from {@link CrudRepository}
 * </p>
 * 
 * @author ikkesh ramanna
 */
public interface LanguageDao extends CrudRepository<Language, Integer> {
	
	/**
	 * <p>
	 * Retrives a language from the database by its language code 
	 * </p>
	 * 
	 * @params languageCode
	 * 					The language code of the language to be retrieved
	 * 
	 * @return {@link Language} instance of the language
	 */
	@Query("SELECT L FROM Language L WHERE L.languageCode=:langCode")
	public Language getLanguage(@Param("langCode") String languageCode);
	
}
