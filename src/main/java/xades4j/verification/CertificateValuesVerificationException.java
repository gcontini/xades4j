package xades4j.verification;

import java.security.cert.CertificateException;

public class CertificateValuesVerificationException extends InvalidPropertyException
{
    private static final long serialVersionUID = 1L;

    public CertificateValuesVerificationException(CertificateException ex)
    {
        super(ex);
    }

    @Override
    protected String getVerificationMessage()
    {
        return "Verification failure, can't parse certificates";
    }

    @Override
    public String getPropertyName()
    {
        return "CertificateValues";
    }

}