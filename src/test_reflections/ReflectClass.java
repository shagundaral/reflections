package test_reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.google.gson.Gson;

public class ReflectClass {
	
	public static void main(String[] args) {
		
		/*Gson gson = new Gson();
		gson.toJson("");*/
		Person p = new Person();
		p.setId(12);
		//p.setName("shagun");
		p.setSalary(100);
		Person p1 = new Person();
		p1.setId(2);
		//p.setName("gv");
		p1.setSalary(9999);
		p.setBoss(p1);
		
		String jsonString = toJson(p);
		System.out.println(jsonString);
	  //  Person p2 = (Person) fromJson(jsonString,Person.class);
		
	}

	
	public static String toJson(Object obj) {
		
		StringBuffer stringBuffer = new StringBuffer();
		Class<? extends Object> mainClass = obj.getClass();
		String className = mainClass.getSimpleName();
		
		stringBuffer.append(className+":{");
		Field[] fields = mainClass.getDeclaredFields();
		for (Field field : fields) {
			
			if(Modifier.isPrivate(field.getModifiers())){
				field.setAccessible(true);
			}
			
			if(!Modifier.isTransient(field.getModifiers())){
				
				try {
					Object field_value = field.get(obj);
					
					if(null!=field_value){
						if(!field.getType().isPrimitive() && null!=field.get(obj)){
							stringBuffer.append(toJson(field_value));
						}else{
							stringBuffer.append(field.getName()+":");
							stringBuffer.append(field.get(obj)).append(",");
						}
					}
					
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
		stringBuffer.append("}");
		
		
		
		return stringBuffer.toString();
	}
	
	public static Object fromJson(String input, Class<? extends Object> cls){
		
		if(null!=input){
			Constructor constrcutor;
			try {
				constrcutor = cls.getConstructor();
			
			Object o =constrcutor.newInstance(); 
			Field[] fields = cls.getDeclaredFields();
			String jsonString = input.substring(input.indexOf("{")+1, input.length()-1);
			String[] fieldValues = jsonString.split(",");
			for (String s : fieldValues) {
				String fieldName = s.split(":")[0];
				
				for(Field field : fields){
					if(field.getName().equalsIgnoreCase(fieldName)){
						Object type = field.getType();
						System.out.println(type.toString());
						if(type.toString().equals("int")){
							Integer value = Integer.parseInt(s.split(":")[1]);
							field.set(o,value);
						}
						
						if(type.toString().equals("double")){
							Double value = Double.parseDouble(s.split(":")[1]);
							field.set(o, value);
						}
					}
				}
				
			}
			return o;
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			
		}
		return null;
	}
	
	
	
}
