package org.doctorcrossref.dao;

import java.util.List;

import org.doctorcrossref.model.domain.EntityObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityObject> getEntitiesBy(String label, String type,
			EntityObject creator) {
		Session s =this.sessionFactory.getCurrentSession();
		Criteria criteria = s.createCriteria(EntityObject.class);
		if (label!=null)
			criteria =criteria.add(Restrictions.eq("label", label));
		if (type!=null)
			criteria = criteria.add(Restrictions.eq("type", type));
		if (creator!=null)
			criteria = criteria.add(Restrictions.eq("creator", creator));
		return criteria.list();
	}

}
