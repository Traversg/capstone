package com.nashss.se.fivelifts.lambda;

import com.nashss.se.fivelifts.activity.requests.GetWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.GetWorkoutHistoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Takes a GetWorkoutHistoryRequest and returns a LambdaResponse.
 */
public class GetWorkoutHistoryLambda
    extends LambdaActivityRunner<GetWorkoutHistoryRequest, GetWorkoutHistoryResult>
    implements RequestHandler<AuthenticatedLambdaRequest<GetWorkoutHistoryRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetWorkoutHistoryRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    GetWorkoutHistoryRequest.builder()
                            .withEmail(claims.get("email"))
                            .build()),
            (request, serviceComponent) ->
                serviceComponent.provideGetWorkoutHistoryActivity().handleRequest(request)
        );
    }
}
