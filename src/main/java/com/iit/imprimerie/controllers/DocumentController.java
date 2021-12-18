package com.iit.imprimerie.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.iit.imprimerie.entities.Document;
import com.iit.imprimerie.entities_response.DocumentResponse;
import com.iit.imprimerie.services.DocumentService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class DocumentController {
	@Autowired
	DocumentService docs;
	@ApiOperation(value = "Ajouter document", notes = "cette methode permet au enseignant d'ajouter un document ", response = Document.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet document est ajouter"),
			@ApiResponse(code = 400, message = "l'objet document n'est pas valide") })
	@PostMapping("/Document")
	public void AjouterDocument(@RequestBody Document m) {
		docs.AjouterDocument(m);
	}
	@ApiOperation(value = "supprimer un document", notes = "cette methode permet de supprimer un document par son id qui existe dans le base de donnée", response = Document.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet est supprimer"),
			@ApiResponse(code = 204, message = "l'objet est supprimer avec sucée"),
			@ApiResponse(code = 401, message = "requée necessite le client doit identifier"),
			@ApiResponse(code = 403, message = "le serveur n'est pas autorisé pour cette requéte")
	})
	@DeleteMapping("/Document/{id}")
	public void supprimerDocument(@PathVariable int id) {
		docs.supprimerDocument(id);
	}
	@ApiOperation(value = "Modifier document", notes = "cette methode permet de modifier un document ", response = Document.class)
	 @ApiResponses(value = { @ApiResponse(code = 200, message = "l'objet est modifer "),
	 @ApiResponse(code = 400, message = "l'objet  n'est pas valide") })
	@PutMapping("/Document")
	public void modifierDocument(@RequestBody Document m) {
		docs.modifierDocument(m);
	}
	@ApiOperation(value = "chercher un document", notes = "cette methode permet de chercher un document par son id qui existe dans le base de donnée", response = Document.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le document est trouvée"),
	@ApiResponse(code = 404, message = "aucun document existe dans la base avec ce id"),
	@ApiResponse(code = 500, message = "aucun document existe dans la base de donnée avec ce id")
	})
	@GetMapping("/Document/{id}")
	public DocumentResponse getDocument(@PathVariable int id) {
		DocumentResponse m = docs.getDocument(id);
		return m;
	}

	@GetMapping("/GetAllDocument")
	public List<Document> getAllDocument() {
		return docs.getAllDocument();
	}
	@ApiOperation(value = "chercher un document", notes = "cette methode permet de chercher un document par  id d'enseignant qui existe dans le base de donnée", response = Document.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le document est trouvée"),
	@ApiResponse(code = 404, message = "aucun documentt existe dans la base avec ce id"),
	@ApiResponse(code = 500, message = "aucun edocument existe dans la base de donnée avec ce id")
	})
	@GetMapping("/GetDocByEns/{id}")
	public List<Document> getDocumentByEnseignant(@PathVariable int id) {
		return docs.getDocumentByEnseignant(id);
	}
	@ApiOperation(value = "chercher un document", notes = "cette methode permet de chercher un document par  id de matiére qui existe dans le base de donnée", response = Document.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "le document est trouvée"),
	@ApiResponse(code = 404, message = "aucun documentt existe dans la base avec ce id"),
	@ApiResponse(code = 500, message = "aucun edocument existe dans la base de donnée avec ce id")
	})
	@GetMapping("/GetDocByMat/{id}")
	public List<Document> getDocumentByMatiere(@PathVariable int id) {
		return docs.getDocumentByMatiere(id);
	}
	
}
