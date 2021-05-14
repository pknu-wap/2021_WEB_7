package egovframework.platform.module.base;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Named;

@Service("baseService")
public class BaseService extends EgovAbstractServiceImpl {
	protected Logger logger;

	@Named("sqlSessionTmpl")
	@Inject
	protected SqlSessionTemplate sqlSessionTemplate;
	protected String nameSpace;
}
