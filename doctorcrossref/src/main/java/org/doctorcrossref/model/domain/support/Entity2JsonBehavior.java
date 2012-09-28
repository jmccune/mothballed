package org.doctorcrossref.model.domain.support;

import java.beans.PropertyDescriptor;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.doctorcrossref.model.domain.EntityObject;
import org.doctorcrossref.system.behavior.AbstractBehaviorBase;
import org.doctorcrossref.system.standard.BehaviorNames;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.tierlon.system.helper.GeneralHelper;

public class Entity2JsonBehavior extends AbstractBehaviorBase<String> {

	private static Set<String> exclusionSet;
	static {
		exclusionSet = new HashSet<String>();
		//exclusionSet.add("creator"); 
		exclusionSet.add("propertySet");
		exclusionSet.add("class");
	}
	// ==============================================================
	// CONSTRUCTION
	// ==============================================================
	public Entity2JsonBehavior() {
		super(BehaviorNames.JSON_SERIALIZATION);
	}
	
	// ==============================================================
	// IMPLEMENTATION
	// ==============================================================
	@Override
	public String runBehavior(Object... args) {
		GeneralHelper.assertObjectsOfType(EntityObject.class, args);
		String start=(args.length==1) ? "": "[";
		String end=(args.length==1)? "":"]";
		
		StringBuilder sb = new StringBuilder();
		sb.append(start);
		
		int count=0;
		for (Object obj: args) {
			if (count++>0) sb.append(",");
			EntityObject entity = (EntityObject) obj;
			Map<String,Object> mapRep = buildAsMap(entity);
			addPropMap(mapRep,entity);
			sb.append(serializeMapRep(mapRep));
		}
		
		
		sb.append(end);
		return sb.toString();
	}


	
	@Override
	public Class<String> getResultTypeClass() {
		return String.class;
	}

	// ==============================================================
	// PRIVATE Support Methods
	// ==============================================================
	private void addPropMap(Map<String,Object> mapRep, EntityObject obj) {
		Set<EntityProperty> propSet = obj.getPropertySet();
		Map<String,String> propMap = new HashMap<String,String>();
		for (EntityProperty property : propSet) {
			propMap.put(property.getKey(), property.getValue());
		}
		mapRep.put("propertyMap", propMap);
	}
	
	
	private Map<String, Object> buildAsMap(EntityObject entity) {
		
		BeanWrapper wrapper = new BeanWrapperImpl(entity);
		PropertyDescriptor[] descriptors=wrapper.getPropertyDescriptors();
		Map<String,Object> propMap = new HashMap<String,Object>();
		for (PropertyDescriptor descriptor : descriptors) {
			String key = descriptor.getName();
			if (exclusionSet.contains(key)) {
				continue;
			}
			Object value = wrapper.getPropertyValue(key);
			if (value!=null) {
				if (value instanceof EntityObject) {
					//Use a special representation of the entity object.
					Map<String,String> submap = new HashMap<String,String>();
					EntityObject subobject = (EntityObject) value;
					submap.put("label",subobject.getLabel());
					submap.put("type",subobject.getType());
					submap.put("UUID", subobject.getUUID());
					value = submap;
					
					key = key+"_reference";
				}
			}
			propMap.put(key,value);
		}
		return propMap;
	}
	
	private String serializeMapRep(Map<String, Object> mapRep) {
		StringWriter writer = new StringWriter();
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(writer, mapRep);
		} catch (Exception e) {
			e.printStackTrace();
			return " { exception: "+e.getMessage()+"}";
		}
		return writer.toString();
	}
}
