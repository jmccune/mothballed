package org.universaldataengine.webui;


import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.doctorcrossref.dao.IEntityDao;
import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.model.domain.Ontology;
import org.doctorcrossref.model.domain.support.EntityFactory;
import org.doctorcrossref.model.domain.support.EntityProperty;
import org.doctorcrossref.system.behavior.IBehavior;
import org.doctorcrossref.system.standard.BehaviorNames;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tierlon.system.helper.GeneralHelper;


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
	@RequestMapping(value = "/entity/test", method = RequestMethod.GET)
	@ResponseBody
	public String home(Locale locale, Model model) {
		logger.info("Welcome to entity");
		
		EntityObject obj = new EntityObject("TestObj","Testing","faketype",null,
				new Date(),null,null,null);
		
		EntityProperty property = new EntityProperty(obj,"TestKey","TestValue");
		obj.getPropertySet().add(property);
		
		dao.saveEntity(obj);
		List<EntityObject> entityObjectList = dao.getEntities();
		
		EntityFactory factory = new EntityFactory();
		EntityObject systemAuthor = factory.obtainSystemAuthor();
		
		Date fakeDate1 = GeneralHelper.parseDate("1987.06.05");
		EntityObject jmccune = factory.createAuthor("McCune","Justin","A",null,
				"Aspiring programmer",
				fakeDate1,null);
		
		EntityObject edgarAllenPoe = factory.createAuthor("Poe","Edgar","Allen",null,
				"Dead Poet",
				fakeDate1,null);
		
		dao.saveEntity(jmccune);
		dao.saveEntity(edgarAllenPoe);
		return "EntityList"+ entityObjectList.size()+" SysAuhtor: "+systemAuthor;
	}
	
	@RequestMapping(value = "/entity/author", method = RequestMethod.GET)
	@ResponseBody
	public String getAuthors(Locale locale, Model model) {
		logger.info("Welcome to getAuthors!");
		
		List<EntityObject> authorList = 
				dao.getEntitiesBy(null, Ontology.AUTHOR,null);
		
		System.out.println("AUTHORLIST SIZE: "+authorList.size());
		
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		int count=0;
		for (EntityObject author: authorList) {
			IBehavior<String> serializer=
					author.getBehavior(BehaviorNames.JSON_SERIALIZATION);
			
			if (serializer!=null && 
				serializer.canRunBehavior().isAcceptable()) {
				if (count++>0) {
					sb.append(",");
				}
				sb.append(serializer.runBehavior(author));
			}
		}
		sb.append("]");
		
//		StringWriter writer = new StringWriter();
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			mapper.writeValue(writer, authorList);
//		} catch (Exception e) {
//			e.printStackTrace();
//			return " { exception: "+e.getMessage()+"}";
//		}
		
		
		
		return "AUTHORS:>> "+sb.toString();
		
	}
	
	
}
