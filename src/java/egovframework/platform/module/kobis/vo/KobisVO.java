package egovframework.platform.module.kobis.vo;

import egovframework.platform.module.base.BaseMapperInterface;
import egovframework.platform.module.common.vo.DefaultVO;
import egovframework.platform.util.Constants;

public class KobisVO extends DefaultVO implements BaseMapperInterface {
	private ResponseVO response;
	public ResponseVO getResponse() {
		return response;
	}

	public void setResponse(ResponseVO response) {
		this.response = response;
	}

	@Override
	public KobisVO complete() {
		return null;
	}
}

