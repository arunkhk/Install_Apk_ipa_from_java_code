package servlet.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * System.out.println("Arun +"); Integer[] arr = new Integer[1000]; for (int i =
		 * 0; i < arr.length; i++) { arr[i] = i; }
		 * Collections.shuffle(Arrays.asList(arr));
		 * System.out.println("Arun +"+Arrays.toString(arr));
		 */

		  ArrayList<Integer> list = new ArrayList<Integer>();
	        for (int i = 1; i <= 6; i++) {
	            list.add(new Integer(i));
	        }
	        Collections.shuffle(list);
	        for (int i = 0; i < 3; i++) {
	            System.out.println(list.get(i));
	        }
	    
	}

 static int[] sampleRandomNumbersWithoutRepetition(int start, int end, int count) {
	    Random rng = new Random();

	    int[] result = new int[count];
	    int cur = 0;
	    int remaining = end - start;
	    for (int i = start; i < end && count > 0; i++) {
	        double probability = rng.nextDouble();
	        if (probability < ((double) count) / (double) remaining) {
	            count--;
	            result[cur++] = i;
	        }
	        remaining--;
	    }
	    return result;
	}

}
