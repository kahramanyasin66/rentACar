package com.kodlamaio.rentACar.core.utilities.mapping;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

@Service // git otomatik dependency enjecktions yap IOC'den yararlan demek 
// bellekte oluşturulmuş instance'a bean deniyor fasulye 
public class ModelMapperServiceImpl implements ModelMapperService {
	private ModelMapper modelMapper;

	public ModelMapperServiceImpl(ModelMapper modelMapper) {

		this.modelMapper = modelMapper;
	}

	@Override
	public ModelMapper forResponse() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.LOOSE); //gevşek herşey map olmak zorunda değil
		return this.modelMapper;
	}

	@Override
	public ModelMapper forRequest() {
		this.modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STANDARD); // herşey map olacak
		return this.modelMapper;
	}

}