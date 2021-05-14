package egovframework.platform.module.base;
import egovframework.platform.module.common.DefaultRestTemplate;
import egovframework.platform.module.main.vo.UserVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 시스템 공통 controller
 */
@CrossOrigin
public class BaseController {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	protected UserVO loginUserInfo;

	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Autowired
	protected DefaultRestTemplate restService;

	@Autowired
	protected HttpServletRequest context;

	private boolean DEBUG_MODE = false;

	public BaseController() {
		super();
	}

	protected void setLoginUserInfo() {
		System.out.println("setLoginUserInfo");
		if (context != null) {
			HttpSession session = context.getSession();

			if (session != null) {
				loginUserInfo = (UserVO) session.getAttribute("userInfoPlatform");
			}
		}
	}
}