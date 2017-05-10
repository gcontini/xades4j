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
package xades4j.properties;

import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.List;

/**
 * The TimeStampValidationData element is an optional unsigned property qualifying the signature. 
 * 
 * */
public final class TimeStampValidationDataProperty extends UnsignedSignatureProperty
{
    public static final String PROP_NAME = "TimeStampValidationData";
    
    private final List<X509Certificate> cers;
    private final Collection<X509CRL> crls;
    
    public TimeStampValidationDataProperty(List<X509Certificate> certs ,Collection<X509CRL> crls){
    	this.cers = certs;
    	this.crls = crls;
    }

    @Override
    public String getName()
    {
        return PROP_NAME;
    }
    
    public List<X509Certificate> getCerts()
    {
        return cers;
    }
    public Collection<X509CRL> getCrls()
    {
        return crls;
    }
    
}
