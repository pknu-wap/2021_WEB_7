package egovframework.platform.module.sa.service;

import egovframework.platform.module.base.BaseService;
import egovframework.platform.module.common.service.CommonService;
import egovframework.platform.module.sa.vo.SaleVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("sa010Service")
public class SA010Service extends BaseService {
	@Autowired
	protected CommonService commonService;
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public SA010Service() {
		super();

		nameSpace = "SA010Mapper";
		logger = LoggerFactory.getLogger(this.getClass());
	}

	public List<SaleVO> select(String year, String month) throws BadSqlGrammarException {
		HashMap<String, String> param = new HashMap<>();
		param.put("YYYYMM", year + month);

		List<SaleVO> list = sqlSessionTemplate.selectList(nameSpace + ".select", param);

		return list;
	}

	@Transactional
	public void save(List<SaleVO> list) throws BadSqlGrammarException {
		for (SaleVO param : list) {
			sqlSessionTemplate.insert(nameSpace + ".save", param);
		}
	}
}
