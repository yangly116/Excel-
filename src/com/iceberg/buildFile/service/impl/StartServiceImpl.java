package com.iceberg.buildFile.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.iceberg.buildFile.service.CallWorkService;
import com.iceberg.buildFile.service.CreatFileService;
import com.iceberg.buildFile.service.StartService;

/** 
 * 控制台
 * @author 作者：杨文培 
 * @version 创建时间：Jul 15, 2017 9:39:54 PM  
 */

public class StartServiceImpl implements StartService{
	@Resource
	private BuildPatchServiceImpl buildPatchServiceImpl;
	private Map<String, CallWorkService> mappingService;
	private List<String> lCodes;//待执行的指令列表
	@Override
	public void start() {
		buildPatchServiceImpl.cleanPatch();
		for (int j = 0; j < lCodes.size(); j++) {
			String code = lCodes.get(j);
			CallWorkService callWorkService = getCallWorkService(code);
			callWorkService.callWork();
		}
		System.out.println("生成完成！");
	}
	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.StartService#clearScript()
	 */
	@Override
	public void cleanScript() {
		buildPatchServiceImpl.cleanScript();
		System.out.println("清空完成！");
	}
	/* (non-Javadoc)
	 * @see com.iceberg.buildFile.service.StartService#refreshScript()
	 */
	@Override
	public void refreshScript() {
		buildPatchServiceImpl.refreshScript();
		System.out.println("刷新完成！");
	}
	public Map<String, CallWorkService> getMappingService() {
		return mappingService;
	}
	public void setMappingService(Map<String, CallWorkService> mappingService) {
		this.mappingService = mappingService;
	}
	
	public List<String> getlCodes() {
		return lCodes;
	}
	public void setlCodes(List<String> lCodes) {
		this.lCodes = lCodes;
	}
	private CallWorkService getCallWorkService(String code){
		CallWorkService callWorkService = mappingService.get(code);
		return callWorkService;
	}
}
