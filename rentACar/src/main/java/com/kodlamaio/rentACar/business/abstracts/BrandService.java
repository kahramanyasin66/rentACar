package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.brands.CreateBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.DeleteBrandRequest;
import com.kodlamaio.rentACar.business.requests.brands.UpdateBrandRequest;
import com.kodlamaio.rentACar.business.responses.brands.BrandResponse;
import com.kodlamaio.rentACar.business.responses.brands.ListBrandResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

// Response request 

public interface BrandService {
	Result add(CreateBrandRequest createBrandRequest);

	Result delete(DeleteBrandRequest deleteBrandRequest);

	Result update(UpdateBrandRequest updateBrandRequest);

	DataResult<List<ListBrandResponse>> getAll();

	DataResult<BrandResponse> getById(int id);

}