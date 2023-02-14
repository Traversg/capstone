package com.nashss.se.fivelifts.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fivelifts.activity.requests.GetMostRecentWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.GetMostRecentWorkoutResult;

/**
 * Takes a GetMostRecentWorkoutLambda and returns a LambdaResponse.
 */
public class GetMostRecentWorkoutLambda
    extends LambdaActivityRunner<GetMostRecentWorkoutRequest, GetMostRecentWorkoutResult>
    implements RequestHandler<AuthenticatedLambdaRequest<GetMostRecentWorkoutRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetMostRecentWorkoutRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                GetMostRecentWorkoutRequest.builder()
                    .withEmail(claims.get("email"))
                    .build()),
            (request, serviceComponent) ->
                serviceComponent.provideMostRecentWorkoutActivity().handleRequest(request)
        );
    }
}
