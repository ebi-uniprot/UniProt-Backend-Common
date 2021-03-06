package uk.ac.ebi.uniprot.cv.go.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class GoRelationFileReader {
	private static final String COMMENT_PREFIX = "!";
	private static final String SEPARATOR = "\t";
	private static final String PART_OF = "part_of";
	private static final String IS_A = "is_a";
	private Map<String, List<String >> isAMap = new HashMap<>();
	private Map<String, List<String >> isPartMap = new HashMap<>();
	private final String goRelationFPath;
	
	private static final String FILENAME ="GO.relations";
	
	public GoRelationFileReader(String goRelationFPath) {
		this.goRelationFPath = goRelationFPath;
	}
	public Map<String, List<String >> getIsAMap() {
		return isAMap;
	}
	public Map<String, List<String >> getIsPartMap() {
		return isPartMap;
	}
	
	public void read() {
		String filename = goRelationFPath +File.separator + FILENAME;
		try (Stream<String> stream = Files.lines(Paths.get(filename))) {	
			stream.forEach(this::readLine);
		} catch (IOException e) {
			throw new RuntimeException (e);
		}
	}
	private void readLine(String line) {
		if(line.startsWith(COMMENT_PREFIX))
			return;
		String[] tokens = line.split(SEPARATOR);
		if(tokens.length==3)  {
		
			if(tokens[1].equals(IS_A)) {
				add2Map(isAMap, tokens[0], tokens[2]);
			}else if(tokens[1].equals(PART_OF)) {
				add2Map(isPartMap, tokens[0], tokens[2]);
			}
		}
	}
	private void add2Map(Map<String, List<String >>  map, String key, String value) {
		List<String> values = map.get(key);
		if(values ==null) {
			values= new ArrayList<>();
			map.put(key, values);
		}
		values.add(value);
	}
}
