package com.iit.imprimerie.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iit.imprimerie.dao.DocumentDao;
import com.iit.imprimerie.entities.Document;
import com.iit.imprimerie.entities_response.DocumentResponse;
import com.iit.imprimerie.entities_response.EnseignantResponse;
import com.iit.imprimerie.entities_response.MatiereResponse;
import com.iit.imprimerie.exception.ConflitException;
import com.iit.imprimerie.exception.NoException;
import com.iit.imprimerie.exception.NotFoundException;
import com.iit.imprimerie.proxies.MicroServiceEnseignantProxy;

@Service
public class DocumentService {
	@Autowired
	DocumentDao docs;
	@Autowired
	MicroServiceEnseignantProxy me;

	public void AjouterDocument(Document m) {
		Document m1 = getDocumentByPath(m.getPath());
		EnseignantResponse e = getEnsById(m.getEns());
		MatiereResponse mat = getMatById(m.getMat());
		m.setEns(e.getId());
		m.setMat(mat.getId_mat());
		docs.saveAndFlush(m);
		throw new NoException("l'objet est ajouter avec sucée");

	}

	public void supprimerDocument(int id) {

		try {
			docs.deleteById(id);
		} catch (Exception e) {
			// TODO: handle exception
			throw new NotFoundException("no recod with the id " + id + " was found in daabase ");

		}
	}

	public void modifierDocument(Document m) {
		Document m1 = getDocumentByPath(m.getPath());
		EnseignantResponse e = getEnsById(m.getEns());
		MatiereResponse mat = getMatById(m.getMat());
		m.setEns(e.getId());
		m.setMat(mat.getId_mat());
		docs.saveAndFlush(m);

		throw new NoException("l'objet est Modifier avec sucée");
	}

	public DocumentResponse getDocument(int id) {
		Document m = getDocumentById(id);
		DocumentResponse mm = new DocumentResponse(m);
		mm.setEns(getEnsById(m.getEns()));
		mm.setMat(getMatById(m.getMat()));
		return mm;
	}

	public List<Document> getAllDocument() {
		return docs.findAll();
	}

	public List<Document> getDocumentByEnseignant(int id) {
		EnseignantResponse e = getEnsById(id);
		List<Document> ld = docs.getDocymentByEnsignant(id);
		if (ld != null) {
			return ld;
		} else {
			throw new NotFoundException("no recod with the id enseignant " + id + " was found in daabase ");
		}

	}

	public List<Document> getDocumentByMatiere(int id) {
		MatiereResponse m = getMatById(id);
		List<Document> ld = docs.getDocymentByMatiere(id);
		if (ld != null)
			return ld;
		else
			throw new NotFoundException("no recod with the id matiere " + id + " was found in daabase ");

	}

	public EnseignantResponse getEnsById(int id) {
		try {
			EnseignantResponse e = me.getEnsById(id);

			return e;
		} catch (Exception e) {
			throw new NotFoundException("l'objet enseignant avec id =" + id + " not found");
		}
	}

	public MatiereResponse getMatById(int id) {

		try {
			MatiereResponse m = me.getMatById(id);
			return m;
		} catch (Exception e) {
			throw new NotFoundException("l'objet Matiere avec id =" + id + " not found");
		}
	}

	public Document getDocumentById(int id) {
		try {
			Document m = docs.findById(id).get();

			return m;
		} catch (Exception e) {
			throw new NotFoundException("l'objet document avec id =" + id + " not found");
		}
	}

	public Document getDocumentByPath(String path) {

		Document m = docs.findByPath(path);
		if (m != null) {
			throw new ConflitException("l'objet document avec le path =" + path + " existe deja dans le base donnée");
		} else
			return m;

	}

}
