package com.jdsolutions.restful_web.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringResource {
	
	@GetMapping("/getSomeBean")
	public SomeBean getSomeBean() {
		return new SomeBean(1, "Name", "&&&&&&&&&&&&");
	}

}
