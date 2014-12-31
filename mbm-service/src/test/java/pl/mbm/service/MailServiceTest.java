package pl.mbm.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.mbm.configuration.MailBeans;
import pl.mbm.configuration.MailServiceTestContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MailServiceTestContext.class, MailBeans.class })
public class MailServiceTest {

	@Autowired
	private MailService mailService;

	@Test
	public void autowireTest() {
		assertNotNull(mailService);
	}

	@Test
	public void sendActivationMailTest() {
		String link = "http://matrom.pl";
		// mailService.sendActivationMail(TestUtil.getUserToSendMail(), link);
	}
}
