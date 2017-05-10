/*
 * XAdES4j - A Java library for generation and verification of XAdES signatures.
 * Copyright (C) 2010 Luis Goncalves.
 *
 * XAdES4j is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 3 of the License, or any later version.
 *
 * XAdES4j is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Lesser General Public License along
 * with XAdES4j. If not, see <http://www.gnu.org/licenses/>.
 */
package xades4j.production;

import java.security.cert.CRLException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;

import xades4j.properties.TimeStampValidationDataProperty;
import xades4j.properties.data.PropertyDataObject;
import xades4j.properties.data.TimeStampValidationDataData;
import xades4j.xml.bind.xades.XmlCRLValuesType;
import xades4j.xml.bind.xades.XmlCertificateValuesType;
import xades4j.xml.bind.xades.XmlEncapsulatedPKIDataType;
import xades4j.xml.bind.xades.XmlRevocationValuesType;

/**
 *
 */
class DataGenTimeStampValidationData implements PropertyDataObjectGenerator<TimeStampValidationDataProperty>
{
    @Override
    public PropertyDataObject generatePropertyData(
    		TimeStampValidationDataProperty prop,
            PropertiesDataGenerationContext ctx) throws PropertyDataGenerationException
    {
    	 List<X509Certificate> certs = prop.getCerts();
    	 Collection<X509CRL> crls = prop.getCrls();
    	 XmlCertificateValuesType xmlCertValuesType = new XmlCertificateValuesType();
         List xmlCerts = xmlCertValuesType.getEncapsulatedX509CertificateOrOtherCertificate();

         for (X509Certificate cert : certs)
         {
             XmlEncapsulatedPKIDataType xmlEncodCert = new XmlEncapsulatedPKIDataType();
             try {
 				xmlEncodCert.setValue(cert.getEncoded());
 			} catch (CertificateEncodingException e) {
 				throw new PropertyDataGenerationException(prop, e.getMessage() ,e);
 			}
             xmlCerts.add(xmlEncodCert);
         }

         XmlRevocationValuesType xmlRevocValuesType = new XmlRevocationValuesType();
         XmlCRLValuesType xmlCRLValues = new XmlCRLValuesType();
         xmlRevocValuesType.setCRLValues(xmlCRLValues);

         List xmlCRLs = xmlCRLValues.getEncapsulatedCRLValue();

         for (X509CRL crl : crls)
         {
             XmlEncapsulatedPKIDataType xmlEncodCert = new XmlEncapsulatedPKIDataType();
             try {
 				xmlEncodCert.setValue(crl.getEncoded());
 			} catch (CRLException e) {
 				throw new PropertyDataGenerationException(prop, e.getMessage() ,e);
 			}
             xmlCRLs.add(xmlEncodCert);
         }
        return new TimeStampValidationDataData(xmlCertValuesType ,xmlRevocValuesType);
    }
}
