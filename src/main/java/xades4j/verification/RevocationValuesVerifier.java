package xades4j.verification;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CRLException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import xades4j.properties.QualifyingProperty;
import xades4j.properties.RevocationValuesProperty;
import xades4j.properties.data.RevocationValuesData;

public class RevocationValuesVerifier implements
        QualifyingPropertyVerifier<RevocationValuesData>
{

    @Override
    public QualifyingProperty verify(RevocationValuesData propData,
            QualifyingPropertyVerificationContext ctx)
            throws InvalidPropertyException
    {
        Collection<byte[]> rawRevocationData = propData.getData();
        CertificateFactory certFactory;
        try
        {
            certFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException ex)
        {
            throw new RevocationValuesVerificationException(ex);
        }

        List<X509CRL> crls = new ArrayList<X509CRL>();

        for (byte[] crl : rawRevocationData)
        {
            InputStream inStream = new ByteArrayInputStream(crl);
            try
            {
                crls.add((X509CRL) certFactory.generateCRL(inStream));
            } catch (CRLException ex)
            {
                throw new RevocationValuesVerificationException(ex);
            }
        }

        return new RevocationValuesProperty(crls);
    }

}