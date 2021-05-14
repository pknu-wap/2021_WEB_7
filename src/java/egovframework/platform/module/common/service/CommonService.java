/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package egovframework.platform.module.common.service;

import egovframework.platform.module.base.BaseService;
import egovframework.platform.module.common.vo.ComCodeVO;
import egovframework.platform.module.common.vo.FileVO;
import egovframework.platform.module.common.vo.JSCodeVO;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service("commonService")
public class CommonService extends BaseService {
	@Autowired
	private DataSourceTransactionManager transactionManager;

	public CommonService() {
		super();

		nameSpace = "CommonMapper";
		logger = LoggerFactory.getLogger(this.getClass());
	}

	// 순번펑션 (독립적인 트랜잭션)
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public String selectSeq(String orgaCd, String workGrp, String code) throws BadSqlGrammarException {
		HashMap<String, String> param = new HashMap<>();
		param.put("ORGA_CD", orgaCd);
		param.put("WORK_GRP", workGrp);
		param.put("CODE", code);

		String seq = sqlSessionTemplate.selectOne(nameSpace + ".selectSeq", param);

		return seq;
	}

	public List<ComCodeVO> selectComCode(HashMap param) throws BadSqlGrammarException {
		List<ComCodeVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectComCode", param);

		return list;
	}

	public List<JSCodeVO> selectAddr1(HashMap param) throws BadSqlGrammarException {
		List<JSCodeVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectAddr1", param);

		return list;
	}

	public List<JSCodeVO> selectAddr2(HashMap param) throws BadSqlGrammarException {
		List<JSCodeVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectAddr2", param);

		return list;
	}

	public List<JSCodeVO> selectAddr3(HashMap param) throws BadSqlGrammarException {
		List<JSCodeVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectAddr3", param);

		return list;
	}

	public List<ComCodeVO> selectDeviceType(HashMap<String, String> param) throws BadSqlGrammarException {
		List<ComCodeVO> list = sqlSessionTemplate.selectList(nameSpace + ".selectDeviceType", param);

		return list;
	}

	public String selectFileID(FileVO param) throws BadSqlGrammarException {
		String result = sqlSessionTemplate.selectOne(nameSpace + ".selectFileId", param);

		return result;
	}

	public FileVO selectFile(FileVO param) throws BadSqlGrammarException {
		FileVO result = sqlSessionTemplate.selectOne(nameSpace + ".selectFile", param);

		return result;
	}

	@Transactional
	public void insertFile(FileVO file) throws BadSqlGrammarException {
		sqlSessionTemplate.insert(nameSpace + ".insertFile", file);
	}
}