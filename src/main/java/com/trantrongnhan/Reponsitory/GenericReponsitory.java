package com.trantrongnhan.Reponsitory;

import java.util.List;

import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.sorting.Sorter;

public interface GenericReponsitory<T> {

	List<T> findAll();
	Integer insert(T obj);
	void update(T obj);
	void delete(T obj);
	T findById(Integer id);
	List<T> search(T obj,PageRequest pageRequest,Sorter sorter);
}
