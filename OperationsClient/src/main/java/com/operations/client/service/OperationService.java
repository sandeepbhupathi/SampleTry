package com.operations.client.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.operations.client.dto.ClientOperationResponse;
import com.sample.test.SampleAdder;

@Service
public class OperationService {

	private static final Logger logger = LoggerFactory.getLogger(OperationService.class);
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NewAdder newAdder;
	
	@Autowired
	@Qualifier("sampleAdder")
	private SampleAdder sampleAdder;
	
	
	public int getAdder(int noOfAdder,String sampleAdderStr){
		if(sampleAdderStr.equalsIgnoreCase("SAMPLE")){
			return sampleAdder.add(noOfAdder);
		}
		return newAdder.add(noOfAdder);
	}
	
	public long performAddOperation(int noOfOperations) {
		
		AtomicLong finalSum=new AtomicLong(0);	
		
		IntStream.range(1, noOfOperations+1).forEach((i)->{finalSum.set(invokeAddService(i)+finalSum.get());});
		
		return finalSum.get();
	}
	
	public int invokeAddService(int noOfEach) {
		String requestURL = "http://localhost:9090/operations/add/{baseNo}/{noOfOperation}";
		Map<String, String> mapParam = new HashMap<>();
		mapParam.put("noOfOperation", "" + noOfEach);
		mapParam.put("baseNo", "" + noOfEach);

		ClientOperationResponse operationResponse = restTemplate.getForObject(requestURL, ClientOperationResponse.class, mapParam);
		return operationResponse.getResponseRes();
	}

	public long performMultiThreadAddOperation(int noOfOperations) throws InterruptedException {
		AtomicLong finalSum=new AtomicLong(0);	
		
		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()*10);
		
		List<Callable<Boolean>> runnables = new ArrayList<>();
		
		IntStream.range(1, noOfOperations + 1).forEach((i) -> {
				runnables.add(() -> {
				synchronized (this) {
					finalSum.set(invokeAddService(i) + finalSum.get());
				}	
				//System.out.println(i+"::"+finalSum.get());
				return true;
			});
		});
		
		executor.invokeAll(runnables).stream().map(future->{
			try{
				return future.get();
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}).count();
		
		return finalSum.get();
	}
	
	
	public long performParllelStramsAddOperation(int noOfOperations) throws InterruptedException {
		AtomicLong finalSum=new AtomicLong(0);
		IntStream.range(1, noOfOperations + 1).parallel().forEach((i) -> {
			synchronized (this) {
				finalSum.set(invokeAddService(i) + finalSum.get());	
			}
			
		});
		return finalSum.get();
		
	}
	
	
	/*private HttpEntity<String> httpEntity() {
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    HttpEntity <String> entity = new HttpEntity<String>(headers);
		return entity;
	}*/

}
