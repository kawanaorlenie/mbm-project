package pl.mbm.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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

import pl.mbm.configuration.ResetPasswordControllerTestContext;
import pl.mbm.configuration.WebAppContext;
import pl.mbm.dao.util.DaoTestUtils;
import pl.mbm.service.ResetPasswordService;
import pl.mbm.util.WebTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { WebAppContext.class,
		ResetPasswordControllerTestContext.class })
@WebAppConfiguration
public class ResetPasswordControllerTest {

	private MockMvc mockMvc;
	@Autowired
	private ResetPasswordService resetPasswordServiceMock;
	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(resetPasswordServiceMock);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	// @formatter:off
	@Test
	public void beginProcedureTest() throws Exception {
		when(resetPasswordServiceMock.beginProcedure(DaoTestUtils.USER_EMAIL))
			.thenReturn(true);

		mockMvc.perform(post("/forgot")
				.param("email", DaoTestUtils.USER_EMAIL))
			.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType(WebTestUtils.APPLICATION_JSON_UTF8))
				.andExpect((jsonPath("$.message", is("Procedure has been initialized successfully"))));				
				
		
		verify(resetPasswordServiceMock, times(1)).beginProcedure(DaoTestUtils.USER_EMAIL);
	}
	// @formatter:on

}
