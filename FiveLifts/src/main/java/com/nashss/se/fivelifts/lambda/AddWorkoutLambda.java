package com.nashss.se.fivelifts.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.nashss.se.fivelifts.activity.requests.AddWorkoutRequest;
import com.nashss.se.fivelifts.activity.results.AddWorkoutResult;

/**
 * Takes a AddWorkoutRequest and returns a LambdaResponse.
 */
public class AddWorkoutLambda
        extends LambdaActivityRunner<AddWorkoutRequest, AddWorkoutResult>
        implements RequestHandler<AuthenticatedLambdaRequest<AddWorkoutRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<AddWorkoutRequest> input, Context context) {
        return super.runActivity(
            () -> {
                AddWorkoutRequest unauthenticatedRequest = input.fromBody(AddWorkoutRequest.class);
                return input.fromUserClaims(claims ->
                    AddWorkoutRequest.builder()
                        .withEmail(claims.get("email"))
                        .withWorkoutDate(unauthenticatedRequest.getWorkoutDate())
                        .withWorkoutType(unauthenticatedRequest.getWorkoutType())
                        .withTimeStarted(unauthenticatedRequest.getTimeStarted())
                        .withTimeEnded(unauthenticatedRequest.getTimeEnded())
                        .withSquatWeight(unauthenticatedRequest.getSquatWeight())
                        .withBenchPressWeight(unauthenticatedRequest.getBenchPressWeight())
                        .withOverheadPressWeight(unauthenticatedRequest.getOverheadPressWeight())
                        .withBarbellRowWeight(unauthenticatedRequest.getBarbellRowWeight())
                        .withDeadliftWeight(unauthenticatedRequest.getDeadliftWeight())
                        .withSquatReps(unauthenticatedRequest.getSquatReps())
                        .withBenchPressReps(unauthenticatedRequest.getBenchPressReps())
                        .withOverheadPressReps(unauthenticatedRequest.getOverheadPressReps())
                        .withBarbellRowReps(unauthenticatedRequest.getBarbellRowReps())
                        .withDeadliftReps(unauthenticatedRequest.getDeadliftReps())
                        .withBodyWeight(unauthenticatedRequest.getBodyWeight())
                        .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideAddWorkoutActivity().handleRequest(request)
        );
    }
}
