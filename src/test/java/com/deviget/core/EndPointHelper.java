package com.deviget.core;

import io.github.cdimascio.dotenv.Dotenv;

public class EndPointHelper {
	public static final String BASE_URL = Dotenv.load().get(CoreConstants.BASE_URL);

}
