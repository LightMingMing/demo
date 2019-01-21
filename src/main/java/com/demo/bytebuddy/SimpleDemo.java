package com.demo.bytebuddy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

public class SimpleDemo {

	public static void main(String[] args) throws Exception {

	}

	public static void withToStringMethod() throws InstantiationException, IllegalAccessException {
		Class<?> dynamicType = new ByteBuddy().subclass(Object.class).method(ElementMatchers.named("toString"))
				.intercept(FixedValue.value("Hello, world!")).make().load(SimpleDemo.class.getClassLoader())
				.getLoaded();
		System.out.println(dynamicType.getName());
		System.out.println(dynamicType.newInstance().toString());
	}

	public static void writeGenerateCodeToFile() throws IOException {
		DynamicType.Unloaded<Object> uploaded = new ByteBuddy(ClassFileVersion.JAVA_V8).subclass(Object.class).make();
		DynamicType.Loaded<Object> loaded = uploaded.load(SimpleDemo.class.getClassLoader());
		Files.write(Paths.get("D:/", "DynamicType.class"), loaded.getBytes(), StandardOpenOption.CREATE);
	}

}
