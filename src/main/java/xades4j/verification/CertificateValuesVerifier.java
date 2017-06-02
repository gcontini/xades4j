package xades4j.verification;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import xades4j.properties.CertificateValuesProperty;
import xades4j.properties.QualifyingProperty;
import xades4j.properties.data.CertificateValuesData;

public class CertificateValuesVerifier implements QualifyingPropertyVerifier<CertificateValuesData>
{
    @Override
    public QualifyingProperty verify(CertificateValuesData propData,
            QualifyingPropertyVerificationContext ctx)
            throws InvalidPropertyException
    {
        Collection<byte[]> rawCerts = propData.getData();
        CertificateFactory certFactory;
        try
        {
            certFactory = CertificateFactory.getInstance("X509", "BC");
        } catch (NoSuchProviderException ex)
        {
            throw new IllegalStateException("BC provider not registered", ex);
        } catch (CertificateException ex)
        {
            throw new CertificateValuesVerificationException(ex);
        }

        List<X509Certificate> certificates = new ArrayList<X509Certificate>();

        for (byte[] cert : rawCerts)
        {
            InputStream inStream = new ByteArrayInputStream(cert);
            try
            {
                certificates.add((X509Certificate) certFactory.generateCertificate(inStream));
            } catch (CertificateException ex)
            {
                throw new CertificateValuesVerificationException(ex);
            }
        }

        return new CertificateValuesProperty(certificates);
    }

}