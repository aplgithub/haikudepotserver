/*
 * Copyright 2014, Andrew Lindesay
 * Distributed under the terms of the MIT License.
 */

package org.haikuos.haikudepotserver.multipage.model;

import org.fest.assertions.Assertions;
import org.junit.Test;

public class PaginationTest {

    /**
     * <p>Tests the special case where there's one page.</p>
     */

    @Test
    public void testGenerateSuggestedPages_one() {
        Pagination p = new Pagination(1,0,10);
        Assertions.assertThat(p.generateSuggestedPages(6)).isEqualTo(new int[] {0});
    }

    @Test
    public void testGenerateSuggestedPages_linear() {
        Pagination p = new Pagination(50,23,10);
        Assertions.assertThat(p.generateSuggestedPages(6)).isEqualTo(new int[] {0,1,2,3,4});
    }

    @Test
    public void testGenerateSuggestedPages_fanRight() {
        Pagination p = new Pagination(500,21,10);
        Assertions.assertThat(p.generateSuggestedPages(10)).isEqualTo(new int[] {0,1,2,3,4,8,14,23,34,49});
    }

    @Test
    public void testGenerateSuggestedPages_fanLeft() {
        Pagination p = new Pagination(500,475,10);
        Assertions.assertThat(p.generateSuggestedPages(10)).isEqualTo(new int[] {0,13,23,31,38,43,45,46,48,49});
    }

    @Test
    public void testGenerateSuggestedPages_fanLeftAndRight() {
        Pagination p = new Pagination(500,250,10);
        Assertions.assertThat(p.generateSuggestedPages(10)).isEqualTo(new int[] {0,9,16,21,23,24,27,31,38,49});
    }

}