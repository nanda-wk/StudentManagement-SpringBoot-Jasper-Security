package studentmanagement.controller.test;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import studentmanagement.model.SearchBean;
import studentmanagement.model.StudentBean;
import studentmanagement.service.StudentService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class StudentControllerNormalTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	StudentService studentService;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity()) 
				.build();
	}
	
	//student search setup
	@Test
	@WithMockUser
	public void studentSearchTest() throws Exception {
		this.mockMvc.perform(get("/user/studentSearch"))
				//.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD001"))
		.andExpect(model().attributeExists("bean"));
	}
	
	//student search result test
	@Test
	public void studentSearchResultTest() throws Exception {
		SearchBean bean = createSerach();
		this.mockMvc.perform(get("/user/studentResult")
				.with(user("admin").password("pass").roles("USER"))
				.flashAttr("bean", bean))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD001"))
		.andExpect(model().attributeExists("bean"));
	}
	
	//student register get test
	@Test
	public void studentRegisterGetTest() throws Exception {
		this.mockMvc.perform(get("/user/studentRegister")
				.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002"))
		.andExpect(model().attributeExists("bean"));
	}
	
	//student register validate test
	@Test
	public void studentRegisterValidateTest() throws Exception {
		this.mockMvc.perform(post("/user/studentRegister")
				.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002"));
	}
	
	//student register post test
	@Test
	public void studentRegisterPostTest() throws Exception {
		StudentBean bean = createStudent();
		this.mockMvc.perform(post("/user/studentRegister")
				.with(user("admin").password("pass").roles("USER"))
				.flashAttr("bean", bean))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002"));
	}
	
	//student delete test
	@Test
	public void studentDeleteTest() throws Exception {
		StudentBean bean = createStudent();
		this.mockMvc.perform(get("/user/studentDelete")
				.param("id", bean.getStudentId())
				.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().is(302))
		.andExpect(redirectedUrl("/user/studentSearch?success=Delete+successful"));
	}
	
	//student update get test
	@Test
	public void studentUpdateGetTest() throws Exception {
		StudentBean bean = createStudent();
		this.mockMvc.perform(get("/user/studentUpdate")
				.param("id", bean.getStudentId())
				.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002-01"))
		.andExpect(model().attributeExists("bean"));
	}

	
	//student update validate test
	@Test
	public void studentUpdateValidateTest() throws Exception {
		this.mockMvc.perform(post("/user/studentUpdate")
				.with(user("admin").password("pass").roles("USER")))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002-01"));
	}
	
	//student update post test
	@Test
	public void studentUpdatePostTest() throws Exception {
		StudentBean bean = createStudent();
		bean.setStatus("Failed");
		this.mockMvc.perform(post("/user/studentUpdate")
				.with(user("admin").password("pass").roles("USER"))
				.flashAttr("bean", bean))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD002-01"));
	}
	
	private StudentBean createStudent() {
		StudentBean bean = new StudentBean();
		bean.setStudentId("S999");
		bean.setStudentName("StudentName");
		bean.setYear("2022");
		bean.setMonth("12");
		bean.setDay("27");
		bean.setClassName("ClassName");
		bean.setStatus("Passed");
		return bean;
	}
	
	private SearchBean createSerach() {
		SearchBean bean = new SearchBean();
		bean.setStudentId("");
		bean.setStudentName("");
		bean.setClassName("");
		return bean;
	}
}
