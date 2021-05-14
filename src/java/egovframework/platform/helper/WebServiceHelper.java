package egovframework.platform.helper;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class WebServiceHelper {
	public static Object getService(ServletContext ctx, String serviceBeanId) throws IllegalStateException, BeansException {
		Object rObj = null;

		try {
			WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(ctx);
			rObj = wac.getBean(serviceBeanId);
		} catch (IllegalStateException e) {
			throw new IllegalStateException("WebServiceHelderp.getService error! ");
		} catch (BeansException e) {
			throw new BeanCreationException(serviceBeanId, "Failed to create");
		}

		return rObj;
	}
}
