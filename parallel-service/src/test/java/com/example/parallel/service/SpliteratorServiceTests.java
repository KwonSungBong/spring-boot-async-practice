package com.example.parallel.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpliteratorServiceTests {

	@Autowired
	private SpliteratorService spliteratorService;

	@Test
	public void test() {
		final String SENTENCE = " Nel mezzo del cammin   di  nostra  vita " +
				"mi   ritrovai  in una   selva oscura" +
				" ch   la    dritta  via era   smarrita";

		int count = spliteratorService.countWorkds1(SENTENCE);

		System.out.println(count);
	}

	@Test
	public void test2() {
		final String SENTENCE = " Nel mezzo del cammin   di  nostra  vita " +
				"mi   ritrovai  in una   selva oscura" +
				" ch   la    dritta  via era   smarrita";

		int count = spliteratorService.countWorkds2(SENTENCE);

		System.out.println(count);
	}

}
