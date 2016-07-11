package dbgo.system.util.mapper;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang3.StringUtils;

/**
 * 使用Jaxb2.0实现XML<->Java Object的Mapper.
* @author 戴辉辉 
* @date Jun 19, 2016 2:19:53 PM 
* @version V1.0
 */
public class jaxbMapper {
	@SuppressWarnings("rawtypes")
	private static ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<Class, JAXBContext>();
	

	/**
	 * Java Object->Xml with encoding.
	 */
	@SuppressWarnings("rawtypes")
	public static String toXml(Object root, Class clazz, String encoding) {
		try {
			StringWriter writer = new StringWriter();
			createMarshaller(clazz, encoding).marshal(root, writer);
			return writer.toString();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Xml->Java Object.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xml, Class<T> clazz) {
		try {
			StringReader reader = new StringReader(xml);
			return (T) createUnmarshaller(clazz).unmarshal(reader);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 创建Marshaller并设定encoding(可为null).
	 * 线程不安全，需要每次创建或pooling。
	 */
	public static Marshaller createMarshaller(@SuppressWarnings("rawtypes") Class clazz, String encoding) {
		try {
			JAXBContext jaxbContext = getJaxbContext(clazz);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			if (StringUtils.isNotBlank(encoding)) {
				marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
			}

			return marshaller;
		} catch (JAXBException e) {
			throw new RuntimeException("");
		}
	}
	
	protected static JAXBContext getJaxbContext(@SuppressWarnings("rawtypes") Class clazz) {
		JAXBContext jaxbContext = jaxbContexts.get(clazz);
		if (jaxbContext == null) {
			try {
				jaxbContext = JAXBContext.newInstance(clazz);
				jaxbContexts.putIfAbsent(clazz, jaxbContext);
			} catch (JAXBException ex) {
			}
		}
		return jaxbContext;
	}
	
	/**
	 * 创建UnMarshaller
	* @Title: createUnmarshaller 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param clazz
	* @param @return    设定文件 
	* @return Unmarshaller    返回类型 
	* @throws
	 */
	public static Unmarshaller createUnmarshaller(@SuppressWarnings("rawtypes") Class clazz) {  
        try {  
        	Unmarshaller unmarshaller=getJaxbContext(clazz).createUnmarshaller();
            return unmarshaller;//jaxbContext.createUnmarshaller();  
        } catch (JAXBException e) {  
            throw new RuntimeException(e);  
        }  
    }
}
