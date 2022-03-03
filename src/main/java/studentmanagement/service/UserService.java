package studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import studentmanagement.dao.UserRepository;
import studentmanagement.dto.ClassDTO;
import studentmanagement.dto.StudentDTO;
import studentmanagement.dto.UserDTO;
import studentmanagement.model.SubreportBean;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	BCryptPasswordEncoder encode;
	
	@Autowired
	private StudentService sService;
	
	@Autowired
	private ClassService cService;

	public void deleteById(String id) {
		userRepo.deleteById(id);
	}

	public List<UserDTO> findAll() {
		return userRepo.findAll();
	}
	
	public UserDTO save(UserDTO dto) {
		String encodePwd = encode.encode(dto.getPassword());
		dto.setPassword(encodePwd);
		dto.setRole("ROLE_USER");
		dto.setEnable(true);
		return userRepo.save(dto);
	}

	public List<UserDTO> findByIdOrName(String id, String name) {
		return userRepo.findByIdOrName(id, name);
	}
	
	public SubreportBean findSub() {
		SubreportBean nBean = new SubreportBean();
		
		List<StudentDTO> studentList = sService.findAll();
		List<UserDTO> userList = userRepo.findAll();
		List<ClassDTO> classList = cService.findAll();
		
		nBean.setsList(studentList);
		nBean.setcList(classList);
		nBean.setuList(userList);
		
		
		return nBean;
		
	}
}
