package com.trantrongnhan.service;

import java.util.List;

import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.sorting.Sorter;

public interface IBuildingService {
	List<BuildingDTO>findAll();
	Integer insert(BuildingDTO dto);
	void update(BuildingDTO dto);
	void delete(BuildingDTO dto);
	BuildingDTO findById(Integer id);
	List<BuildingDTO>search(BuildingDTO obj,PageRequest pageRequest,Sorter sorter);
}
