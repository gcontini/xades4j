package xades4j.verification;

import java.io.ByteArrayInputStream;
import java.security.NoSuchProviderException;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import xades4j.properties.QualifyingProperty;
import xades4j.properties.TimeStampValidationDataProperty;
import xades4j.properties.data.TimeStampValidationDataData;
import xades4j.xml.bind.xades.XmlCRLValuesType;
import xades4j.xml.bind.xades.XmlCertificateValuesType;
import xades4j.xml.bind.xades.XmlEncapsulatedPKIDataType;
import xades4j.xml.bind.xades.XmlRevocationValuesType;

public class TimeStampValidationDataVerifier implements  QualifyingPropertyVerifier<TimeStampValidationDataData> {

	@Override
	public QualifyingProperty verify(TimeStampValidationDataData propData, QualifyingPropertyVerificationContext ctx)
			throws InvalidPropertyException {
		XmlCertificateValuesType xmlCertificateValuesType = propData.getXmlCertificateValuesType();
		List<Object> encapsulatedX509CertificateOrOtherCertificate = xmlCertificateValuesType.getEncapsulatedX509CertificateOrOtherCertificate();
		List<X509Certificate> certs = new ArrayList<X509Certificate>();
		for (Object object : encapsulatedX509CertificateOrOtherCertificate) {
			certs.add((X509Certificate)object);
		}
		
		XmlRevocationValuesType xmlRevocationValuesType = propData.getXmlRevocationValuesType();
		XmlCRLValuesType crlValues = xmlRevocationValuesType.getCRLValues();
		List<XmlEncapsulatedPKIDataType> encapsulatedCRLValue = crlValues.getEncapsulatedCRLValue();
		CertificateFactory instance;
		try {
			instance = CertificateFactory.getInstance("X509", "BC");
		} catch (NoSuchProviderException ex)
		{
			throw new IllegalStateException("BC provider not registered", ex);
		} catch (CertificateException e)
		{
			throw new RevocationValuesVerificationException(e);
		}
		List<X509CRL> crls = new ArrayList<X509CRL>();
		for (XmlEncapsulatedPKIDataType xmlEncapsulatedPKIDataType : encapsulatedCRLValue) {
			byte[] value = xmlEncapsulatedPKIDataType.getValue();
			try {
				CRL crl = instance.generateCRL(new ByteArrayInputStream(value));
				crls.add((X509CRL)crl);
			} catch (CRLException e) {
				throw new RevocationValuesVerificationException(e);
			}
			
		}
		
		return new TimeStampValidationDataProperty(certs, crls);
	}



}