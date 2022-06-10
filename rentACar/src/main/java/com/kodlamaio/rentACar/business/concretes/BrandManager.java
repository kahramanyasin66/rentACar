package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.BrandService;
import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.brands.BrandResponse;
import com.kodlamaio.rentACar.business.responses.brands.ListBrandResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;

//BrandServiceImpl görebilirsin 
@Service
public class BrandManager implements BrandService {

	private BrandRepository brandRepository;
	private ModelMapperService modelMapperService;

	@Autowired // Git construtor parametresine bak somutunu new'le bana getir
	public BrandManager(BrandRepository brandRepository, ModelMapperService modelMapperService) {

		this.brandRepository = brandRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateBrandRequest createBrandRequest) {

		/*
		 * Brand brand = new Brand(); brand.setName(createBrandRequest.getName());
		 * this.brandRepository.save(brand);
		 */

		Brand brand = this.modelMapperService.forRequest().map(createBrandRequest, Brand.class);
		this.brandRepository.save(brand);
		return new SuccessResult("BRAND.ADDED");

	}

	@Override
	public Result delete(DeleteBrandRequest deleteBrandRequest) {

		int brandId = deleteBrandRequest.getId();
		this.brandRepository.deleteById(brandId);
		return new SuccessResult("BRAND.DELETED");

	}

	@Override
	public Result update(UpdateBrandRequest updateBrandRequest) {
		
		Brand brandToUpdate = modelMapperService.forRequest().map(updateBrandRequest, Brand.class);		
		
		//brandToUpdate.setName(updateBrandRequest.getName());
		this.brandRepository.save(brandToUpdate);
		return new SuccessResult("BRAND.UPDATED");

	}

	@Override
	public DataResult<List<ListBrandResponse>> getAll() {
		// return new SuccessDataResult<List<Brand>>(this.brandRepository.findAll());

		// brand'leri tek tek dolaş her bir brand için this. bilmem çalıştır collect
		// listede topla

		List<Brand> brands = this.brandRepository.findAll();
		
		List<ListBrandResponse> response = brands.stream()
				.map(brand -> this.modelMapperService.forResponse()
				.map(brand, ListBrandResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListBrandResponse>>(response);

	}

	@Override
	public DataResult<BrandResponse> getById(int id) {
		Brand brand  =  brandRepository.findById(id);
		BrandResponse response = this.modelMapperService.forResponse().map(brand, BrandResponse.class);
		return new SuccessDataResult<BrandResponse>(response,"BRAND.GETTED"); 
		// new SuccessDataResult<BrandResponse>(this.brandRepository.findById(brandResponse.getId()));
	}
}