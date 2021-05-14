package egovframework.platform.module.main.service;

import egovframework.platform.module.base.BaseService;
import egovframework.platform.module.common.service.CommonService;
import egovframework.platform.module.main.vo.ProgramVO;
import egovframework.platform.module.main.vo.UserVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("mainService")
public class MainService extends BaseService {
	@Autowired
	private DataSourceTransactionManager transactionManager;

	@Autowired
	protected CommonService commonService;

	public MainService() {
		super();

		nameSpace = "MainMapper";
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// 사용자정보 조회
	public UserVO selectLoginUser(UserVO user) throws BadSqlGrammarException {
		HashMap<String, String> param = new HashMap<>();
		param.put("USR_ID", user.USR_ID);
		param.put("USR_PW", user.USR_PW);

		user = sqlSessionTemplate.selectOne(nameSpace + ".selecUserInfo", param);

		return user;
	}

	// 사용자 메뉴 목록
	public List<ProgramVO> selectMenuList(String programId) throws BadSqlGrammarException {
		HashMap<String, String> param = new HashMap<>();
		param.put("PGM_ID", programId);

		List<ProgramVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectMenuList", param);

		return list;
	}
}