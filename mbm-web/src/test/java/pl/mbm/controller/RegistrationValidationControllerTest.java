package pl.mbm.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import pl.mbm.configuration.TestRegistrationValidationControllerConfig;
import pl.mbm.configuration.WebAppContext;
import pl.mbm.util.TestUtil;
import pl.mbm.validator.RegistrationValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
		TestRegistrationValidationControllerConfig.class, WebAppContext.class })
@WebAppConfiguration
public class RegistrationValidationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private RegistrationValidator registrtionValidatorMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(registrtionValidatorMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void injectionOfRegistrationValidator_ShoulReturnNotNull() {
		assertNotNull(registrtionValidatorMock);
	}

	@Test
	public void validateName_ShouldBeValid() throws Exception {

		when(registrtionValidatorMock.nameFormatCorrect("user00")).thenReturn(
				true);
		when(registrtionValidatorMock.nameExists("user00")).thenReturn(false);
		/* @formatter:off */
		mockMvc.perform(
				post("/validate/registerForm/name")
						.contentType(MediaType.APPLICATION_JSON)
						.content(TestUtil.getUserRegistrationFormJsonBytes()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect((jsonPath("$.message", is("Name format is correct"))));

		verify(registrtionValidatorMock, times(1)).nameFormatCorrect("user00");
		verify(registrtionValidatorMock, times(1)).nameExists("user00");
		verifyNoMoreInteractions(registrtionValidatorMock);
		/* @formatter:on */
	}

}
