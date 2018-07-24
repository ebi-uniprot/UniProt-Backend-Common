package uk.ac.ebi.uniprot.cv.go.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URL;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.ac.ebi.uniprot.cv.go.GoTerm;

class GoRelationFileRepoTest {
	private static GoRelationFileRepo goRelation;
	@BeforeAll
	 static void init() {
		  URL url = ClassLoader.getSystemClassLoader().getResource("goterm");
		GoRelationFileReader relationReader = new GoRelationFileReader(url.getFile());
		GoTermFileReader termReader = new GoTermFileReader(url.getFile());
		goRelation = GoRelationFileRepo.create(relationReader, termReader);
	}
	@Test
	void hasIsAOnly() {
		String goId = "GO:2001268";
		List<GoTerm> isAs = goRelation.getParents(goId);
		assertFalse(isAs.isEmpty());
		List<GoTerm> partOfs = goRelation.getPartOfParents(goId);
		assertTrue(partOfs.isEmpty());
	}

	@Test
	void hasBothIsAAndPartOf() {
		String goId = "GO:2001197";
		List<GoTerm> isAs = goRelation.getParents(goId);
		assertFalse(isAs.isEmpty());
		assertEquals(2, isAs.size());
		assertEquals("GO:0010927", isAs.get(0).getId());
		assertEquals("cellular component assembly involved in morphogenesis", isAs.get(0).getName());
		
		assertEquals("GO:0070831", isAs.get(1).getId());
		assertEquals("basement membrane assembly", isAs.get(1).getName());
		List<GoTerm> partOfs = goRelation.getPartOfParents(goId);
		
		assertFalse(partOfs.isEmpty());
		assertEquals(1, partOfs.size());
		assertEquals("GO:0010172", partOfs.get(0).getId());
		assertEquals("embryonic body morphogenesis", partOfs.get(0).getName());
		
		//GO:2001197	is_a	GO:0010927
	//	GO:2001197	is_a	GO:0070831
	//	GO:2001197	part_of	GO:0010172
	}

}
