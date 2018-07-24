package uk.ac.ebi.uniprot.cv.go;

import java.util.List;

public interface GoRelationRepo {
	List<GoTerm> getParents(String goId);
	List<GoTerm> getPartOfParents(String goId);
}
