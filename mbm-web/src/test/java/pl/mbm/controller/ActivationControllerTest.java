package pl.mbm.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import pl.mbm.configuration.ActivationControllerTestContext;
import pl.mbm.configuration.WebAppContext;
import pl.mbm.dao.util.TestUtils;
import pl.mbm.service.ActivationService;
import pl.mbm.util.TestUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppContext.class,
		ActivationControllerTestContext.class })
@WebAppConfiguration
public class ActivationControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private ActivationService activationServiceMock;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(activationServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	// @formatter:off
	@Test
	public void activateUserTest() throws Exception {
		when(activationServiceMock.activateUser(TestUtils.getActivationCode()))
			.thenReturn(TestUtils.USER);

		mockMvc.perform(get("/activation").param("user.name", TestUtils.USER_NAME).param("code", TestUtils.CODE))
			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
				.andExpect((jsonPath("$.message", is("Account has been activated"))));
		
		verify(activationServiceMock, times(1)).activateUser(TestUtils.getActivationCode());
	}
	// @formatter:on
}
