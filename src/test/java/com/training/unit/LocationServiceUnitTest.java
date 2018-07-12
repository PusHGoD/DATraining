package com.training.unit;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.training.model.cassandra.LocationCass;
import com.training.model.jpa.Location;
import com.training.repository.LocationCassRepository;
import com.training.repository.LocationRepository;
import com.training.service.impl.LocationServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class LocationServiceUnitTest {

	@InjectMocks
	private LocationServiceImpl service;

	@Mock
	private LocationCassRepository repository;

	@Mock
	private LocationRepository jpaRepository;

	UUID testUuid = UUID.fromString("b59466d1-75f1-11e8-a123-39c7db8a5a4a");
	UUID testUuid2 = UUID.fromString("2dd545c0-75f1-11e8-a123-39c7db8a5a4a");
	UUID wrongTestUuid = UUID.fromString("c381032b-7057-11e8-8754-c3e87a3ddddc");

	@Test
	public void testGetAllLocations() {
		LocationCass location1 = new LocationCass();
		location1.setLocationId(testUuid);
		LocationCass location2 = new LocationCass();
		location2.setLocationId(testUuid2);
		List<LocationCass> list = new ArrayList<LocationCass>();
		list.add(location1);
		list.add(location2);
		when(repository.findAll()).thenReturn(list);
		assertEquals(list, service.getAllLocations());
	}

	@Test
	public void testAddLocation() {
		Location location = new Location(testUuid, "VN", "HCMC", null, null);
		when(jpaRepository.save(location)).thenReturn(location);
		assertEquals(location, service.addLocation(location));
	}

	@Test
	public void testUpdateLocation() {
		Location location = new Location(testUuid2, "VN", "Ha Noi", null, null);
		when(jpaRepository.findById(testUuid2)).thenReturn(Optional.of(location));
		when(jpaRepository.save(location)).thenReturn(location);
		assertEquals(location, service.updateLocation(location));
	}
}
