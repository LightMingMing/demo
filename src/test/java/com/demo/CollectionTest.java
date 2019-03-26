package com.demo;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class CollectionTest {

	@Test(expected = ConcurrentModificationException.class)
	public void testRemoveWithForEach() {
		Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3", "4"));
		for (String str : set) {
			System.out.println("forEach: " + str);
			set.remove(str);
		}
		assertTrue(set.size() == 0);
	}

	@Test
	public void testRemoveWithIterator() {
		Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3", "4"));
		Iterator<String> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println("iterator: " + iterator.next());
			iterator.remove();
		}
		assertTrue(set.size() == 0);
	}
}