package com.trantrongnhan.service;

import java.util.List;
import java.util.Map;

import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.paging.Pageable;
import com.trantrongnhan.sorting.Sorter;

public interface IBuildingService {
	List<BuildingDTO>findAll();
	Integer insert(BuildingDTO dto);
	void update(BuildingDTO dto);
	void delete(Integer id);
	BuildingDTO findById(Integer id);
	List<BuildingDTO>search(BuildingDTO obj,PageRequest pageRequest,Sorter sorter);
	List<BuildingDTO>search(Map<String,Object>map,Pageable pageable,Sorter sorter,Object...where);
}
