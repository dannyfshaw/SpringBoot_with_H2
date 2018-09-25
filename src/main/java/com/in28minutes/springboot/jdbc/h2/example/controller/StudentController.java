package com.in28minutes.springboot.jdbc.h2.example.controller;

import java.util.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.commons.id.serial.LongGenerator;

import com.in28minutes.springboot.jdbc.h2.example.student.Student;
import com.in28minutes.springboot.jdbc.h2.example.student.StudentJdbcRepository;

@RestController
public class StudentController {
	@Autowired
	StudentJdbcRepository studentJdbcRepository;

	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse findAllStudents() {
		RestResponse restResponse = new RestResponse();
		List<Student> allStudentsRetrieved = new ArrayList<Student>();
		try {
			allStudentsRetrieved = studentJdbcRepository.findAll();
			restResponse.setData(allStudentsRetrieved);
		} catch (Exception ex) {
			restResponse.setWasSuccessful(false);
			restResponse.setResponseMessage("Failed to find all student records:: exception was::" + ex.getMessage());
			return restResponse;
		}
		restResponse.setWasSuccessful(true);
		restResponse.setResponseMessage("Successfully retrieved " + allStudentsRetrieved.size() + " student records");
		return restResponse;
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	@ResponseBody
	public RestResponse deleteAllStudents() {
		RestResponse restResponse = new RestResponse();
		try {
			int numOfRecordsDeleted = studentJdbcRepository.deleteAll();
			restResponse.setData(numOfRecordsDeleted);
		} catch (Exception ex) {
			restResponse.setWasSuccessful(false);
			restResponse.setResponseMessage("Failed to delete all student records:: exception was::" + ex.getMessage());
			return restResponse;
		}
		restResponse.setWasSuccessful(true);
		restResponse.setResponseMessage("Successfully deleted all student records");
		return restResponse;
	}

	@RequestMapping(value="/insert/{numOfrecords}", method = RequestMethod.POST)
	@ResponseBody
	public RestResponse insertStudents(@RequestBody Student student, @PathVariable("numOfrecords") int numOfrecords){
		RestResponse restResponse = new RestResponse();
		
		try {
			for(int i = 0;i<numOfrecords;i++) {
		//	long uniqueID = UUID.randomUUID().getMostSignificantBits(); 
			student.setId(UUID.randomUUID().getMostSignificantBits());
		restResponse.setData(studentJdbcRepository.insert(student));
			}
		}catch(Exception ex) {
			restResponse.setWasSuccessful(false);
			restResponse.setResponseMessage("Failed to insert student record:: exception was::"+ex.getMessage());
			return restResponse;
		}
		restResponse.setWasSuccessful(true);
		restResponse.setResponseMessage("Successfully inserted student record");
		return restResponse;
	}
}
