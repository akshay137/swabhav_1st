package com.techlabs.automobile.singleton;

import java.util.*;

public interface IConfigLoader {
	Map<String, String> loadConfig(String path);
}
