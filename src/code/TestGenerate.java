package code;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;




public class TestGenerate {

	public static void main(String[] args) throws Exception {
		Map map = new Map();

		map.generateMap("code/Roma.txt");
		System.out.println(map);
	
	}
	 
}
