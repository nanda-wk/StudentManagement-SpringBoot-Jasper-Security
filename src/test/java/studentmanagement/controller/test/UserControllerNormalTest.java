package studentmanagement.controller.test;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

import studentmanagement.model.UserBean;
import studentmanagement.service.UserService;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserControllerNormalTest {

	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	UserService userService;
	
	@BeforeEach
	public void setup() {
		mockMvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity()) 
				.build();
	}
	
	@Disabled
	@Test
	@WithMockUser
	public void homeTest() throws Exception {
		this.mockMvc.perform(get("/user/home"))
		.andExpect(status().isOk())
		.andExpect(view().name("M00001"));
	}
	
	//user add validate test
	@Disabled
	@Test
	@WithMockUser
	public void userAddValidateTest() throws Exception {
		this.mockMvc.perform(get("/user/userAdd"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"));
	}
	
	@Disabled
	@Test
	@WithMockUser
	public void userAddGetTest() throws Exception {
		this.mockMvc.perform(get("/user/userAdd"))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"))
		.andExpect(model().attributeExists("bean"));
	}
	
	@Test
	@WithMockUser
	public void userAddPostTest() throws Exception {
		UserBean bean = createUser();
		this.mockMvc.perform(post("/user/userAdd")
				.flashAttr("bean", bean))
		.andExpect(status().isOk())
		.andExpect(view().name("USR002"));
	}
	
	private UserBean createUser() {
		UserBean bean = new UserBean();
		bean.setId("U999");
		bean.setName("User Test");
		bean.setPassword("123");
		bean.setConPwd("123");
		return bean;
	}
}
