package org.structuredb.query.handler;

import org.structuredb.query.data.ParsedQuery;
import org.structuredb.query.data.QueryParser;
import org.structuredb.query.data.RawQuery;
import org.structuredb.structure.Error;
import org.structuredb.structure.Structure;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QueryExecutor {

    private QueryParser queryParser;

    private ExecutorService executorService;

    public QueryExecutor(int poolSize) {
        this.queryParser = new QueryParser();
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public Structure handle(RawQuery rawQuery) throws ExecutionException, InterruptedException {
        try {
            ParsedQuery parsedQuery = queryParser.parse(rawQuery);

            // TODO: Prevent loading this on every handle call
            QueryHandlerMap queryHandlerMap = new QueryHandlerMap(parsedQuery.getQueryData());

            Future<Structure> future = executorService.submit(queryHandlerMap.getHandler(parsedQuery.getQueryType()));
            return future.get();
        } catch (Throwable t) {
            return new Error(t);
        }
    }
}
