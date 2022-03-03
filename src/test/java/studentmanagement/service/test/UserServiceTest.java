package studentmanagement.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import studentmanagement.dto.UserDTO;
import studentmanagement.service.UserService;

public class UserServiceTest {

	@Mock
	private UserService uService;
	
	
	@BeforeEach
	void setUp() {
		uService = Mockito.mock(UserService.class);
	}
	
	@Test
	void saveTest() {
		UserDTO expected = createUser();
		Mockito.when(uService.save(expected)).thenReturn(expected);
		UserDTO actula = uService.save(expected);
		Assertions.assertEquals(expected, actula);
		
	}
	
	@Test
	void findAllTest() {
		List<UserDTO> expected = new ArrayList<UserDTO>();
		Mockito.when(uService.findAll()).thenReturn(expected);
		List<UserDTO> actual = uService.findAll();
		Assertions.assertEquals(expected.size(), actual.size());
	}
	
	@Test
	void findByIdOrNameTest() {
		List<UserDTO> expected = new ArrayList<UserDTO>();
		Mockito.when(uService.findByIdOrName("U999", "User Name Test")).thenReturn(expected);
		List<UserDTO> actual = uService.findByIdOrName("U999", "User Name Test");
		Assertions.assertEquals(expected, actual);
	}
	
	@Test
	void deleteByIdTest() {
		UserDTO expected = new UserDTO();
		expected.setId("U999");
		uService.deleteById(expected.getId());
		Mockito.verify(uService).deleteById(expected.getId());
	}
	
	
	private UserDTO createUser() {
		UserDTO dto = new UserDTO();
		dto.setId("U999");
		dto.setName("UserName");
		dto.setPassword("Password");
		dto.setEnable(true);
		dto.setRole("ROLE_USER");
		dto.setImg("/images/path");
		return dto;
	}
}
