package com.operations.client.service;

import com.sample.test.SampleAdder;

public class NewAdder extends SampleAdder {
	
	@Override
	public int add(int a) {
		return a+5;
	}

}
