package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.util.HashMap;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexCheckService;

@Service
public class FindexCreditAdapter implements FindexCheckService {
	Random random = new Random();
	HashMap<String, Integer> findexScore;

	public int CheckFindexScore(String tcNo) {
		findexScore = new HashMap<String, Integer>();
		int score = random.nextInt(1900) + 1;

		findexScore.put(tcNo, score);
		System.out.println(score);
		return score;
	}

}
