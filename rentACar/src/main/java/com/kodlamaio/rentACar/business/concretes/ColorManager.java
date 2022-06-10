package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.ColorService;
import com.kodlamaio.rentACar.business.requests.colors.CreateColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.DeleteColorRequest;
import com.kodlamaio.rentACar.business.requests.colors.UpdateColorRequest;
import com.kodlamaio.rentACar.business.responses.colors.ColorResponse;
import com.kodlamaio.rentACar.business.responses.colors.ListColorResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class ColorManager implements ColorService {
	ColorRepository colorRepository;
	ModelMapperService modelMapperService ;
	

	@Autowired
	public ColorManager(ColorRepository colorRepository,ModelMapperService modelMapperService) {	
		this.colorRepository = colorRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateColorRequest creteColorRequest) {
		Color color = this.modelMapperService.forRequest().map(creteColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.ADDED");

	}

	@Override
	public Result delete(DeleteColorRequest deleteColorRequest) {
		int colorId = deleteColorRequest.getId();
		this.colorRepository.deleteById(colorId);
		return new SuccessResult("COLOR.DELETED");
	}

	@Override
	public Result update(UpdateColorRequest updateColorRequest) {
		
		
		/*Color colorToUpdate = colorRepository.findById(updateColorRequest.getId());
		updateColorRequest.setName(colorToUpdate.getName());*/
		Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
		this.colorRepository.save(color);
		return new SuccessResult("COLOR.UPDATED");

	}

	@Override
	public DataResult<List<ListColorResponse>> getAll() {
		
		return null ; 
		//new SuccessDataResult<List<ColorResponse>>(this.colorRepository.findAll());
	}

	@Override
	public DataResult<ColorResponse> getById(int id) {
		
		return null ;
		//new SuccessDataResult<ColorResponse>(this.colorRepository.findById(colorResponse.getId()));
	}
}