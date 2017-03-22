package xades4j.verification;

public class RevocationValuesVerificationException extends
        InvalidPropertyException
{
    private static final long serialVersionUID = 1L;

    public RevocationValuesVerificationException(Exception ex)
    {
        super(ex);
    }

    @Override
    protected String getVerificationMessage()
    {
        return "Can't verify revocation information";
    }

    @Override
    public String getPropertyName()
    {
        return "RevocationValues";
    }

}