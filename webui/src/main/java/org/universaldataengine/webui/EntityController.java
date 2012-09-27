package org.universaldataengine.webui;


import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.doctorcrossref.dao.IEntityDao;
import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.model.domain.support.EntityProperty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/*
 * 
 */
@Controller
public class EntityController {
	private static final Logger logger = LoggerFactory.getLogger(EntityController.class);
	
	private IEntityDao dao;
	
	@Autowired
	public void setEntityDao(IEntityDao dao) {
		this.dao = dao;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/entity/", method = RequestMethod.GET)
	@ResponseBody
	public String home(Locale locale, Model model) {
		logger.info("Welcome to entity");
		
		EntityObject obj = new EntityObject("TestObj","Testing","faketype",null,
				new Date(),null,null,null);
		
		EntityProperty property = new EntityProperty(obj,"TestKey","TestValue");
		obj.getPropertySet().add(property);
		
		dao.saveEntity(obj);
		List<EntityObject> entityObjectList = dao.getEntities();
		return "EntityList"+ entityObjectList.size();
	}
	
}
