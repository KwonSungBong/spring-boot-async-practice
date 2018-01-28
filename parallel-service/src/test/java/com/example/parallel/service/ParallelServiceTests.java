package com.example.parallel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParallelServiceTests {

	@Autowired
	private ParallelService parallelService;

	@Test
	public void sequentialSum() {
		long sum = parallelService.sequentialSum(5);

		System.out.println(sum);
	}

	@Test
	public void parallelSum() {
		long sum = parallelService.parallelSum(5);

		System.out.println(sum);
	}

	@Test
	public void iterateSum() {
		long sum1 = parallelService.measureSumPerf(parallelService::sequentialSum, 10_000_000);

		System.out.println("sequential sum : " + sum1 + " msecs");

		long sum2 = parallelService.measureSumPerf(parallelService::parallelSum, 10_000_000);

		System.out.println("parallel sum : " + sum2 + " msecs");
	}

	@Test
	public void rangeClosedSum() {
		long sum1 = parallelService.measureSumPerf(parallelService::rangedSum, 10_000_000);

		System.out.println("sequential sum : " + sum1 + " msecs");

		long sum2 = parallelService.measureSumPerf(parallelService::parallelRangedSum, 10_000_000);

		System.out.println("parallel sum : " + sum2 + " msecs");
	}

}
