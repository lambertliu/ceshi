package it.analyze.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;

public interface UrlService {

	EasyUIResult QueryTo(String pos, String city, Integer page, Integer rows);

}
