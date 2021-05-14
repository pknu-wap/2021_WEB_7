package egovframework.platform.module.sa.controller;

import egovframework.platform.module.base.BaseController;
import egovframework.platform.module.common.vo.ResultVO;
import egovframework.platform.module.sa.service.SA010Service;
import egovframework.platform.module.sa.vo.SaleVO;
import egovframework.platform.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@CrossOrigin
@RestController
public class SA010Controller extends BaseController {
	@Autowired
	private SA010Service sa010Service;

	public SA010Controller() {
		super();

		logger = LoggerFactory.getLogger(this.getClass());
	}

	@RequestMapping("/sa010.do")
	public ModelAndView sa010page(HttpServletRequest request, HttpServletResponse response, @RequestParam String project) {
		logger.info("requestURL:" + context.getRequestURL());

		try {
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		ModelAndView view = new ModelAndView("/sa/sa010Page");

		return view;
	}

	@RequestMapping("/sa010/select")
	public List<SaleVO> sa010select(HttpServletRequest request, HttpServletResponse response, @RequestParam String year, @RequestParam String month) {
		logger.info("requestURL:" + context.getRequestURL());
		List<SaleVO> list = null;

		try {
			list = sa010Service.select(year, month);
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		return list;
	}

	@RequestMapping(value = "/sa010/save", method = RequestMethod.POST)
	public ResultVO insertCA(@RequestBody List<SaleVO> param) {
		logger.info("requestURL:" + context.getRequestURL());

		ResultVO result = null;

		try {
			sa010Service.save(param);

			result = new ResultVO("00", "OK");
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
			result = new ResultVO("99", Constants.ERROR_SQL);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
			result = new ResultVO("99", Constants.ERROR_CON);
		}

		return result;
	}
}
