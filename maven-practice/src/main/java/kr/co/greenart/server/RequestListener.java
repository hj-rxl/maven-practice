package kr.co.greenart.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletRequestAttributeEvent;
import jakarta.servlet.ServletRequestAttributeListener;
import jakarta.servlet.ServletRequestEvent;
import jakarta.servlet.ServletRequestListener;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServletRequest;

@WebListener //리스너 등록 (web.xml에서도 가능)
public class RequestListener implements ServletRequestListener, ServletRequestAttributeListener {
	private static final Logger log = LoggerFactory.getLogger(RequestListener.class); //param: 작성중인 클래스명
	
	@Override
	public void attributeAdded(ServletRequestAttributeEvent srae) {
		String attrName = srae.getName();
		Object attrValue = srae.getValue();
		
		log.info("{} : {} added", attrName, attrValue.toString());
	}

	@Override
	public void requestInitialized(ServletRequestEvent sre) {
		HttpServletRequest req = (HttpServletRequest) sre.getServletRequest();
		
		String uri = req.getRequestURI();
		String method = req.getMethod();
		
		String logMessage = String.format("%s %s", uri, method);
		//사용자가 요청할때마다 기록남기기 (로깅)
		//이렇게 콘솔에만 남기는건 별로라서, 로거(로깅하는 객체) 라이브러리 2개 추가 => slf4j & logback classic
//		System.out.println(logMessage);
		
		log.info(logMessage);
	}
}




