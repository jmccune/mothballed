package org.doctorcrossref.model.domain.support;

import java.util.Date;

import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.model.domain.Ontology;
import org.doctorcrossref.model.domain.context.EntityContext;
import org.doctorcrossref.model.system.standard.StandardProperty;
import org.tierlon.util.GeneralHelper;

public class EntityFactory {

	
	
	// ----------------------
	// Excerpt
	// ----------------------
	EntityObject createExcerpt(EntityContext originatingContext,
			String label, String description,
			Date publicationDate) {
		
		//TODO:
		throw new IllegalStateException("Not yet implemented");
	}
	
	
	// ----------------------
	// Volume
	// ----------------------
	// (Volume -- may refer to a formal volume (Volume I)
	//            or to an informal volume -- the volume of all the works of
	//                      CS Lewis)
	// ----------------------
	EntityObject createVolume(EntityContext volumeContext,
			String label, String description,
			Date startDate, Date endDate) {
		
		//TODO:
		throw new IllegalStateException("Not yet implemented");
	}

	// ----------------------
	// Article
	// ----------------------
	EntityObject createArticle(EntityContext originatingContext,
			String label, String description,
			Date publicationDate) {
		
		//TODO:
		throw new IllegalStateException("Not yet implemented");
	}

	// ----------------------
	// Book
	// ----------------------
	EntityObject createBook(String label, String description,
			EntityObject author, Date publicationDate) {
		GeneralHelper
				.assertNonNull(label, description, author, publicationDate);

		return new EntityObject(label, description, Ontology.BOOK_TYPE, author,
				publicationDate, null, null, null);

	}

	// ----------------------
	// WebPage
	// ----------------------
	EntityObject createWebPage(String label, String description,
			EntityObject author, Date pageCreationDate, String webpageUrl) {
		EntityObject object = new EntityObject(label, description,
				Ontology.WEBPAGE_TYPE, author, pageCreationDate, null, null,
				null);

		object.addProperty(StandardProperty.SITE_URL_PROPERTY, webpageUrl);
		return object;
	}

}
