package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class DemoApplicationTests {

	@Test
	public void contextLoads() {
//		BigDecimal bigDecimal = new BigDecimal(String.valueOf((Double)null));
//		System.out.println(bigDecimal);
		System.out.println(0b11000000 &
				0b10011111);
	}

	public static void getFieldListFromJsonStr() {
		String fieldName = "value";
		String reg ="[{\"value\":6},{\"value\":7}]";
		String regex = "(?<=(\"" + fieldName + "\":))([7-9]*)";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(reg);
		while (matcher.find()) {
			String str = matcher.group().trim();
			if (str != null && !str.isEmpty()) {
				System.out.println(str);
			}
		}
	}

	public static void main(String[] args) {
		getFieldListFromJsonStr();
	}

}
