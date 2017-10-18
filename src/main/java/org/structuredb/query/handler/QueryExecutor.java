package org.structuredb.query.handler;

import org.structuredb.query.parser.ParsedQuery;
import org.structuredb.query.parser.QueryParser;
import org.structuredb.query.data.RawQuery;
import org.structuredb.structure.Error;
import org.structuredb.structure.Structure;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class QueryExecutor {

    private int poolSize;

    private String dataPath;

    private QueryParser queryParser;

    private ExecutorService executorService;

    public QueryExecutor(int poolSize, String dataPath) {
        this.poolSize = poolSize;
        this.dataPath = dataPath;
        this.queryParser = new QueryParser();
        this.executorService = Executors.newFixedThreadPool(poolSize);
    }

    public Structure handle(RawQuery rawQuery) throws ExecutionException, InterruptedException {
        try {
            ParsedQuery parsedQuery = queryParser.parse(rawQuery);

            // TODO: Prevent loading this on every handle call
            QueryHandlerMap queryHandlerMap = new QueryHandlerMap(parsedQuery.getQueryData(), dataPath);

            Future<Structure> future = executorService.submit(queryHandlerMap.getHandler(parsedQuery.getQueryType()));
            return future.get();
        } catch (Throwable t) {
            return new Error(t);
        }
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getDataPath() {
        return dataPath;
    }
}
