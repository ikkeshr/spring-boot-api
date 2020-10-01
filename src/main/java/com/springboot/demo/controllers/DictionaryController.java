package com.springboot.demo.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.demo.entity.Language;
import com.springboot.demo.entity.Word;
import com.springboot.demo.services.impl.DictionaryServiceImpl;

/**
 * <p>
 * {@link DictionaryController} is the controller which listens to requests concerning dictionary services
 * interact with.
 * </p>
 * 
 * @author ikkesh.ramanna
 */
@RestController
@CrossOrigin("http://localhost:4200") // Enable CORS for Origin http://localhost:4200 (Angular App)
public class DictionaryController {

	/**
	 * <p>
	 * {@link DictionaryServiceImpl} instance.
	 * </p>
	 */
	@Autowired
	private DictionaryServiceImpl dictionaryService;
	
	/**
	 * <p>
	 * Services GET request made to the "/" endpoint.
	 * This endpoint has no use, only for testing purposes.
	 */
	@RequestMapping("/")
	public String index() {
		return "Greetings from Dictionary API!";
	}
	
	/**
	 * <p>
	 * Listens to GET requests made to the '/dictionary/language/{langCode}' endpoint.
	 * This endpoint is used to retrive a {@link Language} instance of a particular language code
	 * </p>
	 * 
	 * @param langCode
	 * 				language code that the {@link Language} object must be in
	 *  
	 * @returns a {@link Language} instance of the language code specified as param
	 * 
	 */
	@RequestMapping(path="/dictionary/language/{langCode}")
	public Language getLanguageByCode(@PathVariable("langCode") String langCode) {
		return this.dictionaryService.getLanguageByCode(langCode);
	}
	
	/**
	 * <p>
	 * Listens to GET requests made to the '/dictionary/{lang}/{size}' endpoint.
	 * This endpoint is used to retrieve {@param size} words of language {@param langCode}
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
	@RequestMapping(path="/dictionary/{lang}/{size}", method = RequestMethod.GET)
	public List<Word> getRandWords(@PathVariable("lang") String langCode, @PathVariable("size") int size) {
		List<Word> words = new ArrayList<Word>();
		words = this.dictionaryService.getRandWords(langCode, size);
		return words;
	}
	
	/**
	 * <p>
	 * Listens to GET requests made to the '/dictionary/{id}' endpoint.
	 * This endpoint is used to retrieve a word form the database by its Id
	 * </p>
	 * 
	 * @param id
	 * 			The Id of the word	
	 *  
	 * @returns a {@link Word} instance which id is {@param id}
	 * 
	 */
	@RequestMapping(path="/dictionary/{id}")
	public Word getWordById(@PathVariable("id") Integer id) {
		Word words = this.dictionaryService.getWordById(id);
		return words;
	}
	
	/**
	 * <p>
	 * Listens to GET requests made to the '/dictionary' endpoint.
	 * This endpoint is used to check whether a word exist in the database
	 * </p>
	 * 
	 * @param lanCode
	 * 				A Request Parameter specifying the language code of the language that the word is
	 * @param word
	 * 			Request parameter specifying the word
	 * 
	 * @returns true if the word exists, false otherwise
	 * 
	 */
	@RequestMapping(path="/dictionary")
	public Boolean checkIfWordExist(@RequestParam(name = "lang") String langCode, @RequestParam(name = "word") String word) {
		return this.dictionaryService.ifWordExist(word, langCode);
	}
	
	/**
	 * <p>
	 * Listens to PUT requests made to the '/dictionary/modify' endpoint.
	 * This endpoint is used update a word in the database.
	 * </p>
	 * 
	 * @param wordObj
	 * 			{@link Word} object found inthe request body. It is the word to be updated
	 * 			The {@link Word} object does not need to have the id property
	 * 
	 * @param updatedWord
	 * 				request parameter specifying the updated word text.
	 * 
	 */
	@RequestMapping(path="/dictionary/modify", method = RequestMethod.PUT)
	public void putWord(@RequestBody Word wordObj, @RequestParam(name = "updatewordto") String updatedWord) {
		this.dictionaryService.updateWord(wordObj, updatedWord);
	}
	
	/**
	 * <p>
	 * Listens to POST requests made to the '/dictionary/create' endpoint.
	 * This endpoint is used add a word in the database.
	 * </p>
	 * 
	 * @param wordObj
	 * 			{@link Word} object found in the request body. It is the word to be created
	 * 			The {@link Word} object does not need to have the id property
	 * 
	 * 
	 */
	@RequestMapping(path="/dictionary/create", method = RequestMethod.POST)
	public void postWord(@RequestBody Word wordObj) {
		this.dictionaryService.createWord(wordObj);
	}
	
	/**
	 * <p>
	 * Listens to DELETE requests made to the '/dictionary/delete' endpoint.
	 * This endpoint is used delete a word from the database.
	 * </p>
	 * 
	 * @param langCode
	 * 			A variable found in the url specifying the language code of the language
	 * 			that the word to be deleleted belongs to
	 * 
	 * @param word
	 * 			the word to be deleted
	 * 
	 */
	@RequestMapping(path="/dictionary/delete/{lang}", method = RequestMethod.DELETE)
	public void deleteWord(@PathVariable("lang") String langCode, @RequestParam(name = "word") String word) {
		this.dictionaryService.removeWord(word, langCode);
	}
	
}
