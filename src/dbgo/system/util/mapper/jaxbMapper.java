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
 * ʹ��Jaxb2.0ʵ��XML<->Java Object��Mapper.
* @author ���Ի� 
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
	 * ����Marshaller���趨encoding(��Ϊnull).
	 * �̲߳���ȫ����Ҫÿ�δ�����pooling��
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
	 * ����UnMarshaller
	* @Title: createUnmarshaller 
	* @Description: TODO(������һ�仰�����������������) 
	* @param @param clazz
	* @param @return    �趨�ļ� 
	* @return Unmarshaller    �������� 
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
