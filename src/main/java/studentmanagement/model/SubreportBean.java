package studentmanagement.model;

import java.util.List;

import studentmanagement.dto.ClassDTO;
import studentmanagement.dto.StudentDTO;
import studentmanagement.dto.UserDTO;

public class SubreportBean {

	private List<StudentDTO> sList;
	private List<UserDTO> uList;
	private List<ClassDTO> cList;

	public List<StudentDTO> getsList() {
		return sList;
	}

	public void setsList(List<StudentDTO> sList) {
		this.sList = sList;
	}

	public List<UserDTO> getuList() {
		return uList;	}

	public void setuList(List<UserDTO> uList) {
		this.uList = uList;
	}

	public List<ClassDTO> getcList() {
		return cList;
	}

	public void setcList(List<ClassDTO> cList) {
		this.cList = cList;
	}

}
