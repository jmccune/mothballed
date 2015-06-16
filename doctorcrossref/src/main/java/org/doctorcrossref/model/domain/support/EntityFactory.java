package org.doctorcrossref.model.domain.support;

import java.util.Date;
import java.util.List;

import org.doctorcrossref.dao.IEntityDao;
import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.model.domain.Ontology;
//import org.doctorcrossref.model.domain.context.EntityContext;
import org.doctorcrossref.system.standard.PersonName;
import org.doctorcrossref.system.standard.StandardProperty;
import org.springframework.context.ApplicationContext;
import org.tierlon.system.helper.GeneralHelper;
import org.tierlon.system.helper.SpringHelper;

public class EntityFactory {

	
	private IEntityDao entityDao;
	private static EntityObject systemAuthor=null;
	
	
	// ==============================================================
	// PUBLIC FACTORY METHODS
	// ==============================================================
	
	
	// ----------------------
	// Author
	// ----------------------
	public EntityObject createAuthor(String lastName,String firstName,String middle,
			String suffix,
			String description, Date birthDate, Date deathDate) {

		EntityObject systemAuthor = obtainSystemAuthor();
		
		String name=
				PersonName.normalizeName(lastName, firstName, middle, suffix);
		
		EntityObject object = new EntityObject(name, description,
				Ontology.AUTHOR, systemAuthor, birthDate, null, deathDate,
				null);
		
		object.addProperty("name_first", firstName);
		object.addProperty("name_last", lastName);
		if (middle!=null) 
			object.addProperty("name_middle", middle);
		if (suffix!=null)
			object.addProperty("name_suffix", suffix);
		return object;
	}
	
	// ----------------------
	// Excerpt
	// ----------------------
	public EntityObject createExcerpt(EntityObject author,
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
	public EntityObject createVolume(EntityObject author,
			String label, String description,
			Date startDate, Date endDate) {
		
		//TODO:
		throw new IllegalStateException("Not yet implemented");
	}

	// ----------------------
	// Article
	// ----------------------
	public EntityObject createArticle(EntityObject author,
			String label, String description,
			Date publicationDate) {
		
		//TODO:
		throw new IllegalStateException("Not yet implemented");
	}

	// ----------------------
	// Book
	// ----------------------
	public EntityObject createBook(EntityObject author,
			String label, String description,
			Date publicationDate) {
		GeneralHelper
				.assertNonNull(label, description, author, publicationDate);

		return new EntityObject(label, description, Ontology.BOOK_TYPE, author,
				publicationDate, null, null, null);

	}

	// ----------------------
	// WebPage
	// ----------------------
	public EntityObject createWebPage(EntityObject author,
			String label, String description,
			Date pageCreationDate, String webpageUrl) {
		EntityObject object = new EntityObject(label, description,
				Ontology.WEBPAGE_TYPE, author, pageCreationDate, null, null,
				null);

		object.addProperty(StandardProperty.SITE_URL_PROPERTY, webpageUrl);
		return object;
	}
	

	// ----------------------
	// SystemAuthor
	// ----------------------	
	public EntityObject obtainSystemAuthor() {
		if (systemAuthor!=null) {
			return systemAuthor;
		}
				
		List<EntityObject> entityList = 
				getEntityDao().getEntitiesBy("__SYSTEM__",Ontology.AUTHOR,null);
		
		if (!entityList.isEmpty())
			return entityList.get(0);
		
		Date datetime = new Date();
		EntityObject object = new EntityObject("__SYSTEM__",
				"The System Which is the beginning/end of all creation",
				Ontology.AUTHOR, null, datetime,datetime,null,null);
		
		object.setCreator(object);
		getEntityDao().saveEntity(object);
		return object;
	}

	// ==============================================================
	// PUBLIC FACTORY METHODS
	// ==============================================================
	private IEntityDao getEntityDao() {
		if (this.entityDao!=null)
			return this.entityDao;
		
		
		ApplicationContext context = SpringHelper.getApplicationContext();
		entityDao = (IEntityDao) context.getBean("entityDao");
		if (entityDao==null)
			throw new NullPointerException();
		return entityDao;
	}
}
