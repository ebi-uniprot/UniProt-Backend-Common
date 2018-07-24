package uk.ac.ebi.uniprot.cv.go.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.Map;

import org.junit.jupiter.api.Test;

import uk.ac.ebi.uniprot.cv.go.GoTerm;

class GoTermFileReaderTest {

	@Test
	void testRead() {
	//	String filename = "src/test/resources/goterm";
		  URL url = ClassLoader.getSystemClassLoader().getResource("goterm");
		GoTermFileReader reader = new GoTermFileReader(url.getFile());
		Map<String, GoTerm> goterms = reader.read();
		assertFalse(goterms.isEmpty());
	
	}

}
