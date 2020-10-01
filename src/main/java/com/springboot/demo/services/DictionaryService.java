package com.springboot.demo.services;

import java.util.List;

import com.springboot.demo.entity.Language;
import com.springboot.demo.entity.Word;
import com.springboot.demo.exceptions.ApiRequestException;

/**
 * <p>
 * {@link DictionaryService} defines service methods related to the table Words
 * in the database
 * </p>
 * 
 * @author ikkesh.ramanna
 */
public interface DictionaryService {

	/**
	 * <p>
	 * Retrives a {@link Language} instance of a particular language code
	 * </p>
	 * 
	 * @param langCode
	 * 				language code that the {@link Language} object must be in
	 *  
	 * @returns a {@link Language} instance of the language code @param langCode
	 * 
	 */
	public Language getLanguageByCode(String languageCode);
	
	/**
	 * <p>
	 * Retrieves {@param size} words of language {@param langCode}
	 * </p>
	 * 
	 * @param langCode
	 * 				language code of the language that the words must be in.
	 * @param size
	 * 			the number of words to retrieve		
	 *  
	 * @returns a {@link List} instance of {@link Word} objects
	 * 
	 */
	public List<Word> getRandWords(String languageCode, Integer size);
	
	/**
	 * <p>
	 * Retrieves a word form the database by its Id
	 * </p>
	 * 
	 * @param id
	 * 			The Id of the word	
	 *  
	 * @returns a {@link Word} instance which id is {@param id}
	 * 
	 */
	public Word getWordById(Integer id);
	
	/**
	 * <p>
	 * Checks whether a word exist in the database
	 * </p>
	 * 
	 * @param lanCode
	 * 				The language code of the language that the word is
	 * @param word
	 * 			The word text
	 * 
	 * @returns true if the word exists, false otherwise
	 * 
	 */
	public Boolean ifWordExist(String word, String langCode);
	
	/**
	 * <p>
	 * Updates a word in the database.
	 * </p>
	 * 
	 * @param wordObj
	 * 			{@link Word} object specifying the word to be updated
	 * 			The {@link Word} object does not need to have the id property
	 * 
	 * @param updatedWord
	 * 				The updated word text.
	 * 
	 * @throws ApiRequestException
	 * 					thrown if the word to be updated does not exists
	 */
	public void updateWord(Word word, String updatedWord) throws ApiRequestException;
	
	/**
	 * <p>
	 * Adds a word in the database.
	 * </p>
	 * 
	 * @param wordObj
	 * 			{@link Word} instance to be created
	 * 			The {@link Word} object does not need to have the id property
	 * 
	 * @throws ApiRequestException
	 * 					thrown if the word to be created already exists
	 */
	public void createWord(Word word) throws ApiRequestException;
	
	/**
	 * <p>
	 * Deletes a word from the database.
	 * </p>
	 * 
	 * @param langCode
	 * 			The language code of the language
	 * 			that the word to be deleleted belongs to
	 * 
	 * @param word
	 * 			The word to be deleted
	 * @throws ApiRequestException
	 * 					thrown if the word to be deleted does not exists
	 */
	public void removeWord(String word, String langCode) throws ApiRequestException;
	
}
