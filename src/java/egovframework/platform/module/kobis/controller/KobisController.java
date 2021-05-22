package egovframework.platform.module.kobis.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import egovframework.platform.module.base.BaseRestController;
import egovframework.platform.module.kobis.vo.MovieListResultVO;
import egovframework.platform.module.kobis.service.KobisService;
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

	private final String BASE_URL = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/";
	private final String SERVICE_KEY = "809a07f4b41508becc428d67efe8446d";

	public KobisController() {
		super();
		logger = LoggerFactory.getLogger(this.getClass());
	}

	@RequestMapping(value="/getMovieList", method = RequestMethod.GET)
	public @ResponseBody
	MovieListResultVO getMovieList() {
		logger.info("requestURL:" + context.getRequestURL());
		MovieListResultVO movieList = null;

		String result = "";

		try {
			String MOVIE_URL = BASE_URL + "movie/searchMovieList.json?key=" + SERVICE_KEY;
			MOVIE_URL += "&curPage=1";
			//MOVIE_URL += "&movieNm=" + movieNm;

			URL url = new URL(MOVIE_URL);

			RestTemplate rt = new RestTemplate();
			ObjectMapper mapper = new ObjectMapper();

			//result = rt.getForObject(url.toURI(), String.class);

			movieList = mapper.readValue(rt.getForObject(url.toURI(), String.class), MovieListResultVO.class);
			System.out.println(movieList);
		} catch (Exception e) {
			logger.error(Constants.ERROR_CON, e);
		}

		return movieList;
	}
}