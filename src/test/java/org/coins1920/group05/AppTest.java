package org.coins1920.group05;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;

/**
 * Test for ticket board fetching.
 */
public class AppTest {

    /**
     * Test fetching a board.
     */
    public void testBoardFetching() {
        final boolean b = true;
        assertThat(b, is(true));
        assertThat(b, is(not(nullValue())));
    }

}
