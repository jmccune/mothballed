package org.doctorcrossref.dao;

import java.util.List;

import org.doctorcrossref.model.EntityObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EntityDaoImpl implements IEntityDao {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntityObject> getEntities() {
		Session s =this.sessionFactory.getCurrentSession();
		return s.createCriteria(EntityObject.class).list();
	}

	@Override
	public EntityObject saveEntity(EntityObject entity) {
		Session session = sessionFactory.getCurrentSession();
	    session.saveOrUpdate(entity);
	    return entity;
	}

	@Override
	public void deleteEntity(EntityObject obj) {
		Session session = sessionFactory.getCurrentSession();
	    session.delete(obj);		
	}
	
	
	
	

}
