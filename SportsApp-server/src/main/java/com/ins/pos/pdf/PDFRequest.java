package com.ins.pos.pdf;

import java.util.Map;

public class PDFRequest {
	private String templateID;
	private Map<String, Transactions> dataObjsMap;
	private String templateVersion;
	private String userId;
	private String templateType;

	public String getTemplateID() {
		return templateID;
	}

	public void setTemplateID(String templateID) {
		this.templateID = templateID;
	}

	public Map<String, Transactions> getDataObjsMap() {
		return dataObjsMap;
	}

	public void setDataObjsMap(Map<String, Transactions> dataObjsMap) {
		this.dataObjsMap = dataObjsMap;
	}

	public String getTemplateVersion() {
		return templateVersion;
	}

	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

}
