package com.microservices.hotelrating.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.hotelrating.Utility.RatingUtility;
import com.microservices.hotelrating.repository.RatingRepository;
import com.microservices.hotelrating.service.RatingService;


@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	RatingRepository ratingrepo;
	
	@Override
	public RatingUtility  created(RatingUtility rating) {
		
		return ratingrepo.save(rating) ;
	}

	@Override
	public List<RatingUtility> getAllRating() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public Object getRatingByUserId(Long userId) {
//		
//		return ratingrepo.findById(userId);
//	}

//	  @Override
//	    public List<RatingUtility> getRatingByUserId(Long userId) {
//	        return ratingrepo.findByUserID(userId);
//	    }

	@Override
	public Optional<RatingUtility> getRatingByRatingId(Long ratingId){
		return ratingrepo.findById(ratingId);
	}

	@Override
	public Object getRatingByUserId(String userId) {

	return ratingrepo.findUserByUserId(userId);
	}


}
