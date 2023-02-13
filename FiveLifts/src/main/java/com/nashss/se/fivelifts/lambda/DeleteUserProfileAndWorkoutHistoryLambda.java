package com.nashss.se.fivelifts.lambda;

import com.nashss.se.fivelifts.activity.requests.DeleteUserProfileAndWorkoutHistoryRequest;
import com.nashss.se.fivelifts.activity.results.DeleteUserProfileAndWorkoutHistoryResult;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

/**
 * Takes a DeleteUserProfileAndWorkoutHistoryRequest and returns a LambdaResponse.
 */
public class DeleteUserProfileAndWorkoutHistoryLambda
    extends LambdaActivityRunner<DeleteUserProfileAndWorkoutHistoryRequest,
    DeleteUserProfileAndWorkoutHistoryResult>
    implements RequestHandler<AuthenticatedLambdaRequest<DeleteUserProfileAndWorkoutHistoryRequest>,
    LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<DeleteUserProfileAndWorkoutHistoryRequest>
                                                    input, Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    DeleteUserProfileAndWorkoutHistoryRequest.builder()
                        .withEmail(claims.get("email"))
                        .build()),
            (request, serviceComponent) ->
                serviceComponent.provideDeleteProfileAndWorkoutHistoryActivity().handleRequest(request)
        );
    }
}
