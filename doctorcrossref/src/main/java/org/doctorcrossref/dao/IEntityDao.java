package org.doctorcrossref.dao;

import java.util.List;

import org.doctorcrossref.model.EntityObject;

public interface IEntityDao {

	void         deleteEntity(EntityObject obj);
	EntityObject saveEntity(EntityObject obj);
	List<EntityObject> getEntities();
}
