package com.springboot.demo.services.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.springboot.demo.dao.LanguageDao;
import com.springboot.demo.dao.WordDao;
import com.springboot.demo.entity.Language;
import com.springboot.demo.entity.Word;
import com.springboot.demo.exceptions.ApiRequestException;
import com.springboot.demo.services.DictionaryService;

/**
 * <p>
 * {@link DictionaryServiceImpl} provides implementation for the interface
 * {@link DictionaryService}.
 * </p>
 * 
 * @author ikkesh.ramanna
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {
	
	/**
	 * <p>
	 * {@link WordDao} instance.
	 * </p>
	 */
	@Autowired
	private WordDao wordDao;
	
	/**
	 * <p>
	 * {@link LanguageDao} instance.
	 * </p>
	 */
	@Autowired
	private LanguageDao languageDao;
	
	/**
	 * {@inheritDoc}
	 */
	public Language getLanguageByCode(String languageCode) {
		return languageDao.getLanguage(languageCode);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public List<Word> getRandWords(String languageCode, Integer size) {
		Pageable pageable = PageRequest.of(0, size);
		return wordDao.getWords(languageCode, pageable);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Word getWordById(Integer id) {
		return wordDao.findById(id).get();
	}
	
	/**
	 * {@inheritDoc}
	 */
	public Boolean ifWordExist(String word, String langCode) {
		Word wordObj = wordDao.getWord(word, langCode);
		
		if (wordObj == null) {
			return false;
		} else {
			return true;
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void updateWord(Word word, String updatedWord) throws ApiRequestException {
		Word wordObj = wordDao.getWord(word.getWord(), word.getLanguage().getLanguageCode());
		
		if (wordObj == null) {
			throw new ApiRequestException("Word don't exist!", HttpStatus.OK);
		} else {
			wordObj.setWord(updatedWord);
			wordDao.save(wordObj);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void createWord(Word word) throws ApiRequestException {
		Word wordObj = wordDao.getWord(word.getWord(), word.getLanguage().getLanguageCode());
		
		if (wordObj == null) {
			Language language = languageDao.getLanguage(word.getLanguage().getLanguageCode());
			
			// If the language does not exist in the language table, then insert it
			if (language == null) {
				language = languageDao.save(new Language(null, word.getLanguage().getLanguageCode().toUpperCase()));
			}
						
			wordDao.save(new Word(null, word.getWord().toLowerCase(), language));
		} else {
			throw new ApiRequestException("Word already exists!", HttpStatus.OK);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	public void removeWord(String word, String langCode) throws ApiRequestException {
		Word wordObj = wordDao.getWord(word, langCode);
		
		if (wordObj == null) {
			throw new ApiRequestException("Word don't exist!", HttpStatus.OK);
		} else {
			wordDao.delete(wordObj);
		}
	}
	
}
