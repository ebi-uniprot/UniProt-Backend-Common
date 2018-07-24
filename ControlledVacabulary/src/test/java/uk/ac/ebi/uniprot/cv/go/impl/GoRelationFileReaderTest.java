package uk.ac.ebi.uniprot.cv.go.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

class GoRelationFileReaderTest {

	@Test
	void testRead() {
		   URL url = ClassLoader.getSystemClassLoader().getResource("goterm");
		  String filename = url.getFile();
		GoRelationFileReader reader = new GoRelationFileReader(filename);
		reader.read();
		 Map<String, List<String >> isAMap = reader.getIsAMap();
		 assertFalse(isAMap.isEmpty());
		 Map<String, List<String >> isPartMap = reader.getIsPartMap();
		 assertFalse(isPartMap.isEmpty());
	}

}
