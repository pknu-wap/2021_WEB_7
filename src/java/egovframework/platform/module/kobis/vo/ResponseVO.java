package egovframework.platform.module.kobis.vo;

import egovframework.platform.module.common.vo.DefaultVO;

public class ResponseVO extends DefaultVO {
	private HeaderVO header;
	private BodyVO body;

	public HeaderVO getHeader() {
		return header;
	}

	public void setHeader(HeaderVO header) {
		this.header = header;
	}

	public BodyVO getBody() {
		return body;
	}

	public void setBody(BodyVO body) {
		this.body = body;
	}
}
