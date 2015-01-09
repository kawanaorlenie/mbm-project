package pl.mbm.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
import pl.mbm.service.ActivationService;
import pl.mbm.service.util.ServiceTestUtils;

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
		when(activationServiceMock.activateUser(ServiceTestUtils.USER_NAME,ServiceTestUtils.USER_ACTIVATION_CODE))
			.thenReturn(ServiceTestUtils.getUserJTable());

		mockMvc.perform(get("/activation").param("name", ServiceTestUtils.USER_NAME).param("code", ServiceTestUtils.USER_ACTIVATION_CODE))
			.andDo(print())
				.andExpect(status().isOk())				
				.andExpect(view().name("login"))
                .andExpect(model().attribute("activated", is(true)));
				
		
		verify(activationServiceMock, times(1)).activateUser(ServiceTestUtils.USER_NAME,ServiceTestUtils.USER_ACTIVATION_CODE);
	}
	// @formatter:on
}
