package com.example.parallel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForkJoinServiceTests {

	@Autowired
	private ForkJoinService forkJoinService;

	@Test
	public void forkJoinSum() {
		long sum = forkJoinService.measureSumPerf(forkJoinService::forkJoinSum, 10_000_000);

		System.out.println("forkJoin sum : " + sum + " msecs");
	}

}
