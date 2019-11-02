package org.coins1920.group05;

import org.coins1920.group05.fetcher.TrelloBoardFetcher;
import org.coins1920.group05.fetcher.model.trello.Board;
import org.coins1920.group05.fetcher.model.trello.Card;
import org.coins1920.group05.fetcher.model.trello.Member;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TrelloFetcherTest {

    private Logger logger = LoggerFactory.getLogger(TrelloFetcherTest.class);

    private static TrelloBoardFetcher fetcher;

    @BeforeClass
    public static void setUp() {
        final String key = System.getenv("TRELLO_API_KEY");
        final String token = System.getenv("TRELLO_OAUTH_KEY");
        fetcher = new TrelloBoardFetcher(key, token);
    }

    @Test
    public void testFetchSingleBoard() {
        final Board board = fetcher.fetchBoard("lgaJQMYA");
        assertThat(board, is(not(nullValue())));
        assertThat(board.getId(), is("5db19ed8f8b54324663c159c"));
        assertThat(board.getName(), is("Team5Coin"));
    }

    @Test
    public void testFetchBoardMembers() {
        final List<Member> members = fetcher.fetchBoardMembers("lgaJQMYA");
        assertThat(members, is(not(nullValue())));
        assertThat(members.size(), is(not(0)));
        logger.info("There are " + members.size() + " members!");
        logger.info(" the first one is: " + members.get(0));

        final long membersCalledBugs = members
                .stream()
                .filter(m -> m.getFullName().equals("Bugs"))
                .count();
        assertThat(membersCalledBugs, is(1L));
    }

    @Test
    public void testFetchCards() {
        final List<Card> cards = fetcher.fetchTickets("lgaJQMYA");
        assertThat(cards, is(not(nullValue())));
        assertThat(cards.size(), is(not(0)));
        logger.info("There are " + cards.size() + " cards!");
        logger.info(" the first one is: " + cards.get(0));

        final long cardsNamedRCsvStructure = cards
                .stream()
                .filter(m -> m.getName().equals("CSV structure"))
                .count();
        assertThat(cardsNamedRCsvStructure, is(1L));
    }

    @Test
    @Ignore
    public void testFetchAllBoardsOfMember() {
        final List<Board> boards = fetcher.fetchBoards();
        assertThat(boards, is(not(nullValue())));
        assertThat(boards.size(), is(not(0)));
        logger.info("There are " + boards.size() + " boards!");

        final Board firstBoard = boards.get(0);
        logger.info("boards[0] is: " + firstBoard);
        logger.info("boards[0].id = " + firstBoard.getId());
    }

}
