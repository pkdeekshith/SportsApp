package com.ins.pos.pdf;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class PDFGeneratorService {
	private ResourceLoader resourceLoader;
	
	public PDFRequest getPDFRequest(Transactions dataObjcts, String template) {
		PDFRequest requestObj = new PDFRequest();
		Map<String, Transactions> dataObjctsBean = new HashMap<String, Transactions>();
		dataObjctsBean.put("1", dataObjcts);
		requestObj.setTemplateID(template);
		requestObj.setDataObjsMap(dataObjctsBean);
		return requestObj;
	}

	public JasperPrint createPdf(final PDFRequest pdfRequestImpl) {
		JasperPrint jasperPrint = null;
		try {
			Map<String, Transactions> dataBeanMap = pdfRequestImpl.getDataObjsMap();
			Set<String> keySet = dataBeanMap.keySet();
			for (String key : keySet) {
				Transactions dataObject = dataBeanMap.get(key);
				File file = new File("/SportsApp/Templates/"+ pdfRequestImpl.getTemplateID());
				jasperPrint = fillPdf(dataObject, file);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}

	public final JasperPrint fillPdf(final Transactions dataObject, final File jasperTemplate) {
		JasperPrint jasperPrint = null;
		try {

			List<Transactions> objList = new ArrayList<Transactions>();
			objList.add(dataObject);
			JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(objList);
			JasperReport jasperReport;
			jasperReport = (JasperReport) JRLoader.loadObject(jasperTemplate);
			jasperPrint = JasperFillManager.fillReport(jasperReport, null, beanDataSource);

		} catch (JRException e) {
			e.printStackTrace();
		}
		return jasperPrint;
	}

}
