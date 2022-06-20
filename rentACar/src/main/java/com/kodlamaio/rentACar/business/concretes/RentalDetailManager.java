package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalDetailService;
import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.responses.rentalDetails.ListRentalDetailResponse;
import com.kodlamaio.rentACar.business.responses.rentalDetails.RentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalDetailRepository;
import com.kodlamaio.rentACar.entities.concretes.RentalDetail;
@Service
public class RentalDetailManager  implements RentalDetailService{
	private ModelMapperService modelMapperService;
	private RentalDetailRepository rentalDetailRepository;
	
    @Autowired
	public RentalDetailManager(ModelMapperService modelMapperService, RentalDetailRepository rentalDetailRepository) {
		super();
		this.modelMapperService = modelMapperService;
		this.rentalDetailRepository = rentalDetailRepository;
	}

	@Override
	public Result add(CreateRentalDetailRequest createRentalDetailRequest) {
		RentalDetail rentalDetail =  this.modelMapperService.forRequest().map(createRentalDetailRequest,RentalDetail.class);
		this.rentalDetailRepository.save(rentalDetail);		
		
		return new SuccessResult("RENTALDETAIL.ADDED");
	}

	@Override
	public Result update(UpdateRentalDetailRequest updateRentalDetailRequest) {
		RentalDetail rentalDetailToUpdate = this.modelMapperService.forRequest().map(updateRentalDetailRequest, RentalDetail.class);
		this.rentalDetailRepository.save(rentalDetailToUpdate);
		
		return new SuccessResult("RENTALDETAIL.UPDATED");
	}

	@Override
	public Result delete(DeleteRentalDetailRequest deleteRentalDetailRequest) {
		RentalDetail rentalDetail = this.modelMapperService.forRequest().map(deleteRentalDetailRequest, RentalDetail.class);		
		this.rentalDetailRepository.delete(rentalDetail);
		return  new SuccessResult("RENTALDETAIL.DELETED");
	}

	@Override
	public DataResult<List<ListRentalDetailResponse>> getAll() {
		List<RentalDetail> rentalDetails = this.rentalDetailRepository.findAll();
		List<ListRentalDetailResponse> response = rentalDetails.stream()
				.map(rentalDetail -> this.modelMapperService.forResponse()
				.map(rentalDetails, ListRentalDetailResponse.class)).collect(Collectors.toList());
		return new SuccessDataResult<List<ListRentalDetailResponse>>(response);
	}

	@Override
	public DataResult<RentalDetailResponse> getById(int id) {
		RentalDetail rentalDetail = this.rentalDetailRepository.findById(id);
		RentalDetailResponse response = this.modelMapperService.forResponse().map(rentalDetail, RentalDetailResponse.class);
		return new SuccessDataResult<RentalDetailResponse>(response);
	}
 
}
