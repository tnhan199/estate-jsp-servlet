package com.trantrongnhan.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.Entity.BuildingEntity;
import com.trantrongnhan.Reponsitory.impl.BuildingReponsitory;
import com.trantrongnhan.converter.BuildingConverter;
import com.trantrongnhan.paging.PageRequest;
import com.trantrongnhan.service.IBuildingService;
import com.trantrongnhan.sorting.Sorter;

public class BuildingService implements IBuildingService {
	private BuildingConverter buildingConverter;
	private BuildingReponsitory buildingReponsitory;
	
	public BuildingService() {
		buildingConverter=new BuildingConverter();
		buildingReponsitory=new BuildingReponsitory();
	}

	@Override
	public Integer insert(BuildingDTO dto) {
		return buildingReponsitory.insert(buildingConverter.toEntity(dto));
	}

	@Override
	public void update(BuildingDTO dto) {
		buildingReponsitory.update(buildingConverter.toEntity(dto));
	}

	@Override
	public void delete(BuildingDTO dto) {
		buildingReponsitory.delete(buildingConverter.toEntity(dto));
	}

	@Override
	public BuildingDTO findById(Integer id) {
		return buildingConverter.toDTO(buildingReponsitory.findById(id));
	}

	@Override
	public List<BuildingDTO> findAll() {
		List<BuildingDTO> result=new ArrayList();
		List<BuildingEntity>list=buildingReponsitory.findAll();
		for(BuildingEntity i:list) {
			result.add(buildingConverter.toDTO(i));
		}
		return result;
	}

	@Override
	public List<BuildingDTO> search(BuildingDTO obj,PageRequest pageRequest,Sorter sorter) {
		// TODO Auto-generated method stub
		List<BuildingDTO> result=new ArrayList();
		List<BuildingEntity>list=buildingReponsitory.search(buildingConverter.toEntity(obj),pageRequest,sorter);
		for(BuildingEntity i:list) {
			result.add(buildingConverter.toDTO(i));
		}
		return result;
		
	}

}
