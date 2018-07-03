package com.training.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.dto.TimeDTO;
import com.training.exception.BadRequestException;
import com.training.exception.NoDataFoundException;
import com.training.model.DBType;
import com.training.model.cassandra.TimeCass;
import com.training.model.jpa.Time;
import com.training.service.TimeService;

@RestController
@RequestMapping("/time")
public class TimeController {
	public static final Logger log = LoggerFactory.getLogger(TimeController.class);

	@Autowired
	private TimeService service;

	@GetMapping(value = "/", headers = "Accept=application/json")
	public List<TimeDTO> getAllLocations() {
		List<TimeCass> list = service.getAllTimes();
		List<TimeDTO> dtoList = list.stream().map(time -> convertToDTO(time, DBType.CASSANDRA))
				.collect(Collectors.toList());
		return dtoList;
	}

	@PostMapping(value = "/add", headers = "Accept=application/json")
	public TimeDTO addLocation(@RequestBody TimeDTO time) {
		return convertToDTO(service.addTime(convertToJPAEntity(time)), DBType.JPA);
	}

	@PutMapping(value = "/update", headers = "Accept=application/json")
	public TimeDTO updateLocation(@RequestBody TimeDTO time) {
		return convertToDTO(service.updateTime(convertToJPAEntity(time)), DBType.JPA);
	}

	public TimeDTO convertToDTO(Object obj, DBType type) {
		TimeDTO dto = new TimeDTO();
		if (obj == null) {
			throw new NoDataFoundException("Not found time");
		}
		if (type == DBType.JPA) {
			Time time = (Time) obj;
			dto.setTimeId(time.getTimeId());
			dto.setMonth(time.getMonth());
			dto.setQuarter(time.getQuarter());
			dto.setYear(time.getYear());
			dto.setCreatedAt(time.getCreatedAt());
			dto.setModifiedAt(time.getModifiedAt());
		} else if (type == DBType.CASSANDRA) {
			TimeCass time = (TimeCass) obj;
			dto.setTimeId(time.getTimeId());
			dto.setMonth(time.getMonth());
			dto.setQuarter(time.getQuarter());
			dto.setYear(time.getYear());
			dto.setCreatedAt(time.getCreatedAt());
			dto.setModifiedAt(time.getModifiedAt());
		} else {
			throw new BadRequestException("No type");
		}
		return dto;
	}

	public Time convertToJPAEntity(TimeDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		Time time = new Time();
		time.setTimeId(dto.getTimeId());
		time.setMonth(dto.getMonth());
		time.setQuarter(dto.getQuarter());
		time.setYear(dto.getYear());
		time.setCreatedAt(dto.getCreatedAt());
		time.setModifiedAt(dto.getModifiedAt());
		return time;
	}

	public TimeCass convertToCassandraEntity(TimeDTO dto) {
		if (dto == null) {
			throw new BadRequestException("Parameters not valid");
		}
		TimeCass time = new TimeCass();
		time.setTimeId(dto.getTimeId());
		time.setMonth(dto.getMonth());
		time.setQuarter(dto.getQuarter());
		time.setYear(dto.getYear());
		time.setCreatedAt(dto.getCreatedAt());
		time.setModifiedAt(dto.getModifiedAt());
		return time;
	}
}
