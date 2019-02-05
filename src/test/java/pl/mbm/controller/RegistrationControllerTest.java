package pl.mbm.controller;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pl.mbm.dao.util.DaoTestUtils.USER;
import static pl.mbm.dao.util.DaoTestUtils.USER_EMAIL;
import static pl.mbm.dao.util.DaoTestUtils.USER_NAME;
import static pl.mbm.service.util.ServiceTestUtils.getUserJTable;
import static pl.mbm.service.util.ServiceTestUtils.getUserRegistrationForm;
import static pl.mbm.service.util.ServiceTestUtils.getUserRegistrationFormWithWrongFields;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.mbm.configuration.RegistrationControllerTestContext;
import pl.mbm.configuration.WebAppContext;
import pl.mbm.dao.UserDao;
import pl.mbm.service.UserService;
import pl.mbm.util.WebTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={WebAppContext.class,
		RegistrationControllerTestContext.class})
@WebAppConfiguration
public class RegistrationControllerTest {
	
	private MockMvc mockMvc;
	@Autowired
	private UserService userServiceMock;
	@Autowired
	private WebApplicationContext webApplicationContext;
	@Autowired
	private UserDao userDaoMock;
	
	@Before
	public void setUp(){
		Mockito.reset(userServiceMock,userDaoMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void registerTest() throws Exception {
		when(userServiceMock.registerUser(getUserRegistrationForm()))
			.thenReturn(getUserJTable());
		
		shouldBeValid("/register");
		
		verify(userServiceMock, times(1)).registerUser(getUserRegistrationForm());
	}
	
	@Test
	public void registerTest_ValidationShouldFail() throws Exception {
		basicConstrains_ShouldFail("/register");
	}
	
	@Test
	public void registerTest_ValidationShouldFail_NameAndEmailNotAvailable() throws Exception {
		customConstrains_ShouldFail("/register");
	}
	
	@Test
	public void validateRegisterFormTest() throws IOException, Exception{
		shouldBeValid("/validate/registerForm");
	}
	
	@Test
	public void validateRegisterFormTest_BasicConstrains_ShouldFail() throws IOException, Exception{
		basicConstrains_ShouldFail("/validate/registerForm");
	}
	
	@Test
	public void validateRegisterFormTest_CustomConstrains_ShouldFail() throws IOException, Exception{
		customConstrains_ShouldFail("/validate/registerForm");
	}
	
	
	private void shouldBeValid(String path) throws IOException, Exception{
		when(userDaoMock.findByEmail(USER_EMAIL)).thenReturn(null);
		when(userDaoMock.findByName(USER_NAME)).thenReturn(null);
		// TODO zamiast mockowac dao, lepiej by bylo mockowac validatory, ale nie udalo mi sie tego zrobic
//		doNothing().when(emailNotExistsInDatabaseValidatorMock).initialize( any(EmailNotExistsInDatabase.class));
//		doNothing().when(nameNotExistsInDatabaseValidatorMock).initialize( any(NameNotExistsInDatabase.class));
//		when(emailNotExistsInDatabaseValidatorMock.isValid(any(), any(ConstraintValidatorContext.class))).thenReturn(true);
//		when(nameNotExistsInDatabaseValidatorMock.isValid(any(), any(ConstraintValidatorContext.class))).thenReturn(true);

		mockMvc.perform(post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(WebTestUtils.convertObjectToJsonBytes(getUserRegistrationForm())))
			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(WebTestUtils.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.code", is(200)));
	}
	
	private void basicConstrains_ShouldFail(String path) throws IOException, Exception{
		when(userDaoMock.findByEmail(USER_EMAIL)).thenReturn(USER);
		when(userDaoMock.findByName(USER_NAME)).thenReturn(USER);
		
		mockMvc.perform(post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(WebTestUtils.convertObjectToJsonBytes(getUserRegistrationFormWithWrongFields())))
			.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(content().contentType(WebTestUtils.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.globalErrors", hasSize(1)))
				.andExpect(jsonPath("$.globalErrors[*].field", contains("userRegistrationForm")))
				.andExpect(jsonPath("$.globalErrors[*].message", contains("Passwords are not the same")))
				.andExpect(jsonPath("$.fieldErrors", hasSize(3)))
				.andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("email","name","password")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder("Wrong format of name",
                													"Wrong format of password",
                													"Wrong format of email")));
		
		verifyNoMoreInteractions(userServiceMock);
	}
	
	private void customConstrains_ShouldFail(String path) throws IOException, Exception{
		when(userDaoMock.findByEmail(USER_EMAIL)).thenReturn(USER);
		when(userDaoMock.findByName(USER_NAME)).thenReturn(USER);
		
		mockMvc.perform(post(path)
				.contentType(MediaType.APPLICATION_JSON)
				.content(WebTestUtils.convertObjectToJsonBytes(getUserRegistrationForm())))
			.andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.fieldErrors", hasSize(2)))
				.andExpect(jsonPath("$.fieldErrors[*].field", containsInAnyOrder("email","name")))
                .andExpect(jsonPath("$.fieldErrors[*].message", containsInAnyOrder("This email is not available", 
                													"This name is not available")));
		
		verifyNoMoreInteractions(userServiceMock);
	}
}
