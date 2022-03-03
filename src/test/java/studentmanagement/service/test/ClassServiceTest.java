package studentmanagement.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import studentmanagement.dto.ClassDTO;
import studentmanagement.service.ClassService;

public class ClassServiceTest {

	@Mock
	private ClassService cService;
	
	@BeforeEach
	void setUp() {
		cService = Mockito.mock(ClassService.class);
	}
	
	@Test
	void saveTest() {
		ClassDTO expected = createClass();
		Mockito.when(cService.save(expected)).thenReturn(expected);
		ClassDTO actual = cService.save(expected);
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void findAllTest() {
		List<ClassDTO> expected = new ArrayList<ClassDTO>();
		Mockito.when(cService.findAll()).thenReturn(expected);
		List<ClassDTO> actual = cService.findAll();
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void findByIdOrNameTest() {
		List<ClassDTO> expected = new ArrayList<ClassDTO>();
		Mockito.when(cService.findByIdOrName("C999", "Class Name Test")).thenReturn(expected);
		List<ClassDTO> actual = cService.findByIdOrName("C999", "Class Name Test");
		Assertions.assertEquals(expected, actual);
	}
	
	private ClassDTO createClass() {
		ClassDTO dto = new ClassDTO();
		dto.setId("C999");
		dto.setName("Class Name Test");
		return dto;
	}
}
