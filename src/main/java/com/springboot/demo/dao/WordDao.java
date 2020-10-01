package com.springboot.demo.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.demo.entity.Word;

/**
 * <p>
 * {@link WordDao} defines database functionalities for the table
 * <b>Words</b>.
 * It inherits from {@link CrudRepository}
 * </p>
 * 
 * @author ikkesh ramanna
 */
@Repository
public interface WordDao extends  CrudRepository<Word, Integer>{
	
	/**
	 * <p>
	 * Retrives {@param size} random words of a particular
	 * language code from the database
	 * </p>
	 * 
	 * @params languageCode
	 * 					The language's code of the words to be retrieved
	 * 
	 * @return {@link List} of {@link Word} instances
	 */
	@Query("SELECT W FROM Word W WHERE W.language.languageCode=:langCode ORDER BY RAND()")
	public List<Word> getWords(@Param("langCode") String languageCode, Pageable pageable);

	/**
	 * <p>
	 * Retrives a word by the word text and its language code
	 * </p>
	 * 
	 * @params langCode
	 * 					The language's code of the word to be retrieved
	 * @params word
	 * 			The word text
	 * 
	 * @return {@link Word} instance
	 */
	@Query("SELECT W FROM Word W WHERE W.word=:word and W.language.languageCode=:langCode")
	public Word getWord(@Param("word") String word, @Param("langCode") String langCode);
}
