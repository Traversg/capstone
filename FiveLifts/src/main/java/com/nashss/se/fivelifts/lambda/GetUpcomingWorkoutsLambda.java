package com.nashss.se.fivelifts.lambda;

import com.nashss.se.fivelifts.activity.requests.GetUpcomingWorkoutsRequest;
import com.nashss.se.fivelifts.activity.results.GetUpcomingWorkoutsResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Takes a GetUpcomingWorkoutsRequest and returns a LambdaResponse.
 */
public class GetUpcomingWorkoutsLambda
    extends LambdaActivityRunner<GetUpcomingWorkoutsRequest, GetUpcomingWorkoutsResult>
    implements RequestHandler<AuthenticatedLambdaRequest<GetUpcomingWorkoutsRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetUpcomingWorkoutsRequest> input, Context context) {
        return super.runActivity(
            () -> {
                return input.fromUserClaims(claims ->
                    GetUpcomingWorkoutsRequest.builder()
                        .withEmail(claims.get("email"))
                        .build());
            },
            (request, serviceComponent) ->
                serviceComponent.provideGetUpcomingWorkoutsActivity().handleRequest(request)
        );
    }
}
