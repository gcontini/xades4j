package xades4j.verification;

import java.util.Date;

import xades4j.properties.QualifyingProperty;
import xades4j.providers.ValidationData;

public interface TimeStampProperty extends QualifyingProperty {

	/**
	 * Gets the time-stamp time.
	 * 
	 * @return the time or {@code null} if the property hasn't been processed in
	 *         signature production or verification.
	 */
	public Date getTime();

	public void setTime(Date time);

	public ValidationData getValidationData();

	public void setValidationData(ValidationData validationData);

}
