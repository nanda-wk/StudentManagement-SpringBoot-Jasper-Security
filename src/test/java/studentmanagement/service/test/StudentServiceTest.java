package studentmanagement.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import studentmanagement.dto.StudentDTO;
import studentmanagement.service.StudentService;

public class StudentServiceTest {

	
	@Mock
	private StudentService studentService;

	@BeforeEach
	void setUp() {
		studentService = Mockito.mock(StudentService.class);
	}

	@Test
	void saveTest() {
		StudentDTO expected = createStudent();
		Mockito.when(studentService.save(expected)).thenReturn(expected);
		StudentDTO actual = studentService.save(expected);
		Assertions.assertEquals(expected, actual);
	}

	@Test
	void findAllTest() {
		List<StudentDTO> expected = new ArrayList<StudentDTO>();
		Mockito.when(studentService.findAll()).thenReturn(expected);
		List<StudentDTO> actual = studentService.findAll();
		Assertions.assertEquals(expected.size(), actual.size());
	}
	
	@Test
	void findByStudentIdOrStudentNameOrClassNameOrRegisterDateOrStatusTest() {
		List<StudentDTO> expected = studentService.findByStudentIdOrStudentNameOrClassName("S999", "Student Test Name", "Class Test Name");
		Mockito.when(studentService.findByStudentIdOrStudentNameOrClassName("S999", "Student Test Name", "Class Test Name")).thenReturn(expected);
		List<StudentDTO> actual = studentService.findByStudentIdOrStudentNameOrClassName("S999", "Student Test Name", "Class Test Name");
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void deleteByIdTest() {
		StudentDTO expected = new StudentDTO();
		expected.setStudentId("S979");
		studentService.deleteById(expected.getStudentId());
		Mockito.verify(studentService).deleteById(expected.getStudentId());
	}
	
	private StudentDTO createStudent() {
		StudentDTO dto = new StudentDTO();
		dto.setStudentId("S999");
		dto.setStudentName("StudentName");
		dto.setClassName("ClassName");
		dto.setRegisterDate("2022-2-23");
		dto.setStatus("Passed");
		return dto;
	}
}
