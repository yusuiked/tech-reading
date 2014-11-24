package sample.biz.service;

import org.springframework.stereotype.Service;

@Service
public class SampleServiceImpl implements SampleService {

	@Override
	public String createFullName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}

}
