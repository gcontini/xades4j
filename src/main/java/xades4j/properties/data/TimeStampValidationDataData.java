package xades4j.properties.data;

import xades4j.xml.bind.xades.XmlCertificateValuesType;
import xades4j.xml.bind.xades.XmlRevocationValuesType;

public class TimeStampValidationDataData implements PropertyDataObject {
	
	private XmlCertificateValuesType xmlCertificateValuesType;
	private XmlRevocationValuesType xmlRevocationValuesType;
	
	public TimeStampValidationDataData(XmlCertificateValuesType xmlCertificateValuesType , XmlRevocationValuesType xmlRevocationValuesType){
       this.xmlCertificateValuesType = xmlCertificateValuesType;
       this.xmlRevocationValuesType = xmlRevocationValuesType;
	}
	
	public XmlCertificateValuesType getXmlCertificateValuesType() {
		return xmlCertificateValuesType;
	}
	public XmlRevocationValuesType getXmlRevocationValuesType() {
		return xmlRevocationValuesType;
	}

}
