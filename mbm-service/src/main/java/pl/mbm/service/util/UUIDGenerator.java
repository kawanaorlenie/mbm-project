package pl.mbm.service.util;

import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class UUIDGenerator {

	public String generate() {
		return UUID.randomUUID().toString();
	}
}
