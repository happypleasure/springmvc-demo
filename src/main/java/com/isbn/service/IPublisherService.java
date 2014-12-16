package com.isbn.service;

import com.isbn.pojo.Book;
import com.isbn.pojo.Publisher;
import com.isbn.util.QueryParams;
import com.isbn.util.ReturnObj;

public interface IPublisherService {

	Publisher login(Publisher publisher) throws Exception;

	ReturnObj queryBookList(QueryParams queryParams) throws Exception;

	int addBook(Book book) throws Exception;

	Book queryBookDetailById(Integer id)throws Exception;

	int auth(Publisher publisher)throws Exception;

	ReturnObj queryBook(Book book)throws Exception;
	
}
