package xades4j.properties.data;

import xades4j.properties.TimeStampValidationDataProperty;
import xades4j.utils.ObjectUtils;
import xades4j.xml.bind.xades.XmlCertificateValuesType;
import xades4j.xml.bind.xades.XmlRevocationValuesType;

public class TimeStampValidationDataStructureVerifier implements PropertyDataObjectStructureVerifier {

	@Override
	public void verifyStructure(PropertyDataObject propData) throws PropertyDataStructureException {
		TimeStampValidationDataData timeStampValidationDataData = (TimeStampValidationDataData) propData;
		XmlCertificateValuesType xmlCertificateValuesType = timeStampValidationDataData.getXmlCertificateValuesType();
		XmlRevocationValuesType xmlRevocationValuesType = timeStampValidationDataData.getXmlRevocationValuesType();

		if (ObjectUtils.anyNull(xmlCertificateValuesType, xmlRevocationValuesType)) {
			throw new PropertyDataStructureException("can not be null", TimeStampValidationDataProperty.PROP_NAME);
		}

	}

}