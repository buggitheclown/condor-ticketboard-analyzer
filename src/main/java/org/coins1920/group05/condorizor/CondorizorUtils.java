package org.coins1920.group05.condorizor;

import org.coins1920.group05.model.condor.Actor;
import org.coins1920.group05.model.condor.Edge;
import org.coins1920.group05.model.general.AbstractComment;
import org.coins1920.group05.model.general.AbstractMember;
import org.coins1920.group05.model.general.AbstractTicket;
import org.coins1920.group05.model.general.Interaction;
import org.coins1920.group05.util.Pair;

import java.io.File;
import java.util.List;
import java.util.function.Function;

public class CondorizorUtils {

    // TODO: this is the "old" method, used for Trello. The type parameters differ! -> get rid of it!
    public static <M extends AbstractMember, T extends AbstractTicket> Pair<File, File> mapAndWriteToCsvFilez(
            List<M> boardMembers,
            List<T> allTickets,
            Function<List<M>, List<Actor>> toActors,
            Function<List<T>, List<Edge>> toEdges, String outputDir) {
        // map to actors and tickets:
        final List<Actor> actors = toActors.apply(boardMembers);
        final List<Edge> edges = toEdges.apply(allTickets);

        // write the CSV files to disc:
        final CondorCsvMarshaller condorCsvMarshaller = new DefaultCondorCsvMarshaller();
        return condorCsvMarshaller.write(actors, edges, outputDir);
    }

    /**
     * Maps tickets and board members to edges and actors. Writes them to the two CSV files.
     *
     * @param boardMembers a list of all known board members (incl. commentators etc.)
     * @param interactions a list of all ticket interactions associated with this board
     * @param toActors     a function that maps ticket interactions to edges
     * @param toEdges      a function that maps users to actors
     * @param outputDir    the directory where the CSV files should be written to
     * @param <M>          type parameter for members
     * @param <T>          type parameter for tickets
     * @param <C>          type param for comments
     * @return a pair of files (actors, tickets)
     */
    public static <M extends AbstractMember, T extends AbstractTicket, C extends AbstractComment> Pair<File, File> mapAndWriteToCsvFiles(
            List<M> boardMembers,
            List<Pair<T, Interaction<M, C>>> interactions,
            Function<List<M>, List<Actor>> toActors,
            Function<List<Pair<T, Interaction<M, C>>>, List<Edge>> toEdges, String outputDir) {
        // map to actors and tickets:
        final List<Actor> actors = toActors.apply(boardMembers);
        final List<Edge> edges = toEdges.apply(interactions);

        // write the CSV files to disc:
        final CondorCsvMarshaller condorCsvMarshaller = new DefaultCondorCsvMarshaller();
        return condorCsvMarshaller.write(actors, edges, outputDir);
    }
}
