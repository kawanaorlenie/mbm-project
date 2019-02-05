package pl.mbm.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

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

import pl.mbm.configuration.TestUserManagementControllerConfig;
import pl.mbm.configuration.WebAppContext;
import pl.mbm.service.UserService;
import pl.mbm.service.dto.UserJTable;
import pl.mbm.util.WebTestUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestUserManagementControllerConfig.class,
		WebAppContext.class })
@WebAppConfiguration
public class UserManagementControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private UserService userServiceMock;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		Mockito.reset(userServiceMock);

		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void listUsers_UsersFound_ShouldReturnFoundUserEntries()
			throws Exception {

		UserJTable first = new UserJTable();
		first.setName("first");

		UserJTable second = new UserJTable();
		second.setName("second");

		when(userServiceMock.listUsers()).thenReturn(
				Arrays.asList(first, second));

		// TODO dlaczego status jest OK jak nie ma mapowania do /user/lis
		mockMvc.perform(
				get("/user/list").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content().contentType(WebTestUtils.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)));

		verify(userServiceMock, times(1)).listUsers();
		verifyNoMoreInteractions(userServiceMock);
	}

}
