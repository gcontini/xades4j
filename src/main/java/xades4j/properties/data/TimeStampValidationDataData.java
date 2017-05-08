package xades4j.properties.data;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;

import xades4j.xml.bind.xades.XmlCRLValuesType;
import xades4j.xml.bind.xades.XmlCertificateValuesType;
import xades4j.xml.bind.xades.XmlRevocationValuesType;

public class TimeStampValidationDataData implements PropertyDataObject {
	
	private XmlCertificateValuesType xmlCertificateValuesType;
	private XmlRevocationValuesType xmlRevocationValuesType;
	
	public TimeStampValidationDataData(Collection<X509Certificate> certValues , Collection<X509CRL> crlValues){
        XmlCertificateValuesType xmlCertValues = new XmlCertificateValuesType();
        List xmlCerts = xmlCertValues.getEncapsulatedX509CertificateOrOtherCertificate();

        for (X509Certificate cert : certValues)
        {
            xmlCerts.add(cert);
        }
        this.xmlCertificateValuesType = xmlCertValues;
        XmlRevocationValuesType xmlRevocValues = new XmlRevocationValuesType();
        XmlCRLValuesType xmlCRLValues = new XmlCRLValuesType();
        xmlRevocValues.setCRLValues(xmlCRLValues);

        List xmlCRLs = xmlCRLValues.getEncapsulatedCRLValue();

        for (X509CRL crl : crlValues)
        {
            xmlCRLs.add(crl);
        }
        this.xmlRevocationValuesType = xmlRevocValues;
	}
	
	public XmlCertificateValuesType getXmlCertificateValuesType() {
		return xmlCertificateValuesType;
	}
	public XmlRevocationValuesType getXmlRevocationValuesType() {
		return xmlRevocationValuesType;
	}

	
}
