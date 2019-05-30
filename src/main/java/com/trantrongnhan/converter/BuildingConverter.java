package com.trantrongnhan.converter;

import org.modelmapper.ModelMapper;

import com.trantrongnhan.DTO.BuildingDTO;
import com.trantrongnhan.Entity.BuildingEntity;

public class BuildingConverter {
	public BuildingEntity toEntity(BuildingDTO dto) {
		ModelMapper modelMapper=new ModelMapper();
		BuildingEntity result=modelMapper.map(dto, BuildingEntity.class);
		return result;
	}
	public BuildingDTO toDTO(BuildingEntity entity) {
		ModelMapper modelMapper=new ModelMapper();
		BuildingDTO result=modelMapper.map(entity, BuildingDTO.class);
		return result;
	}
	
}
