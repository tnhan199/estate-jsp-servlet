package com.trantrongnhan.Reponsitory;

import java.util.List;
import java.util.Map;

import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.paging.Pageable;
import com.trantrongnhan.sorting.Sorter;

public interface GenericReponsitory<T> {

	List<T> findAll();
	Integer insert(T obj);
	void update(T obj);
	void delete(Integer id);
	T findById(Integer id);
	List<T> search(T obj,PageRequest pageRequest,Sorter sorter);
	List<T> search(Map<String,Object>map,Pageable pageRequest,Sorter sorter,Object...where);
}
