package pl.mbm.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import pl.mbm.service.validator.RegistrationValidator;
import pl.mbm.service.validator.impl.RegistrationValidatorImpl;

public class RegistrationValidatorTest {

	private RegistrationValidator validator = new RegistrationValidatorImpl();

	@Test
	public void nameFormatCorrectTest() {
		assertTrue("matrom shoud be correct name",
				validator.nameFormatCorrect("matrom"));
		assertFalse("should be too short", validator.nameFormatCorrect("matro"));
		assertFalse("should be too long",
				validator.nameFormatCorrect("matrommatrommatrommatrom"));
		assertFalse("cannot start with number",
				validator.nameFormatCorrect("0matro"));
		assertFalse("cannot end with .", validator.nameFormatCorrect("matro."));
		assertFalse("cannot end with _", validator.nameFormatCorrect("matro_"));
	}

	@Test
	public void emailFormatCorrectTest() {
		assertTrue("should be correct",
				validator.emailFormatCorrect("matrom@matrom.pl"));
	}

	@Test
	public void passwordsMismatchTest() {
		assertTrue("should return true",
				validator.passwordsMismatch("password", "password2"));
	}
}
