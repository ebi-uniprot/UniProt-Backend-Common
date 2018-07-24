package uk.ac.ebi.uniprot.cv.go;

import java.util.List;

public interface GoRelation {
	List<GoTerm> getParents();
	List<GoTerm> getPartOfParents();
}
