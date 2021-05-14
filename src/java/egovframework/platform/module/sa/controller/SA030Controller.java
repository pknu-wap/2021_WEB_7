package egovframework.platform.module.sa.controller;

import egovframework.platform.module.base.BaseController;
import egovframework.platform.module.sa.service.SA030Service;
import egovframework.platform.module.sa.vo.SaleVO;
import egovframework.platform.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
public class SA030Controller extends BaseController {
	@Autowired
	private SA030Service sa030Service;

	public SA030Controller() {
		super();

		logger = LoggerFactory.getLogger(this.getClass());
	}

	@RequestMapping("/sa030.do")
	public ModelAndView sa030page(HttpServletRequest request, HttpServletResponse response, @RequestParam String project) {
		logger.info("requestURL:" + context.getRequestURL());

		try {
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		ModelAndView view = new ModelAndView("/sa/sa030Page");

		return view;
	}

	@RequestMapping("/sa030/select")
	public List<SaleVO> sa030select(HttpServletRequest request, HttpServletResponse response, @RequestParam String year, @RequestParam String subType) {
		logger.info("requestURL:" + context.getRequestURL());
		List<SaleVO> list = null;

		try {
			list = sa030Service.select(year, subType);
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		return list;
	}
}
