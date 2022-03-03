package studentmanagement.controller.test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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

import studentmanagement.model.ClassBean;
import studentmanagement.service.ClassService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ClassControllerTest {

	@Autowired
	private WebApplicationContext context;

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ClassService classService;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).apply(springSecurity()).build();
	}

	// class register get test
	@Test
	@WithMockUser
	public void classRegisterGetTest() throws Exception {
		this.mockMvc.perform(get("/user/classRegister"))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD003"))
		.andExpect(model().attributeExists("bean"));
	}
	
	//class register validate test
	@Test
	@WithMockUser
	public void classRegisterValidateTest() throws Exception {
		this.mockMvc.perform(post("/user/classRegister"))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD003"));
	}
	
	//class register post test
	@Test
	@WithMockUser
	public void classRegisterPostTest() throws Exception {
		ClassBean bean = createClass();
		this.mockMvc.perform(post("/user/classRegister")
				.flashAttr("bean", bean))
		.andExpect(status().isOk())
		.andExpect(view().name("BUD003"));
	}
	
	private ClassBean createClass() {
		ClassBean bean = new ClassBean();
		bean.setId("C999");
		bean.setName("Class Test Name");
		return bean;
	}
}
