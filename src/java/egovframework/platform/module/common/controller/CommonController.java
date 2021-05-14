package egovframework.platform.module.common.controller;

import egovframework.platform.module.base.BaseController;
import egovframework.platform.module.common.service.CommonService;
import egovframework.platform.module.common.vo.ComCodeVO;
import egovframework.platform.module.common.vo.FileVO;
import egovframework.platform.module.common.vo.ImageVO;
import egovframework.platform.module.common.vo.ResultVO;
import egovframework.platform.util.Constants;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

@CrossOrigin
@RestController
public class CommonController extends BaseController {

	@Autowired
	private CommonService service;

	public CommonController() {
		super();

		logger = LoggerFactory.getLogger(this.getClass());
	}

	@RequestMapping(value = "/getComCode", method = RequestMethod.GET)
	public List<ComCodeVO> getAddr1(@RequestParam String workGrp, @RequestParam String refCd) {
		logger.info("requestURL:" + context.getRequestURL());

		List<ComCodeVO> list = null;

		try {
			HashMap<String, String> param = new HashMap<>();

			list = service.selectComCode(param); // 장치 마스터
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		}

		return list;
	}

	@RequestMapping(value = "/saveImage", method = RequestMethod.POST)
	public String saveBlobToImage(HttpServletRequest request, HttpServletResponse response, @RequestBody ImageVO param) throws IOException {
		logger.info("requestURL:" + context.getRequestURL());

		String imageFileName = "";

		String imageData = param.SOURCE;
		String base64Data = imageData.split(",")[1];

		byte[] decodedBytes = Base64.getDecoder().decode(base64Data);
		ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
		BufferedImage image = ImageIO.read(bis);

		Random random = new Random();
		random.setSeed(new Date().getTime());
		String filePath = request.getSession().getServletContext().getRealPath("/copytemp") + "/" + String.valueOf(Math.round(random.nextDouble() * 1000000)) + "_output.png";

		File outputFile = new File(filePath);
		ImageIO.write(image, "png", outputFile);

		imageFileName = outputFile.getName();

		if (bis != null) {
			bis.close();
		}

		return imageFileName;
	}

	public String getFileId (FileVO fileVO) {
		logger.info("requestURL:" + context.getRequestURL());

		String result = null;

		try {
			result = service.selectFileID(fileVO);
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		}

		return result;
	}

	public FileVO getFile (FileVO fileVO) {
		logger.info("requestURL:" + context.getRequestURL());

		FileVO file = null;

		try {
			file = service.selectFile(fileVO);
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
		}

		return file;
	}

	public ResultVO insertFile (FileVO fileVO) {
		logger.info("requestURL:" + context.getRequestURL());

		ResultVO result = null;

		try {
			service.insertFile(fileVO);

			result = new ResultVO("00", "OK");
		} catch (BadSqlGrammarException e) {
			logger.error(Constants.ERROR_SQL, e);
			result = new ResultVO("99", "[Controller Error]");
		}

		return result;
	}

	public String getSHA256(String input){
		String toReturn = null;

		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(input.getBytes("utf8"));
			toReturn = String.format("%064x", new BigInteger(1, digest.digest()));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return toReturn;
	}
}