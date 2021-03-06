/*
 *
 *  * Copyright (C) 2012 Brockmann Consult GmbH (info@brockmann-consult.de)
 *  *
 *  * This program is free software; you can redistribute it and/or modify it
 *  * under the terms of the GNU General Public License as published by the Free
 *  * Software Foundation; either version 3 of the License, or (at your option)
 *  * any later version.
 *  * This program is distributed in the hope that it will be useful, but WITHOUT
 *  * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 *  * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 *  * more details.
 *  *
 *  * You should have received a copy of the GNU General Public License along
 *  * with this program; if not, see http://www.gnu.org/licenses/
 *
 */

package org.esa.s3tbx.olci.radiometry.gasabsorption;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.*;

/**
 * @author muhammad.bc.
 */
public class GaseousAbsorptionAuxTest {

    private GaseousAbsorptionAux absorptionAux;

    @Before
    public void setUp() throws Exception {
        absorptionAux = GaseousAbsorptionAux.getInstance();
    }

    @Test
    public void testGetOzoneHighAux() throws Exception {
        List<double[]> ozoneHighs = absorptionAux.getOzoneHighs();
        assertNotNull(ozoneHighs);
        assertEquals(592, ozoneHighs.size());
    }


    @Test
    public void testGetConvolve() throws Exception {
        ArrayList<double[]> coeffhighres = new ArrayList<>();
        coeffhighres.add(new double[]{1, 2, 3, 4, 5, 6, 7, 8});
        coeffhighres.add(new double[]{1, 2, 3, 4, 5, 6, 7, 8});
        assertEquals(3.5, absorptionAux.convolve(3, 4, coeffhighres));
        assertEquals(4.0, absorptionAux.convolve(3, 5, coeffhighres));
        assertEquals(3.0, absorptionAux.convolve(1, 5, coeffhighres));
    }

    @Test
    public void testGetAbsorptionOLCI() throws Exception {
        double[] olciAbsorption = absorptionAux.absorptionOzone("OLCI");
        double[] expectedArrays = new double[]{
                6.666666666666667E-6,
                3.1E-4,
                0.003181818181818182,
                0.021318181818181816,
                0.04210909090909091,
                0.10538181818181819,
                0.10820909090909091,
                0.050372727272727284,
                0.041042857142857145,
                0.035614285714285716,
                0.019549999999999998,
                0.009142857142857144,
                0.0071,
                0.006966666666666667,
                0.0067,
                0.007673333333333333,
                0.002142857142857143,
                0.001227272727272727,
                0.0015,
                7.095238095238095E-4,
                0.0
        };
        assertEquals(21, olciAbsorption.length);
        Assert.assertArrayEquals(expectedArrays, olciAbsorption, 1e-8);
    }
}