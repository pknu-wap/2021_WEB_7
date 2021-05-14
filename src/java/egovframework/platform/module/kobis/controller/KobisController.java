package egovframework.platform.module.kobis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.platform.module.base.BaseRestController;
import egovframework.platform.module.kobis.service.KobisService;
import egovframework.platform.module.kobis.vo.KobisVO;
import egovframework.platform.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URL;

@CrossOrigin
@RestController
public class KobisController extends BaseRestController {
	@Resource(name = "kobisService")
	private KobisService kobisService;

	/** Validator 검토필요 */
	/*@Resource(name = "beanValidator")
	protected DefaultBeanValidator beanValidator;*/

	private final String BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/movie/searchMovieList.json";
	private final String SERVICE_KEY = "809a07f4b41508becc428d67efe8446d";

	public KobisController() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// 기상정보 GET
	//@RequestMapping(value="/getMovieList/{x}/{y}", method = RequestMethod.GET)
	@RequestMapping(value="/getMovieList", method = RequestMethod.GET)
	public @ResponseBody
	//KobisVO getKma(@PathVariable("x") String x, @PathVariable("y") String y) {
	String getMovieList() {
		logger.info("requestURL:" + context.getRequestURL());
		KobisVO kobisVO = null;

		String result = "";

		try {
			String MOVIE_URL = BASE_URL + "?key=" + SERVICE_KEY;
			MOVIE_URL += "&curPage=1";

			URL url = new URL(MOVIE_URL);

			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();

			//kobisVO = mapper.readValue(rt.getForObject(url.toURI(), String.class), KobisVO.class);

			result = rt.getForObject(url.toURI(), String.class); // 데이터 수신후 무조건 호출

			System.out.println("result\n" + result);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		return result;
	}
}